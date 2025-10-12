package utp.edu.pe.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utp.edu.pe.restaurante.entity.Pedido;

import java.util.List;
import java.util.Optional;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Buscar todos los pedidos de un usuario específico
     * 
     * @param usuarioId ID del usuario
     * @return Lista de pedidos del usuario
     */
    List<Pedido> findByUsuarioId(Long usuarioId);

    /**
     * Buscar pedidos por estado
     * 
     * @param estado Estado del pedido (PENDIENTE, EN_PREPARACION, etc.)
     * @return Lista de pedidos con ese estado
     */
    List<Pedido> findByEstado(String estado);

    /**
     * Buscar pedidos por teléfono de contacto
     * 
     * @param telefono Número de teléfono
     * @return Lista de pedidos con ese teléfono
     */
    List<Pedido> findByTelefonoContacto(String telefono);

    /**
     * Contar cuántos pedidos tiene un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Número de pedidos del usuario
     */
    Long countByUsuarioId(Long usuarioId);

    /**
     * Buscar pedido por ID con detalles cargados (EAGER)
     * También carga los platos de cada detalle
     * 
     * @param id ID del pedido
     * @return Pedido con detalles y platos cargados
     */
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.detalles d LEFT JOIN FETCH d.plato WHERE p.id = :id")
    Optional<Pedido> findByIdWithDetalles(@Param("id") Long id);
}