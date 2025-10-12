package utp.edu.pe.restaurante.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePedidoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePedidoRequest;
import utp.edu.pe.restaurante.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/pedidos")
@CrossOrigin(origins = "*")
public class AdminPedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        List<PedidoDTO> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody CreatePedidoRequest request) {
        PedidoDTO pedido = pedidoService.createPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id, @RequestBody UpdatePedidoRequest request) {
        PedidoDTO pedido = pedidoService.updatePedido(id, request);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        PedidoDTO pedido = pedidoService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(pedido);
    }
}
