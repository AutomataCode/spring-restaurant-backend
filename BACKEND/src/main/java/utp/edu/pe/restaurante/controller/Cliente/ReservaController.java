package utp.edu.pe.restaurante.controller.Cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.entity.Reserva;
import utp.edu.pe.restaurante.entity.Usuario;
import utp.edu.pe.restaurante.service.ReservaService;
import utp.edu.pe.restaurante.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final UsuarioRepository usuarioRepository;

    public ReservaController(ReservaService reservaService, UsuarioRepository usuarioRepository) {
        this.reservaService = reservaService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(
            @RequestParam Long usuarioId,
            @RequestParam String fechaInicio,
            @RequestParam int personas,
            @RequestParam(required = false) String comentario) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        LocalDateTime fecha = LocalDateTime.parse(fechaInicio);

        Reserva reserva = reservaService.crearReserva(usuario, fecha, personas, comentario);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Reserva> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Reserva.Estado estado) {
        return ResponseEntity.ok(reservaService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
