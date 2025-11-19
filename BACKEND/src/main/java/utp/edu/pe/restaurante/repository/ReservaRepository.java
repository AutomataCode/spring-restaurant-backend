package utp.edu.pe.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utp.edu.pe.restaurante.entity.Reserva;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT r FROM Reserva r
        WHERE r.usuario.id = :usuarioId
          AND r.estado <> utp.edu.pe.restaurante.entity.Reserva.Estado.CANCELADA
          AND (
                (r.fechaInicio BETWEEN :inicio AND :fin)
             OR (r.fechaFin BETWEEN :inicio AND :fin)
             OR (:inicio BETWEEN r.fechaInicio AND r.fechaFin)
          )
        """)
    List<Reserva> findReservasEnRango(Long usuarioId, LocalDateTime inicio, LocalDateTime fin);
}
