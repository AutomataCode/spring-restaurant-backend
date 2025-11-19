package utp.edu.pe.restaurante.service;

import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePedidoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePedidoRequest;

import java.util.List;

/**
 * Interfaz del servicio para manejar operaciones de Pedido
 * 
 * Define los métodos que debe implementar el servicio de pedidos.
 * Estos métodos contienen la lógica de negocio para manejar pedidos.
 */
public interface PedidoService {

    /**
     * Obtiene todos los pedidos
     * 
     * @return Lista de todos los pedidos
     */
    List<PedidoDTO> getAllPedidos();

    /**
     * Obtiene un pedido por su ID
     * 
     * @param id ID del pedido
     * @return PedidoDTO del pedido encontrado
     */
    PedidoDTO getPedidoById(Long id);

    /**
     * Obtiene todos los pedidos de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Lista de pedidos del usuario
     */
    List<PedidoDTO> getPedidosByUsuario(Long usuarioId);

    /**
     * Obtiene pedidos por estado
     * 
     * @param estado Estado del pedido
     * @return Lista de pedidos con ese estado
     */
    List<PedidoDTO> getPedidosByEstado(String estado);

    /**
     * Crea un nuevo pedido
     * 
     * @param request Datos del pedido a crear
     * @return PedidoDTO del pedido creado
     */
    PedidoDTO createPedido(CreatePedidoRequest request);

    /**
     * Actualiza un pedido existente
     * 
     * @param id      ID del pedido a actualizar
     * @param request Datos a actualizar
     * @return PedidoDTO del pedido actualizado
     */
    PedidoDTO updatePedido(Long id, UpdatePedidoRequest request);

    /**
     * Elimina un pedido
     * 
     * @param id ID del pedido a eliminar
     */
    void deletePedido(Long id);

    /**
     * Actualiza el estado de un pedido
     * 
     * @param id          ID del pedido
     * @param nuevoEstado Nuevo estado del pedido
     * @return PedidoDTO del pedido actualizado
     */
    PedidoDTO actualizarEstado(Long id, String nuevoEstado);
}
