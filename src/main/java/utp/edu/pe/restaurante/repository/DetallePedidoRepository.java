package utp.edu.pe.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.restaurante.entity.DetallePedido;

import java.util.List;


@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    
    List<DetallePedido> findByPedidoId(Long pedidoId);

    /**
     * Buscar todos los detalles que contienen un plato específico
     * 
     * @param platoId ID del plato
     * @return Lista de detalles que contienen ese plato
     */
    List<DetallePedido> findByPlatoId(Long platoId);

    /**
     * Contar cuántos detalles tiene un pedido
     * 
     * @param pedidoId ID del pedido
     * @return Número de detalles del pedido
     */
    Long countByPedidoId(Long pedidoId);
}