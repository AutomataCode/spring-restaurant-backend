package utp.edu.pe.restaurante.controller.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.service.PedidoService;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

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

    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByUsuario(@PathVariable Long usuarioId) {
        List<PedidoDTO> pedidos = pedidoService.getPedidosByUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByEstado(@PathVariable String estado) {
        List<PedidoDTO> pedidos = pedidoService.getPedidosByEstado(estado);
        return ResponseEntity.ok(pedidos);
    }
}

