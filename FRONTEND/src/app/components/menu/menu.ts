import { Component, OnInit, OnDestroy, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PlatoService } from '../../services/plato.service';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';
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
    // PRIORIDAD 1: Si el plato ya tiene una URL de imagen (del backend), usarla
    if (plato.imagenUrl && plato.imagenUrl.trim() !== '') {
      // Si es una URL completa (http/https), devolverla directamente
      if (plato.imagenUrl.startsWith('http://') || plato.imagenUrl.startsWith('https://')) {
        return plato.imagenUrl;
      }
      
      // Si es una ruta relativa del backend (ej: uploads/platos/...), construir la URL completa
      // Esto corrige el problema de imágenes rotas en otros navegadores
      if (plato.imagenUrl.startsWith('uploads/') || plato.imagenUrl.startsWith('/uploads/')) {
        // Asegurar que no haya doble slash al principio
        const cleanPath = plato.imagenUrl.startsWith('/') ? plato.imagenUrl.substring(1) : plato.imagenUrl;
        return `http://localhost:8080/${cleanPath}`;
      }

      // Si es una ruta relativa del frontend (ej: /assets/...), devolverla
      if (plato.imagenUrl.startsWith('/assets/')) {
        return plato.imagenUrl;
      }
    }
    
    // PRIORIDAD 2: Buscar imagen local basada en el nombre del plato
    // Esto funciona para platos que ya tienen imágenes guardadas localmente en assets
    const imagenLocal = this.getLocalImagePath(plato.nombre);
    if (imagenLocal) {
      return imagenLocal;
    }
    
    // PRIORIDAD 3: Imagen por defecto/placeholder
    return '/assets/images/platos/default.svg';
  }

  private getLocalImagePath(nombrePlato: string): string | null {
    // Normalizar el nombre del plato para buscar la imagen
    // Convertir a minúsculas, eliminar acentos, reemplazar espacios con guiones
    const normalized = nombrePlato
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '') // Eliminar acentos
      .replace(/\s+/g, '-') // Reemplazar espacios con guiones
      .replace(/[^a-z0-9-]/g, ''); // Eliminar caracteres especiales
    
    // Mapeo manual de nombres de platos comunes a nombres de archivo
    // Puedes agregar más mapeos aquí si tienes nombres específicos
    const imageMap: { [key: string]: string } = {
      'ceviche': 'ceviche',
      'lomo-saltado': 'lomo-saltado',
      'arroz-con-pollo': 'arroz-con-pollo',
      'anticuchos': 'anticuchos',
      'pollo-a-la-brasa': 'pollo-a-la-brasa',
      'aji-de-gallina': 'aji-de-gallina',
      'causa-limena': 'causa-limena',
      'papa-a-la-huancaina': 'papa-a-la-huancaina',
    };
    
    // Buscar en el mapeo primero (para nombres exactos)
    const mappedName = imageMap[normalized];
    if (mappedName) {
      return `/assets/images/platos/${mappedName}.jpg`;
    }
    
    // Si no está en el mapeo, intentar usar el nombre normalizado directamente
    // Esto permite que cualquier plato nuevo con imagen local funcione automáticamente
    // Ejemplo: "Pollo a la Brasa" → buscará "pollo-a-la-brasa.jpg"
    return `/assets/images/platos/${normalized}.jpg`;
  }
}
