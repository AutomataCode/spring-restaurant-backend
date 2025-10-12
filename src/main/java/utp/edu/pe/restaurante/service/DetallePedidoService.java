package utp.edu.pe.restaurante.service;

import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreateDetallePedidoRequest;

import java.util.List;

/**
 * Interfaz del servicio para manejar operaciones de DetallePedido
 * 
 * Define los métodos que debe implementar el servicio de detalles de pedidos.
 * Estos métodos contienen la lógica de negocio para manejar detalles de
 * pedidos.
 */
public interface DetallePedidoService {

    /**
     * Obtiene todos los detalles de un pedido
     * 
     * @param pedidoId ID del pedido
     * @return Lista de detalles del pedido
     */
    List<DetallePedidoDTO> getDetallesByPedido(Long pedidoId);

    /**
     * Obtiene un detalle por su ID
     * 
     * @param id ID del detalle
     * @return DetallePedidoDTO del detalle encontrado
     */
    DetallePedidoDTO getDetalleById(Long id);

    /**
     * Crea un nuevo detalle de pedido
     * 
     * @param pedidoId ID del pedido al que pertenece
     * @param request  Datos del detalle a crear
     * @return DetallePedidoDTO del detalle creado
     */
    DetallePedidoDTO createDetalle(Long pedidoId, CreateDetallePedidoRequest request);

    /**
     * Actualiza la cantidad de un detalle
     * 
     * @param id            ID del detalle
     * @param nuevaCantidad Nueva cantidad
     * @return DetallePedidoDTO del detalle actualizado
     */
    DetallePedidoDTO actualizarCantidad(Long id, Integer nuevaCantidad);

    /**
     * Elimina un detalle de pedido
     * 
     * @param id ID del detalle a eliminar
     */
    void deleteDetalle(Long id);
}
