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
  isCreatingPedido: boolean = false;
  errorMessage: string = '';
  showSuccess: boolean = false;
  pedidoConfirmado: boolean = false; // Indica si el pedido ya fue confirmado/guardado
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

  generateWhatsAppMessage(pedidoId?: number): string {
    const user = this.authService.getCurrentUser();
    const tipoPedido = this.pedidoForm.tipoPedido === 'DOMICILIO' ? '🚚 Domicilio' : '🏪 Local';
    const metodoPago = this.pedidoForm.metodoPago === 'EFECTIVO' ? '💵 Efectivo' : '💚 Yape';
    
    let message = `🍽️ *NUEVO PEDIDO`;
    if (pedidoId) {
      message += ` #${pedidoId}`;
    }
    message += `*\n\n`;
    
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

  openWhatsApp(pedidoId?: number): void {
    if (!this.yapeConfig || !this.yapeConfig.whatsapp) {
      this.errorMessage = 'No se pudo obtener el número de WhatsApp de la tienda';
      return;
    }

    const message = this.generateWhatsAppMessage(pedidoId);
    let whatsappNumber = this.yapeConfig.whatsapp.replace(/\D/g, ''); // Solo números
    
    // Si el número no empieza con código de país, agregar código de Perú (+51)
    if (!whatsappNumber.startsWith('51')) {
      whatsappNumber = '51' + whatsappNumber;
    }
    
    const whatsappUrl = `https://wa.me/${whatsappNumber}?text=${encodeURIComponent(message)}`;
    
    // Abrir WhatsApp en una nueva pestaña sin bloquear el flujo
    // Usar window.open con características específicas para evitar bloqueos
    const whatsappWindow = window.open(whatsappUrl, '_blank', 'noopener,noreferrer');
    
    // Si el navegador bloquea la ventana emergente, mostrar un mensaje
    if (!whatsappWindow) {
      // Fallback: copiar el mensaje al portapapeles o mostrar instrucciones
      console.warn('La ventana de WhatsApp fue bloqueada. URL:', whatsappUrl);
      // Intentar abrir de otra manera
      window.location.href = whatsappUrl;
    }
  }

  // Validar el formulario
  validateForm(): boolean {
    if (this.pedidoForm.tipoPedido === 'DOMICILIO' && !this.pedidoForm.direccionEntrega) {
      this.errorMessage = 'La dirección de entrega es requerida para domicilio';
      return false;
    }

    if (!this.pedidoForm.telefonoContacto) {
      this.errorMessage = 'El teléfono de contacto es requerido';
      return false;
    }

    if (!this.pedidoForm.metodoPago) {
      this.errorMessage = 'Debe seleccionar un método de pago';
      return false;
    }

    if (this.pedidoForm.metodoPago === 'EFECTIVO' && !this.pedidoForm.cambioPara) {
      this.errorMessage = 'Debe indicar el monto con el que pagará';
      return false;
    }

    // Verificar que el usuario esté autenticado
    const user = this.authService.getCurrentUser();
    if (!user || !user.id) {
      this.errorMessage = 'Debe iniciar sesión para realizar un pedido';
      this.router.navigate(['/login']);
      return false;
    }

    // Validar que hay detalles
    this.prepareDetalles();
    if (!this.pedidoForm.detalles || this.pedidoForm.detalles.length === 0) {
      this.errorMessage = 'El carrito está vacío';
      return false;
    }

    return true;
  }

  // PASO 1: Confirmar y guardar el pedido en el backend
  confirmarPedido(): void {
    console.log('🔵 confirmarPedido() llamado - Guardando pedido en el backend');
    
    // Validar formulario
    if (!this.validateForm()) {
      return;
    }

    this.isCreatingPedido = true;
    this.errorMessage = '';

    // Asegurar que el usuarioId esté establecido
    const user = this.authService.getCurrentUser();
    this.pedidoForm.usuarioId = user!.id;

    // Log para debug
    console.log('📤 Enviando pedido al backend con datos:', {
      usuarioId: this.pedidoForm.usuarioId,
      tipoPedido: this.pedidoForm.tipoPedido,
      metodoPago: this.pedidoForm.metodoPago,
      direccionEntrega: this.pedidoForm.direccionEntrega,
      telefonoContacto: this.pedidoForm.telefonoContacto,
      detallesCount: this.pedidoForm.detalles.length,
      detalles: this.pedidoForm.detalles.map(d => ({
        platoId: d.platoId,
        cantidad: d.cantidad,
        precioUnitario: d.precioUnitario
      }))
    });

    // Crear el pedido en el backend
    this.pedidoService.createPedido(this.pedidoForm).subscribe({
      next: (pedido) => {
        console.log('✅ Pedido creado exitosamente en el backend:', pedido);
        console.log('✅ ID del pedido guardado:', pedido.id);
        console.log('✅ Estado del pedido:', pedido.estado);
        console.log('✅ UsuarioId del pedido:', pedido.usuarioId);
        
        // Validar que el pedido tiene un ID válido (no un timestamp)
        if (!pedido.id || pedido.id > 1000000000000) {
          console.error('❌ ERROR: El pedido tiene un ID inválido:', pedido.id);
          this.errorMessage = 'Error: El pedido no se guardó correctamente. Por favor, intente nuevamente.';
          this.isCreatingPedido = false;
          return;
        }
        
        // Pedido guardado exitosamente
        this.pedidoCreado = pedido;
        this.pedidoConfirmado = true;
        this.isCreatingPedido = false;
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
        this.isCreatingPedido = false;
        console.error('❌ Error al crear pedido:', error);
        console.error('Error response:', error.error);
        console.error('Error status:', error.status);
        
        if (error.status === 401) {
          this.errorMessage = 'Debe iniciar sesión para realizar un pedido';
          this.router.navigate(['/login']);
        } else if (error.error && error.error.message) {
          this.errorMessage = error.error.message;
        } else if (error.error && typeof error.error === 'string') {
          this.errorMessage = error.error;
        } else {
          this.errorMessage = 'Error al crear el pedido. Por favor, intente nuevamente.';
        }
      }
    });
  }

  // PASO 2: Enviar pedido por WhatsApp (solo si ya fue confirmado)
  enviarPorWhatsApp(): void {
    console.log('📱 enviarPorWhatsApp() llamado');
    
    if (!this.pedidoConfirmado || !this.pedidoCreado) {
      this.errorMessage = 'Debe confirmar el pedido primero antes de enviarlo por WhatsApp';
      return;
    }

    if (!this.yapeConfig || !this.yapeConfig.whatsapp) {
      this.errorMessage = 'No se pudo obtener el número de WhatsApp de la tienda';
      return;
    }

    // Abrir WhatsApp con el mensaje del pedido
    this.openWhatsApp(this.pedidoCreado.id);
    this.showSuccess = true;
  }

  onSubmit(): void {
    // Este método ya no se usa, pero lo mantenemos por si acaso
    this.confirmarPedido();
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

