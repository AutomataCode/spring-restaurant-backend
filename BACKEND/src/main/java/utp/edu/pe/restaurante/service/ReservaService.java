package utp.edu.pe.restaurante.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.restaurante.entity.Reserva;
import utp.edu.pe.restaurante.entity.Usuario;
import utp.edu.pe.restaurante.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Crea una nueva reserva validando que el usuario no tenga otra dentro de un rango de 3h.
     */
    @Transactional
    public Reserva crearReserva(Usuario usuario, LocalDateTime fechaInicio, int personas, String comentario) {
        LocalDateTime rangoInicio = fechaInicio.minusHours(3);
        LocalDateTime rangoFin = fechaInicio.plusHours(3);

        List<Reserva> reservas = reservaRepository.findReservasEnRango(usuario.getId(), rangoInicio, rangoFin);

        if (!reservas.isEmpty()) {
            throw new IllegalStateException("El usuario ya tiene una reserva dentro del margen de 3 horas.");
        }

        Reserva reserva = new Reserva(usuario, fechaInicio, personas);
        reserva.setComentario(comentario);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva cambiarEstado(Long id, Reserva.Estado nuevoEstado) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        reserva.setEstado(nuevoEstado);
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
