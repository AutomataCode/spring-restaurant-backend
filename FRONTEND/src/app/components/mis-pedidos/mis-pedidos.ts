import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PedidoService } from '../../services/pedido.service';
import { AuthService } from '../../services/auth.service';
import { Pedido } from '../../models/pedido.model';

@Component({
  selector: 'app-mis-pedidos',
  templateUrl: './mis-pedidos.html',
  styleUrl: './mis-pedidos.css',
})
export class MisPedidosComponent implements OnInit {
  pedidos: Pedido[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';
  selectedPedido: Pedido | null = null;
  showModal: boolean = false;

  constructor(
    private pedidoService: PedidoService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Verificar autenticación
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return;
    }

    this.loadPedidos();
  }

  loadPedidos(): void {
    this.isLoading = true;
    const user = this.authService.getCurrentUser();

    if (!user) {
      this.router.navigate(['/login']);
      return;
    }

    this.pedidoService.getPedidosByUsuario(user.id).subscribe({
      next: (pedidos) => {
        // Ordenar por fecha más reciente primero
        this.pedidos = pedidos.sort((a, b) =>
          new Date(b.fechaPedido).getTime() - new Date(a.fechaPedido).getTime()
        );
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        if (error.status === 401) {
          this.errorMessage = 'Debe iniciar sesión para ver sus pedidos';
          this.router.navigate(['/login']);
        } else {
          this.errorMessage = 'Error al cargar los pedidos. Por favor, intente nuevamente.';
        }
        console.error('Error al cargar pedidos:', error);
      }
    });
  }

  getEstadoClass(estado: string): string {
    const estadoLower = estado.toLowerCase();
    if (estadoLower.includes('pendiente')) return 'estado-pendiente';
    if (estadoLower.includes('preparacion')) return 'estado-preparacion';
    if (estadoLower.includes('camino') || estadoLower.includes('envio')) return 'estado-envio';
    if (estadoLower.includes('entregado')) return 'estado-entregado';
    if (estadoLower.includes('cancelado')) return 'estado-cancelado';
    return 'estado-default';
  }

  getEstadoLabel(estado: string): string {
    return estado.replace('_', ' ').toUpperCase();
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-ES', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  goToMenu(): void {
    this.router.navigate(['/menu']);
  }

  getCurrentUser() {
    return this.authService.getCurrentUser();
  }

  openPedidoModal(pedido: Pedido): void {
    this.selectedPedido = pedido;
    this.showModal = true;
    document.body.style.overflow = 'hidden'; // Prevenir scroll del body
  }

  closePedidoModal(): void {
    this.showModal = false;
    this.selectedPedido = null;
    document.body.style.overflow = ''; // Restaurar scroll del body
  }

  getTotalItems(pedido: Pedido): number {
    if (!pedido.detalles) return 0;
    return pedido.detalles.reduce((total, detalle) => total + detalle.cantidad, 0);
  }
}

