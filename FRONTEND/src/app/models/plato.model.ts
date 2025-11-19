export interface Plato {
  id: number;
  nombre: string;
  descripcion?: string;
  precio: number;
  categoriaId?: number;
  categoriaNombre?: string;
  imagenUrl?: string;
  tiempoPreparacion?: number;
  disponibleDomicilio?: boolean;
  activo?: boolean;
}

export interface Categoria {
  id: number;
  nombre: string;
  descripcion?: string;
  tipo?: 'COMIDA' | 'BEBIDA';
  activa?: boolean;
}

export interface CartItem {
  plato: Plato;
  cantidad: number;
  subtotal: number;
}

