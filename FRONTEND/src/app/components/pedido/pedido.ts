import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { PedidoService } from '../../services/pedido.service';
import { AuthService } from '../../services/auth.service';
import { ConfigService, YapeConfig } from '../../services/config.service';
import { ImageService } from '../../services/image.service';
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
    private imageService: ImageService,
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

  generateWhatsAppMessage(): string {
    const user = this.authService.getCurrentUser();
    const tipoPedido = this.pedidoForm.tipoPedido === 'DOMICILIO' ? '🚚 Domicilio' : '🏪 Local';
    const metodoPago = this.pedidoForm.metodoPago === 'EFECTIVO' ? '💵 Efectivo' : '💚 Yape';
    
    let message = `🍽️ *NUEVO PEDIDO*\n\n`;
    
    // Información del cliente
    message += `*Cliente:*\n`;
    message += `👤 ${user?.nombre || 'Cliente'}\n`;
    message += `📱 Teléfono: ${this.pedidoForm.telefonoContacto}\n\n`;
    
    // Tipo de pedido
    message += `*Tipo de Pedido:* ${tipoPedido}\n\n`;
    
    // Dirección si es domicilio
    if (this.pedidoForm.tipoPedido === 'DOMICILIO' && this.pedidoForm.direccionEntrega) {
      message += `*Dirección de Entrega:*\n${this.pedidoForm.direccionEntrega}\n\n`;
      if (this.pedidoForm.instruccionesEntrega) {
        message += `*Instrucciones:*\n${this.pedidoForm.instruccionesEntrega}\n\n`;
      }
    }
    
    // Productos
    message += `*Productos:*\n`;
    this.cartItems.forEach((item, index) => {
      message += `${index + 1}. ${item.plato.nombre}\n`;
      message += `   Cantidad: ${item.cantidad} × S/ ${item.plato.precio.toFixed(2)}\n`;
      message += `   Subtotal: S/ ${item.subtotal.toFixed(2)}\n\n`;
    });
    
    // Total
    message += `*Total: S/ ${this.totalPrice.toFixed(2)}*\n\n`;
    
    // Método de pago
    message += `*Método de Pago:* ${metodoPago}\n`;
    if (this.pedidoForm.metodoPago === 'EFECTIVO' && this.pedidoForm.cambioPara) {
      message += `💰 Cambio para: S/ ${this.pedidoForm.cambioPara.toFixed(2)}\n`;
    }
    
    return message;
  }

  openWhatsApp(): void {
    if (!this.yapeConfig || !this.yapeConfig.whatsapp) {
      this.errorMessage = 'No se pudo obtener el número de WhatsApp de la tienda';
      return;
    }

    const message = this.generateWhatsAppMessage();
    let whatsappNumber = this.yapeConfig.whatsapp.replace(/\D/g, ''); // Solo números
    
    // Si el número no empieza con código de país, agregar código de Perú (+51)
    if (!whatsappNumber.startsWith('51')) {
      whatsappNumber = '51' + whatsappNumber;
    }
    
    const whatsappUrl = `https://wa.me/${whatsappNumber}?text=${encodeURIComponent(message)}`;
    
    window.open(whatsappUrl, '_blank');
    
    // Limpiar carrito y mostrar éxito
    const user = this.authService.getCurrentUser();
    this.cartService.clearCart();
    this.showSuccess = true;
    this.pedidoCreado = {
      id: Date.now(), // ID temporal para mostrar en la UI
      usuarioId: user?.id || 0,
      tipoPedido: this.pedidoForm.tipoPedido,
      metodoPago: this.pedidoForm.metodoPago,
      estado: 'PENDIENTE',
      fechaPedido: new Date().toISOString(),
      total: this.totalPrice,
      direccionEntrega: this.pedidoForm.direccionEntrega || undefined,
      instruccionesEntrega: this.pedidoForm.instruccionesEntrega || undefined,
      telefonoContacto: this.pedidoForm.telefonoContacto,
      cambioPara: this.pedidoForm.cambioPara,
      detalles: this.cartItems.map(item => ({
        id: 0,
        platoNombre: item.plato.nombre,
        cantidad: item.cantidad,
        precioUnitario: item.plato.precio,
        subtotal: item.subtotal
      }))
    } as Pedido;
    
    // Scroll al resultado
    setTimeout(() => {
      const resultElement = document.getElementById('pedido-resultado');
      if (resultElement) {
        resultElement.scrollIntoView({ behavior: 'smooth' });
      }
    }, 100);
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

    if (!this.yapeConfig || !this.yapeConfig.whatsapp) {
      this.errorMessage = 'No se pudo obtener el número de WhatsApp de la tienda';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    // Abrir WhatsApp con el mensaje del pedido
    this.openWhatsApp();
    this.isLoading = false;
  }

  goToMenu(): void {
    this.router.navigate(['/menu']);
  }

  goToPedidos(): void {
    this.router.navigate(['/mis-pedidos']);
  }

  getImageUrl(plato: any): string {
    return this.imageService.getPlatoImageUrl(plato);
  }

  onImageError(event: Event): void {
    this.imageService.handleImageError(event);
  }
}

