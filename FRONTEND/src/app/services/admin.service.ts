import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Plato, Categoria } from '../models/plato.model';
import { Pedido } from '../models/pedido.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  // ========== PLATOS ==========
  getAllPlatos(): Observable<Plato[]> {
    return this.http.get<Plato[]>(`${this.apiUrl}/platos`);
  }

  getPlatoById(id: number): Observable<Plato> {
    return this.http.get<Plato>(`${this.apiUrl}/platos/${id}`);
  }

  createPlato(plato: any): Observable<Plato> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Plato>(`${this.apiUrl}/platos`, plato, { headers });
  }

  createPlatoWithImage(plato: any, image: File): Observable<Plato> {
    const formData = new FormData();
    formData.append('nombre', plato.nombre);
    formData.append('descripcion', plato.descripcion || '');
    formData.append('precio', plato.precio.toString());
    formData.append('categoriaId', plato.categoriaId.toString());
    formData.append('imagen', image);
    if (plato.tiempoPreparacion) {
      formData.append('tiempoPreparacion', plato.tiempoPreparacion.toString());
    }
    if (plato.disponibleDomicilio !== undefined) {
      formData.append('disponibleDomicilio', plato.disponibleDomicilio.toString());
    }
    return this.http.post<Plato>(`${this.apiUrl}/platos/con-imagen`, formData);
  }

  updatePlato(id: number, plato: any): Observable<Plato> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Plato>(`${this.apiUrl}/platos/${id}`, plato, { headers });
  }

  updatePlatoWithImage(id: number, plato: any, image: File): Observable<Plato> {
    const formData = new FormData();
    formData.append('nombre', plato.nombre);
    formData.append('descripcion', plato.descripcion || '');
    formData.append('precio', plato.precio.toString());
    formData.append('categoriaId', plato.categoriaId.toString());
    formData.append('imagen', image);
    if (plato.tiempoPreparacion) {
      formData.append('tiempoPreparacion', plato.tiempoPreparacion.toString());
    }
    if (plato.disponibleDomicilio !== undefined) {
      formData.append('disponibleDomicilio', plato.disponibleDomicilio.toString());
    }
    if (plato.activo !== undefined) {
      formData.append('activo', plato.activo.toString());
    }
    // Incluir la imagenUrl existente si hay una, para preservarla si no se sube nueva
    if (plato.imagenUrl) {
      formData.append('imagenUrl', plato.imagenUrl);
    }
    return this.http.put<Plato>(`${this.apiUrl}/platos/${id}/con-imagen`, formData);
  }

  desactivarPlato(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/platos/${id}`);
  }

  deletePlato(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/platos/${id}/permanente`);
  }

  reactivarPlato(id: number): Observable<Plato> {
    return this.http.patch<Plato>(`${this.apiUrl}/platos/${id}/reactivar`, {});
  }

  // ========== CATEGORÍAS ==========
  getAllCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.apiUrl}/categorias`);
  }

  getCategoriaById(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/categorias/${id}`);
  }

  createCategoria(categoria: any): Observable<Categoria> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Categoria>(`${this.apiUrl}/categorias`, categoria, { headers });
  }

  updateCategoria(id: number, categoria: any): Observable<Categoria> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Categoria>(`${this.apiUrl}/categorias/${id}`, categoria, { headers });
  }

  desactivarCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categorias/${id}`);
  }

  deleteCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categorias/${id}/permanente`);
  }

  reactivarCategoria(id: number): Observable<Categoria> {
    return this.http.patch<Categoria>(`${this.apiUrl}/categorias/${id}/reactivar`, {});
  }

  // ========== PEDIDOS ==========
  getAllPedidos(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(`${this.apiUrl}/pedidos`);
  }

  getPedidoById(id: number): Observable<Pedido> {
    return this.http.get<Pedido>(`${this.apiUrl}/pedidos/${id}`);
  }

  actualizarEstadoPedido(id: number, nuevoEstado: string): Observable<Pedido> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    // El backend espera un String, así que enviamos el string directamente
    return this.http.patch<Pedido>(`${this.apiUrl}/pedidos/${id}/estado`, `"${nuevoEstado}"`, { headers });
  }

  deletePedido(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/pedidos/${id}`);
  }

  // ========== SUBIDA DE ARCHIVOS ==========
  uploadPlatoImage(formData: FormData): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/files/upload/plato', formData);
  }

  uploadImage(file: File, subfolder: string): Observable<{ url: string; message: string }> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<{ url: string; message: string }>(`http://localhost:8080/api/files/upload/${subfolder}`, formData);
  }
}

