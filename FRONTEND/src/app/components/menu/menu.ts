import { Component, OnInit, OnDestroy, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PlatoService } from '../../services/plato.service';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';
import { ImageService } from '../../services/image.service';
import { Plato, Categoria, CartItem } from '../../models/plato.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.html',
  styleUrl: './menu.css',
})
export class Menu implements OnInit, OnDestroy, AfterViewInit {
  platos: Plato[] = [];
  categorias: Categoria[] = [];
  platosFiltrados: Plato[] = [];
  platosVisibles: Plato[] = [];
  categoriaSeleccionada: string = 'all';
  isLoading: boolean = true;
  cartItems: CartItem[] = [];
  cartVisible: boolean = false;
  totalItems: number = 0;
  totalPrice: number = 0;

  // Lazy loading
  private itemsPerPage: number = 12;
  private currentPage: number = 1;
  private observer?: IntersectionObserver;

  private subscriptions: Subscription[] = [];

  constructor(
    private platoService: PlatoService,
    private cartService: CartService,
    private authService: AuthService,
    private imageService: ImageService,
    private router: Router
  ) {}

  @ViewChild('loadMoreTrigger', { static: false }) loadMoreTrigger?: ElementRef;

  ngOnInit(): void {
    this.loadCategorias();
    this.loadPlatos();
    this.subscribeToCart();
  }

  ngAfterViewInit(): void {
    this.setupIntersectionObserver();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
    if (this.observer) {
      this.observer.disconnect();
    }
  }

  loadCategorias(): void {
    const sub = this.platoService.getCategoriasConPlatos().subscribe({
      next: (categorias) => {
        this.categorias = categorias;
      },
      error: (error) => {
        console.error('Error cargando categorías:', error);
      }
    });
    this.subscriptions.push(sub);
  }

  loadPlatos(): void {
    this.isLoading = true;
    const sub = this.platoService.getAllPlatos().subscribe({
      next: (platos) => {
        this.platos = platos;
        this.platosFiltrados = platos;
        this.loadInitialItems();
        this.isLoading = false;
        // Setup observer después de que los datos estén cargados
        setTimeout(() => this.setupIntersectionObserver(), 100);
      },
      error: (error) => {
        console.error('Error cargando platos:', error);
        this.isLoading = false;
      }
    });
    this.subscriptions.push(sub);
  }

  loadInitialItems(): void {
    this.currentPage = 1;
    this.platosVisibles = this.platosFiltrados.slice(0, this.itemsPerPage);
  }

  loadMoreItems(): void {
    const nextPage = this.currentPage + 1;
    const startIndex = this.currentPage * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    const newItems = this.platosFiltrados.slice(startIndex, endIndex);
    
    if (newItems.length > 0) {
      this.platosVisibles = [...this.platosVisibles, ...newItems];
      this.currentPage = nextPage;
    }
  }

  setupIntersectionObserver(): void {
    if (typeof IntersectionObserver === 'undefined') {
      // Fallback para navegadores que no soportan IntersectionObserver
      return;
    }

    this.observer = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          this.loadMoreItems();
        }
      });
    }, {
      rootMargin: '200px' // Cargar 200px antes de llegar al elemento
    });

    // Observar el trigger después de que la vista esté inicializada
    setTimeout(() => {
      const trigger = document.querySelector('.load-more-trigger');
      if (trigger && this.observer) {
        this.observer.observe(trigger);
      }
    }, 200);
  }

  subscribeToCart(): void {
    const sub = this.cartService.cartItems$.subscribe(items => {
      this.cartItems = items;
      this.totalItems = this.cartService.getTotalItems();
      this.totalPrice = this.cartService.getTotalPrice();
    });
    this.subscriptions.push(sub);
  }

  filterByCategory(categoriaId: string | number): void {
    this.categoriaSeleccionada = categoriaId.toString();
    
    if (categoriaId === 'all') {
      this.platosFiltrados = this.platos;
    } else {
      this.platosFiltrados = this.platos.filter(
        plato => plato.categoriaId === Number(categoriaId)
      );
    }
    
    // Reiniciar la carga cuando se cambia el filtro
    this.loadInitialItems();
    
    // Reconfigurar el observer después del cambio de filtro
    if (this.observer) {
      this.observer.disconnect();
    }
    setTimeout(() => this.setupIntersectionObserver(), 100);
  }

  addToCart(plato: Plato): void {
    this.cartService.addItem(plato, 1);
    this.toggleCart();
  }

  removeFromCart(platoId: number): void {
    this.cartService.removeItem(platoId);
  }

  updateQuantity(platoId: number, cantidad: number): void {
    this.cartService.updateQuantity(platoId, cantidad);
  }

  toggleCart(): void {
    this.cartVisible = !this.cartVisible;
  }

  clearCart(): void {
    this.cartService.clearCart();
  }

  checkoutBtn(): void {
    this.router.navigate(['/pedido']);
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  getCurrentUser() {
    return this.authService.getCurrentUser();
  }

  goToLogin(): void {
    const currentUrl = this.router.url;
    this.authService.setReturnUrl(currentUrl);
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  getImageUrl(plato: Plato): string {
    return this.imageService.getPlatoImageUrl(plato);
  }

  onImageError(event: Event): void {
    this.imageService.handleImageError(event);
  }
}
