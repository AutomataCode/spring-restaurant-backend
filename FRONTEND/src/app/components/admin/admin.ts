import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { AuthService } from '../../services/auth.service';
import { WebSocketService } from '../../services/websocket.service';
import { Plato, Categoria } from '../../models/plato.model';
import { Pedido } from '../../models/pedido.model';
import { forkJoin, of, Subscription } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Message } from '@stomp/stompjs';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin implements OnInit, OnDestroy {
  // Estados de la vista
  activeTab: 'dashboard' | 'platos' | 'categorias' | 'pedidos' = 'dashboard';
  
  // Suscripciones
  private pedidosSubscription: Subscription | null = null;
  
  // Datos
  platos: Plato[] = [];
  categorias: Categoria[] = [];
  pedidos: Pedido[] = [];
  
  // Estadísticas
  stats = {
    totalPlatos: 0,
    platosActivos: 0,
    totalCategorias: 0,
    categoriasActivas: 0,
    pedidosPendientes: 0,
    pedidosHoy: 0,
    totalVentas: 0
  };
  
  // Modales y formularios
  showPlatoModal = false;
  showCategoriaModal = false;
  showPedidoModal = false;
  editingPlato: Plato | null = null;
  editingCategoria: Categoria | null = null;
  selectedPedido: Pedido | null = null;
  
  // Formularios
  platoForm: any = {
    nombre: '',
    descripcion: '',
    precio: 0,
    categoriaId: null,
    tiempoPreparacion: 0,
    disponibleDomicilio: true,
    activo: true
  };
  
  selectedImage: File | null = null;
  imagePreview: string | null = null;
  
  categoriaForm: any = {
    nombre: '',
    descripcion: '',
    tipo: 'COMIDA',
    activa: true
  };
  
  // Estados de carga
  loading = false;
  errorMessage = '';
  successMessage = '';
  
  // Filtros
  platoFilter = '';
  categoriaFilter = '';
  pedidoFilter = 'TODOS';
  
  constructor(
    private adminService: AdminService,
    private authService: AuthService,
    private webSocketService: WebSocketService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadDashboard();
    this.initWebSocket();
  }

  ngOnDestroy(): void {
    if (this.pedidosSubscription) {
      this.pedidosSubscription.unsubscribe();
    }
  }

  initWebSocket(): void {
    // Iniciar conexión WebSocket
    this.webSocketService.init();

    // Suscribirse a nuevos pedidos
    this.pedidosSubscription = this.webSocketService.watch('/topic/admin/pedidos').subscribe((message: Message) => {
      const nuevoPedido = JSON.parse(message.body);
      console.log('Nuevo pedido recibido por WebSocket:', nuevoPedido);
      
      // Mostrar notificación
      this.showSuccess(`🔔 Nuevo pedido recibido #${nuevoPedido.id}`);
      
      // Reproducir sonido de notificación
      this.playNotificationSound();

      // Actualizar lista de pedidos
      this.pedidos.unshift(nuevoPedido);
      this.calculateStats();
      
      // Si estamos en la pestaña de pedidos, recargar para asegurar orden
      if (this.activeTab === 'pedidos') {
        this.loadPedidos();
      }
    });
  }

  playNotificationSound(): void {
    const audio = new Audio('assets/sounds/notification.mp3');
    audio.play().catch(e => console.log('Error al reproducir sonido:', e));
  }
  
  // ========== NAVEGACIÓN ==========
  setActiveTab(tab: 'dashboard' | 'platos' | 'categorias' | 'pedidos'): void {
    this.activeTab = tab;
    this.clearMessages();
    
    if (tab === 'platos') {
      this.loadPlatos();
    } else if (tab === 'categorias') {
      this.loadCategorias();
    } else if (tab === 'pedidos') {
      this.loadPedidos();
    }
  }
  
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
  
  // ========== DASHBOARD ==========
  loadDashboard(): void {
    this.loading = true;
    forkJoin({
      platos: this.adminService.getAllPlatos().pipe(
        catchError(error => {
          console.error('Error cargando platos:', error);
          this.showError('Error al cargar platos. Verifique que el backend esté corriendo.');
          return of([]);
        })
      ),
      categorias: this.adminService.getAllCategorias().pipe(
        catchError(error => {
          console.error('Error cargando categorías:', error);
          this.showError('Error al cargar categorías. Verifique que el backend esté corriendo.');
          return of([]);
        })
      ),
      pedidos: this.adminService.getAllPedidos().pipe(
        catchError(error => {
          console.error('Error cargando pedidos:', error);
          return of([]);
        })
      )
    }).subscribe({
      next: ({ platos, categorias, pedidos }) => {
        this.platos = platos || [];
        this.categorias = categorias || [];
        // Ordenar pedidos del más reciente al más antiguo
        this.pedidos = (pedidos || []).sort((a, b) => {
          const dateA = new Date(a.fechaPedido).getTime();
          const dateB = new Date(b.fechaPedido).getTime();
          return dateB - dateA; // Más reciente primero
        });
        this.calculateStats();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error en dashboard:', error);
        this.showError('Error al cargar datos del dashboard. Verifique que el backend esté corriendo en http://localhost:8080');
        this.loading = false;
      }
    });
  }
  
  calculateStats(): void {
    this.stats.totalPlatos = this.platos.length;
    this.stats.platosActivos = this.platos.filter(p => p.activo).length;
    this.stats.totalCategorias = this.categorias.length;
    this.stats.categoriasActivas = this.categorias.filter(c => c.activa).length;
    this.stats.pedidosPendientes = this.pedidos.filter(p => 
      p.estado === 'PENDIENTE' || p.estado === 'EN_PREPARACION'
    ).length;
    
    const today = new Date().toISOString().split('T')[0];
    this.stats.pedidosHoy = this.pedidos.filter(p => 
      p.fechaPedido?.startsWith(today)
    ).length;
    
    this.stats.totalVentas = this.pedidos
      .filter(p => p.estado === 'ENTREGADO')
      .reduce((sum, p) => sum + (p.total || 0), 0);
  }
  
  // ========== PLATOS ==========
  loadPlatos(): void {
    this.loading = true;
    this.adminService.getAllPlatos().subscribe({
      next: (platos) => {
        this.platos = platos;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error cargando platos:', error);
        this.showError('Error al cargar platos. Verifique que el backend esté corriendo en http://localhost:8080');
        this.loading = false;
      }
    });
  }
  
  openPlatoModal(plato?: Plato): void {
    this.selectedImage = null;
    this.imagePreview = null;
    
    if (plato) {
      this.editingPlato = plato;
      this.platoForm = {
        nombre: plato.nombre,
        descripcion: plato.descripcion || '',
        precio: plato.precio,
        categoriaId: plato.categoriaId,
        imagenUrl: plato.imagenUrl || '', // Incluir imagenUrl para preservarla
        tiempoPreparacion: plato.tiempoPreparacion || 0,
        disponibleDomicilio: plato.disponibleDomicilio ?? true,
        activo: plato.activo ?? true
      };
      // Si el plato tiene imagen, mostrar preview
      if (plato.imagenUrl) {
        this.imagePreview = plato.imagenUrl.startsWith('http') 
          ? plato.imagenUrl 
          : `http://localhost:8080${plato.imagenUrl}`;
      }
    } else {
      this.editingPlato = null;
      this.platoForm = {
        nombre: '',
        descripcion: '',
        precio: 0,
        categoriaId: null,
        tiempoPreparacion: 0,
        disponibleDomicilio: true,
        activo: true
      };
    }
    this.showPlatoModal = true;
  }
  
  closePlatoModal(): void {
    this.showPlatoModal = false;
    this.editingPlato = null;
    this.selectedImage = null;
    this.imagePreview = null;
  }
  
  onImageSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedImage = file;
      // Crear preview
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagePreview = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
  
  savePlato(): void {
    if (!this.platoForm.nombre || !this.platoForm.precio || !this.platoForm.categoriaId) {
      this.showError('Por favor complete todos los campos obligatorios');
      return;
    }
    
    this.loading = true;
    
    // Si hay una imagen seleccionada, usar el método con FormData
    if (this.selectedImage) {
      const operation = this.editingPlato
        ? this.adminService.updatePlatoWithImage(this.editingPlato.id, this.platoForm, this.selectedImage)
        : this.adminService.createPlatoWithImage(this.platoForm, this.selectedImage);
      
      operation.subscribe({
        next: () => {
          this.showSuccess(this.editingPlato ? 'Plato actualizado' : 'Plato creado');
          this.closePlatoModal();
          this.loadPlatos();
          this.loadDashboard();
        },
        error: (err) => {
          let errorMessage = 'Error al guardar plato';
          if (err.error && err.error.message) {
            errorMessage = err.error.message;
          } else if (err.error && typeof err.error === 'string') {
            errorMessage = err.error;
          } else if (err.message) {
            errorMessage = err.message;
          }
          this.showError(errorMessage);
          this.loading = false;
        }
      });
    } else {
      // Si no hay imagen, usar el método normal
      const operation = this.editingPlato
        ? this.adminService.updatePlato(this.editingPlato.id, this.platoForm)
        : this.adminService.createPlato(this.platoForm);
      
      operation.subscribe({
        next: () => {
          this.showSuccess(this.editingPlato ? 'Plato actualizado' : 'Plato creado');
          this.closePlatoModal();
          this.loadPlatos();
          this.loadDashboard();
        },
        error: (err) => {
          let errorMessage = 'Error al guardar plato';
          if (err.error && err.error.message) {
            errorMessage = err.error.message;
          } else if (err.error && typeof err.error === 'string') {
            errorMessage = err.error;
          } else if (err.message) {
            errorMessage = err.message;
          }
          this.showError(errorMessage);
          this.loading = false;
        }
      });
    }
  }
  
  desactivarPlato(id: number): void {
    if (!confirm('¿Está seguro de desactivar este plato?')) return;
    
    this.loading = true;
    this.adminService.desactivarPlato(id).subscribe({
      next: () => {
        this.showSuccess('Plato desactivado');
        this.loadPlatos();
        this.loadDashboard();
      },
      error: () => {
        this.showError('Error al desactivar plato');
        this.loading = false;
      }
    });
  }
  
  reactivarPlato(id: number): void {
    this.loading = true;
    this.adminService.reactivarPlato(id).subscribe({
      next: () => {
        this.showSuccess('Plato reactivado');
        this.loadPlatos();
        this.loadDashboard();
      },
      error: () => {
        this.showError('Error al reactivar plato');
        this.loading = false;
      }
    });
  }

  deletePlato(id: number): void {
    if (!confirm('¿Está seguro de ELIMINAR PERMANENTEMENTE este plato? Esta acción no se puede deshacer.')) return;
    
    this.loading = true;
    this.adminService.deletePlato(id).subscribe({
      next: () => {
        this.showSuccess('Plato eliminado permanentemente');
        this.loadPlatos();
        this.loadDashboard();
      },
      error: (error) => {
        this.showError('Error al eliminar plato: ' + (error.error?.message || 'Error desconocido'));
        this.loading = false;
      }
    });
  }
  
  // ========== CATEGORÍAS ==========
  loadCategorias(): void {
    this.loading = true;
    this.adminService.getAllCategorias().subscribe({
      next: (categorias) => {
        this.categorias = categorias;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error cargando categorías:', error);
        this.showError('Error al cargar categorías. Verifique que el backend esté corriendo en http://localhost:8080');
        this.loading = false;
      }
    });
  }
  
  openCategoriaModal(categoria?: Categoria): void {
    if (categoria) {
      this.editingCategoria = categoria;
      this.categoriaForm = {
        nombre: categoria.nombre,
        descripcion: categoria.descripcion || '',
        tipo: categoria.tipo || 'COMIDA',
        activa: categoria.activa ?? true
      };
    } else {
      this.editingCategoria = null;
      this.categoriaForm = {
        nombre: '',
        descripcion: '',
        tipo: 'COMIDA',
        activa: true
      };
    }
    this.showCategoriaModal = true;
  }
  
  closeCategoriaModal(): void {
    this.showCategoriaModal = false;
    this.editingCategoria = null;
  }
  
  saveCategoria(): void {
    if (!this.categoriaForm.nombre) {
      this.showError('El nombre es obligatorio');
      return;
    }
    
    this.loading = true;
    const operation = this.editingCategoria
      ? this.adminService.updateCategoria(this.editingCategoria.id, this.categoriaForm)
      : this.adminService.createCategoria(this.categoriaForm);
    
    operation.subscribe({
      next: () => {
        this.showSuccess(this.editingCategoria ? 'Categoría actualizada' : 'Categoría creada');
        this.closeCategoriaModal();
        this.loadCategorias();
        this.loadDashboard();
      },
      error: () => {
        this.showError('Error al guardar categoría');
        this.loading = false;
      }
    });
  }
  
  desactivarCategoria(id: number): void {
    if (!confirm('¿Está seguro de desactivar esta categoría? También se desactivarán sus platos.')) return;
    
    this.loading = true;
    this.adminService.desactivarCategoria(id).subscribe({
      next: () => {
        this.showSuccess('Categoría desactivada');
        this.loadCategorias();
        this.loadPlatos();
        this.loadDashboard();
      },
      error: () => {
        this.showError('Error al desactivar categoría');
        this.loading = false;
      }
    });
  }
  
  reactivarCategoria(id: number): void {
    this.loading = true;
    this.adminService.reactivarCategoria(id).subscribe({
      next: () => {
        this.showSuccess('Categoría reactivada');
        this.loadCategorias();
        this.loadDashboard();
      },
      error: () => {
        this.showError('Error al reactivar categoría');
        this.loading = false;
      }
    });
  }

  deleteCategoria(id: number): void {
    if (!confirm('¿Está seguro de ELIMINAR PERMANENTEMENTE esta categoría? Esta acción no se puede deshacer. La categoría no debe tener platos activos.')) return;
    
    this.loading = true;
    this.adminService.deleteCategoria(id).subscribe({
      next: () => {
        this.showSuccess('Categoría eliminada permanentemente');
        this.loadCategorias();
        this.loadDashboard();
      },
      error: (error) => {
        this.showError('Error al eliminar categoría: ' + (error.error?.message || 'Error desconocido'));
        this.loading = false;
      }
    });
  }
  
  // ========== PEDIDOS ==========
  loadPedidos(): void {
    this.loading = true;
    this.adminService.getAllPedidos().subscribe({
      next: (pedidos) => {
        // Ordenar pedidos del más reciente al más antiguo
        this.pedidos = pedidos.sort((a, b) => {
          const dateA = new Date(a.fechaPedido).getTime();
          const dateB = new Date(b.fechaPedido).getTime();
          return dateB - dateA; // Más reciente primero
        });
        this.loading = false;
      },
      error: () => {
        this.showError('Error al cargar pedidos');
        this.loading = false;
      }
    });
  }
  
  openPedidoModal(pedido: Pedido): void {
    this.selectedPedido = pedido;
    this.showPedidoModal = true;
  }
  
  closePedidoModal(): void {
    this.showPedidoModal = false;
    this.selectedPedido = null;
  }
  
  aceptarPedido(pedido: Pedido): void {
    this.cambiarEstadoPedido(pedido, 'EN_PREPARACION');
  }

  rechazarPedido(pedido: Pedido): void {
    if (!confirm('¿Está seguro de rechazar este pedido?')) return;
    this.cambiarEstadoPedido(pedido, 'CANCELADO');
  }
  
  cambiarEstadoPedido(pedido: Pedido, nuevoEstado: string): void {
    this.loading = true;
    console.log('Cambiando estado del pedido:', pedido.id, 'a:', nuevoEstado);
    
    this.adminService.actualizarEstadoPedido(pedido.id, nuevoEstado).subscribe({
      next: (pedidoActualizado) => {
        console.log('Pedido actualizado recibido:', pedidoActualizado);
        console.log('Estado del pedido actualizado:', pedidoActualizado.estado);
        
        // Actualizar el pedido en la lista local inmediatamente
        const index = this.pedidos.findIndex(p => p.id === pedido.id);
        if (index !== -1) {
          // Usar el estado que viene del backend (que es el correcto)
          const estadoFinal = pedidoActualizado.estado || nuevoEstado;
          this.pedidos[index] = { ...pedidoActualizado, estado: estadoFinal };
          console.log('Pedido actualizado en lista local con estado:', estadoFinal);
        } else {
          // Si no se encuentra, agregarlo
          const estadoFinal = pedidoActualizado.estado || nuevoEstado;
          this.pedidos.push({ ...pedidoActualizado, estado: estadoFinal });
          console.log('Pedido agregado a lista local con estado:', estadoFinal);
        }
        
        let mensaje = '';
        switch(nuevoEstado) {
          case 'EN_PREPARACION':
            mensaje = 'Pedido aceptado y en preparación';
            break;
          case 'EN_CAMINO':
            mensaje = 'Pedido marcado como en camino';
            break;
          case 'ENTREGADO':
            mensaje = 'Pedido marcado como entregado';
            break;
          case 'CANCELADO':
            mensaje = 'Pedido cancelado';
            break;
          default:
            mensaje = 'Estado del pedido actualizado';
        }
        this.showSuccess(mensaje);
        this.closePedidoModal();
        this.loading = false;
        
        // Forzar recarga inmediata para asegurar sincronización
        this.loadPedidos();
        this.loadDashboard();
      },
      error: (error) => {
        console.error('Error al actualizar estado:', error);
        console.error('Error completo:', JSON.stringify(error));
        this.showError('Error al actualizar estado del pedido: ' + (error.error?.message || 'Error desconocido'));
        this.loading = false;
      }
    });
  }

  marcarListo(pedido: Pedido): void {
    // "Listo" significa que está listo para enviar, cambia de EN_PREPARACION a EN_CAMINO
    this.cambiarEstadoPedido(pedido, 'EN_CAMINO');
  }
  
  getEstadoColor(estado: string): string {
    const estados: { [key: string]: string } = {
      'PENDIENTE': 'warning',
      'EN_PREPARACION': 'info',
      'EN_CAMINO': 'primary',
      'ENTREGADO': 'success',
      'CANCELADO': 'danger'
    };
    return estados[estado] || 'secondary';
  }
  
  getEstadoLabel(estado: string): string {
    const labels: { [key: string]: string } = {
      'PENDIENTE': 'Pendiente',
      'EN_PREPARACION': 'En Preparación',
      'EN_CAMINO': 'En Camino',
      'ENTREGADO': 'Entregado',
      'CANCELADO': 'Cancelado'
    };
    return labels[estado] || estado;
  }
  
  // ========== UTILIDADES ==========
  getPlatosFiltrados(): Plato[] {
    let filtrados = this.platos;
    if (this.platoFilter) {
      filtrados = filtrados.filter(p => 
        p.nombre.toLowerCase().includes(this.platoFilter.toLowerCase())
      );
    }
    return filtrados;
  }
  
  getCategoriasFiltradas(): Categoria[] {
    let filtradas = this.categorias;
    if (this.categoriaFilter) {
      filtradas = filtradas.filter(c => 
        c.nombre.toLowerCase().includes(this.categoriaFilter.toLowerCase())
      );
    }
    return filtradas;
  }
  
  getPedidosFiltrados(): Pedido[] {
    let filtrados = this.pedidos;
    if (this.pedidoFilter !== 'TODOS') {
      filtrados = filtrados.filter(p => p.estado === this.pedidoFilter);
    }
    return filtrados;
  }

  // Métodos para obtener pedidos por estado (para vista Kanban)
  getPedidosPendientes(): Pedido[] {
    return this.pedidos.filter(p => p.estado && p.estado.toUpperCase() === 'PENDIENTE');
  }

  getPedidosEnPreparacion(): Pedido[] {
    return this.pedidos.filter(p => p.estado && p.estado.toUpperCase() === 'EN_PREPARACION');
  }

  getPedidosEnCamino(): Pedido[] {
    return this.pedidos.filter(p => p.estado && p.estado.toUpperCase() === 'EN_CAMINO');
  }

  getPedidosEntregados(): Pedido[] {
    return this.pedidos.filter(p => p.estado && p.estado.toUpperCase() === 'ENTREGADO');
  }

  getPedidosCancelados(): Pedido[] {
    return this.pedidos.filter(p => p.estado && p.estado.toUpperCase() === 'CANCELADO');
  }

  // Contadores para cada estado
  getCountPendientes(): number {
    return this.getPedidosPendientes().length;
  }

  getCountEnPreparacion(): number {
    return this.getPedidosEnPreparacion().length;
  }

  getCountEnCamino(): number {
    return this.getPedidosEnCamino().length;
  }

  getCountEntregados(): number {
    return this.getPedidosEntregados().length;
  }

  getCountCancelados(): number {
    return this.getPedidosCancelados().length;
  }
  
  showError(message: string): void {
    this.errorMessage = message;
    this.successMessage = '';
    setTimeout(() => this.errorMessage = '', 5000);
  }
  
  showSuccess(message: string): void {
    this.successMessage = message;
    this.errorMessage = '';
    setTimeout(() => this.successMessage = '', 5000);
  }
  
  clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }
  
  formatDate(dateString: string): string {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('es-PE', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }
  
  formatCurrency(amount: number): string {
    return new Intl.NumberFormat('es-PE', {
      style: 'currency',
      currency: 'PEN'
    }).format(amount);
  }
}
