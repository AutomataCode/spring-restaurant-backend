package utp.edu.pe.restaurante.controller.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.service.DetallePedidoService;

import java.util.List;


@RestController
@RequestMapping("/api/detalles")
@CrossOrigin(origins = "*")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

   
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedidoDTO>> getDetallesByPedido(@PathVariable Long pedidoId) {
        List<DetallePedidoDTO> detalles = detallePedidoService.getDetallesByPedido(pedidoId);
        return ResponseEntity.ok(detalles);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> getDetalleById(@PathVariable Long id) {
        DetallePedidoDTO detalle = detallePedidoService.getDetalleById(id);
        return ResponseEntity.ok(detalle);
    }
}
