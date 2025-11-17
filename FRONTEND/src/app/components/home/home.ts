import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit, OnDestroy, AfterViewInit {
  isAuthenticated: boolean = false;
  currentUser: Usuario | null = null;
  private authSubscription?: Subscription;
  private galleryObserver?: IntersectionObserver;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Verificar estado inicial
    this.isAuthenticated = this.authService.isAuthenticated();
    this.currentUser = this.authService.getCurrentUser();
    
    // Si el usuario es admin, redirigir al panel admin
    if (this.authService.isAdmin()) {
      this.router.navigate(['/admin']);
      return;
    }
    
    // Suscribirse a cambios en el estado de autenticación
    this.authSubscription = this.authService.currentUser$.subscribe(user => {
      this.isAuthenticated = user !== null;
      this.currentUser = user;
      
      // Si el usuario se convierte en admin, redirigir
      if (user && this.authService.isAdmin()) {
        this.router.navigate(['/admin']);
      }
    });
  }

  ngAfterViewInit(): void {
    this.setupGalleryLazyLoading();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.galleryObserver) {
      this.galleryObserver.disconnect();
    }
  }

  setupGalleryLazyLoading(): void {
    if (typeof IntersectionObserver === 'undefined') {
      // Fallback: cargar todas las imágenes si no hay soporte
      this.loadAllGalleryImages();
      return;
    }

    this.galleryObserver = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          const element = entry.target as HTMLElement;
          const imageUrl = element.getAttribute('data-lazy-bg');
          if (imageUrl) {
            const imageDiv = element.querySelector('.gallery-image') as HTMLElement;
            if (imageDiv && !imageDiv.style.backgroundImage) {
              imageDiv.style.backgroundImage = `url('${imageUrl}')`;
              imageDiv.style.opacity = '0';
              setTimeout(() => {
                imageDiv.style.transition = 'opacity 0.5s ease';
                imageDiv.style.opacity = '1';
              }, 10);
            }
            this.galleryObserver?.unobserve(element);
          }
        }
      });
    }, {
      rootMargin: '100px'
    });

    setTimeout(() => {
      const galleryItems = document.querySelectorAll('.gallery-item[data-lazy-bg]');
      galleryItems.forEach(item => {
        this.galleryObserver?.observe(item);
      });
    }, 100);
  }

  loadAllGalleryImages(): void {
    const galleryItems = document.querySelectorAll('.gallery-item[data-lazy-bg]');
    galleryItems.forEach((item) => {
      const element = item as HTMLElement;
      const imageUrl = element.getAttribute('data-lazy-bg');
      if (imageUrl) {
        const imageDiv = element.querySelector('.gallery-image') as HTMLElement;
        if (imageDiv) {
          imageDiv.style.backgroundImage = `url('${imageUrl}')`;
        }
      }
    });
  }

  goToLogin(): void {
    // Guardar la URL actual antes de redirigir al login
    const currentUrl = this.router.url;
    this.authService.setReturnUrl(currentUrl);
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  goToPedido(): void {
    // Verificar si hay items en el carrito
    this.router.navigate(['/pedido']);
  }
}
