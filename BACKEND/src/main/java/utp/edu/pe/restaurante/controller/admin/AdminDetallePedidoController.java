package utp.edu.pe.restaurante.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreateDetallePedidoRequest;
import utp.edu.pe.restaurante.service.DetallePedidoService;

import java.util.List;


@RestController
@RequestMapping("/api/admin/detalles")
@CrossOrigin(origins = "*")
public class AdminDetallePedidoController {

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

   
    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<DetallePedidoDTO> createDetalle(@PathVariable Long pedidoId,
            @RequestBody CreateDetallePedidoRequest request) {
        DetallePedidoDTO detalle = detallePedidoService.createDetalle(pedidoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }

    
    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<DetallePedidoDTO> actualizarCantidad(@PathVariable Long id,
            @RequestBody Integer nuevaCantidad) {
        DetallePedidoDTO detalle = detallePedidoService.actualizarCantidad(id, nuevaCantidad);
        return ResponseEntity.ok(detalle);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        detallePedidoService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
