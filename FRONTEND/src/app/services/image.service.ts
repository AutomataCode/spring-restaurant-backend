import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private readonly API_BASE_URL = 'http://localhost:8080';
  private readonly DEFAULT_IMAGE = '/assets/images/platos/default.svg';

  /**
   * Obtiene la URL completa de una imagen
   * @param imageUrl URL de la imagen (puede ser relativa o absoluta)
   * @returns URL completa de la imagen
   */
  getImageUrl(imageUrl: string | null | undefined): string {
    // Si no hay URL, retornar imagen por defecto
    if (!imageUrl || imageUrl.trim() === '') {
      return this.DEFAULT_IMAGE;
    }

    const trimmedUrl = imageUrl.trim();

    // Si ya es una URL completa (http/https), devolverla directamente
    if (trimmedUrl.startsWith('http://') || trimmedUrl.startsWith('https://')) {
      return trimmedUrl;
    }

    // Si es una ruta relativa del backend (uploads/... o /uploads/...)
    // Construir URL completa del backend
    if (trimmedUrl.startsWith('uploads/') || trimmedUrl.startsWith('/uploads/')) {
      // Limpiar la ruta (remover slash inicial si existe)
      const cleanPath = trimmedUrl.startsWith('/') ? trimmedUrl.substring(1) : trimmedUrl;
      // Construir URL completa asegurando que no haya doble slash
      return `${this.API_BASE_URL}/${cleanPath}`;
    }

    // Si empieza con / pero no es /uploads/, asumir que es una ruta del backend
    if (trimmedUrl.startsWith('/')) {
      // Remover el slash inicial y construir URL completa
      const cleanPath = trimmedUrl.substring(1);
      return `${this.API_BASE_URL}/${cleanPath}`;
    }

    // Si no tiene slash inicial, asumir que es una ruta del backend
    return `${this.API_BASE_URL}/${trimmedUrl}`;
  }

  /**
   * Obtiene la URL de imagen de un plato
   * @param plato Objeto plato con imagenUrl
   * @returns URL completa de la imagen del plato
   */
  getPlatoImageUrl(plato: { imagenUrl?: string | null; nombre?: string }): string {
    // Si el plato tiene una URL de imagen, usarla
    if (plato.imagenUrl && plato.imagenUrl.trim() !== '') {
      return this.getImageUrl(plato.imagenUrl);
    }

    // Si no hay URL, retornar imagen por defecto
    return this.DEFAULT_IMAGE;
  }

  /**
   * Maneja errores de carga de imágenes
   * @param event Evento de error de la imagen
   * @param defaultImage URL de imagen por defecto
   */
  handleImageError(event: Event, defaultImage: string = this.DEFAULT_IMAGE): void {
    const img = event.target as HTMLImageElement;
    if (img && img.src !== defaultImage) {
      img.src = defaultImage;
    }
  }

  /**
   * Verifica si una URL de imagen es válida
   * @param imageUrl URL de la imagen
   * @returns true si la URL es válida
   */
  isValidImageUrl(imageUrl: string | null | undefined): boolean {
    if (!imageUrl || imageUrl.trim() === '') {
      return false;
    }
    return true;
  }
}

