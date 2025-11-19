import { Component, OnInit, OnDestroy, signal, Inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrls: ['./app.css']
})
export class App implements OnInit, OnDestroy {
  title = signal('angular-restaurant-frontend');
  private defaultTitle = 'Restaurante Gustitos - Restaurante';
  private hiddenTitle = 'No te quedes con hambre!';
  private visibilityChangeHandler?: () => void;

  constructor(
    private titleService: Title,
    @Inject(DOCUMENT) private document: Document
  ) {}

  ngOnInit(): void {
    // Establecer título inicial
    this.titleService.setTitle(this.defaultTitle);

    // Detectar cambios de visibilidad de la página
    if (typeof this.document.hidden !== 'undefined') {
      this.visibilityChangeHandler = () => {
        if (this.document.hidden) {
          // Pestaña oculta - cambiar título
          this.titleService.setTitle(this.hiddenTitle);
        } else {
          // Pestaña visible - restaurar título original
          this.titleService.setTitle(this.defaultTitle);
        }
      };

      this.document.addEventListener('visibilitychange', this.visibilityChangeHandler);
    }

    // También usar el evento blur/focus como fallback
    const handleBlur = () => {
      this.titleService.setTitle(this.hiddenTitle);
    };

    const handleFocus = () => {
      this.titleService.setTitle(this.defaultTitle);
    };

    window.addEventListener('blur', handleBlur);
    window.addEventListener('focus', handleFocus);

    // Guardar referencias para cleanup
    (this as any)._blurHandler = handleBlur;
    (this as any)._focusHandler = handleFocus;
  }

  ngOnDestroy(): void {
    if (this.visibilityChangeHandler) {
      this.document.removeEventListener('visibilitychange', this.visibilityChangeHandler);
    }

    if ((this as any)._blurHandler) {
      window.removeEventListener('blur', (this as any)._blurHandler);
    }

    if ((this as any)._focusHandler) {
      window.removeEventListener('focus', (this as any)._focusHandler);
    }
  }
}
