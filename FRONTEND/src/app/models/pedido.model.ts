export interface CreateDetallePedidoRequest {
  platoId: number;
  cantidad: number;
  precioUnitario: number;
  notas?: string;
}

export interface CreatePedidoRequest {
  usuarioId?: number;
  tipoPedido: 'DOMICILIO' | 'LOCAL';
  direccionEntrega?: string;
  instruccionesEntrega?: string;
  telefonoContacto?: string;
  metodoPago: 'EFECTIVO' | 'TARJETA' | 'TRANSFERENCIA';
  cambioPara?: number;
  detalles: CreateDetallePedidoRequest[];
}

export interface DetallePedido {
  id?: number;
  pedidoId?: number;
  platoId?: number;
  platoNombre?: string;
  cantidad: number;
  precioUnitario: number;
  subtotal: number;
  notas?: string;
}

export interface Pedido {
  id: number;
  usuarioId: number;
  tipoPedido: string;
  estado: string;
  fechaPedido: string;
  total: number;
  direccionEntrega?: string;
  instruccionesEntrega?: string;
  telefonoContacto?: string;
  metodoPago: string;
  cambioPara?: number;
  detalles?: DetallePedido[];
}

