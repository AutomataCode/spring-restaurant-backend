import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { PedidoService } from '../../services/pedido.service';
import { AuthService } from '../../services/auth.service';
import { ConfigService, YapeConfig } from '../../services/config.service';
import { CartItem } from '../../models/plato.model';
import { CreatePedidoRequest, Pedido } from '../../models/pedido.model';

@Component({
  selector: 'app-pedido',
  templateUrl: './pedido.html',
  styleUrl: './pedido.css',
})
export class PedidoComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalPrice: number = 0;

  pedidoForm: CreatePedidoRequest = {
    tipoPedido: 'DOMICILIO',
    metodoPago: 'EFECTIVO',
    direccionEntrega: '',
    telefonoContacto: '',
    instruccionesEntrega: '',
    cambioPara: undefined,
    detalles: []
  };

  pedidoCreado: Pedido | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  showSuccess: boolean = false;
  yapeConfig: YapeConfig | null = null;

  constructor(
    private cartService: CartService,
    private pedidoService: PedidoService,
    private authService: AuthService,
    private configService: ConfigService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Verificar autenticación
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return;
    }

    // Cargar items del carrito
    this.cartItems = this.cartService.getCartItems();
    this.totalPrice = this.cartService.getTotalPrice();

    // Si no hay items, redirigir al menú
    if (this.cartItems.length === 0) {
      this.router.navigate(['/menu']);
      return;
    }

    // Cargar datos del usuario
    const user = this.authService.getCurrentUser();
    if (user) {
      this.pedidoForm.usuarioId = user.id;
      this.pedidoForm.telefonoContacto = user.telefono || '';
      this.pedidoForm.direccionEntrega = user.direccion || '';
    }

    // Preparar detalles del pedido
    this.prepareDetalles();

    // Cargar configuración de Yape
    this.loadYapeConfig();
  }

  loadYapeConfig(): void {
    this.configService.getYapeConfig().subscribe({
      next: (config) => {
        this.yapeConfig = config;
      },
      error: (error) => {
        console.error('Error al cargar configuración de Yape:', error);
        // Si falla, usar valores por defecto
        this.yapeConfig = {
          numero: '908556931',
          codigo: '908556931',
          whatsapp: '908556931',
          qrUrl: ''
        };
      }
    });
  }

  onQrImageError(event: any): void {
    // Si la imagen no se puede cargar, ocultarla
    const img = event.target;
    if (img && img.parentElement) {
      img.parentElement.style.display = 'none';
    }
  }

  prepareDetalles(): void {
    this.pedidoForm.detalles = this.cartItems.map(item => ({
      platoId: item.plato.id,
      cantidad: item.cantidad,
      precioUnitario: item.plato.precio,
      notas: ''
    }));
  }

  onSubmit(): void {
    // Validaciones
    if (this.pedidoForm.tipoPedido === 'DOMICILIO' && !this.pedidoForm.direccionEntrega) {
      this.errorMessage = 'La dirección de entrega es requerida para domicilio';
      return;
    }

    if (!this.pedidoForm.telefonoContacto) {
      this.errorMessage = 'El teléfono de contacto es requerido';
      return;
    }

    if (!this.pedidoForm.metodoPago) {
      this.errorMessage = 'Debe seleccionar un método de pago';
      return;
    }

    if (this.pedidoForm.metodoPago === 'EFECTIVO' && !this.pedidoForm.cambioPara) {
      this.errorMessage = 'Debe indicar el monto con el que pagará';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    // Asegurar que los detalles están preparados
    this.prepareDetalles();

    this.pedidoService.createPedido(this.pedidoForm).subscribe({
      next: (pedido) => {
        this.isLoading = false;
        this.pedidoCreado = pedido;
        this.showSuccess = true;
        this.cartService.clearCart();
        
        // Scroll al resultado
        setTimeout(() => {
          const resultElement = document.getElementById('pedido-resultado');
          if (resultElement) {
            resultElement.scrollIntoView({ behavior: 'smooth' });
          }
        }, 100);
      },
      error: (error) => {
        this.isLoading = false;
        if (error.status === 401) {
          this.errorMessage = 'Debe iniciar sesión para realizar un pedido';
          this.router.navigate(['/login']);
        } else if (error.error && error.error.message) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = 'Error al crear el pedido. Por favor, intente nuevamente.';
        }
        console.error('Error al crear pedido:', error);
      }
    });
  }

  goToMenu(): void {
    this.router.navigate(['/menu']);
  }

  goToPedidos(): void {
    this.router.navigate(['/mis-pedidos']);
  }
}

