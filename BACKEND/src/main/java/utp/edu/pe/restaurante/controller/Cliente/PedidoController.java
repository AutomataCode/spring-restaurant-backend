package utp.edu.pe.restaurante.controller.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePedidoRequest;
import utp.edu.pe.restaurante.service.PedidoService;

import java.util.List;


@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Crea un nuevo pedido desde el cliente
     * 
     * @param request Datos del pedido a crear
     * @return PedidoDTO del pedido creado
     */
    @PostMapping("/pedidos")
    public ResponseEntity<PedidoDTO> createPedido(@Validated @RequestBody CreatePedidoRequest request) {
        PedidoDTO pedido = pedidoService.createPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    /**
     * Obtiene todos los pedidos del cliente (usando el endpoint público)
     * Nota: Este endpoint debería filtrar por usuario autenticado en producción
     */
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        List<PedidoDTO> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Obtiene un pedido por ID
     */
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Obtiene los pedidos de un usuario específico
     */
    @GetMapping("/pedidos/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> getPedidosByUsuario(@PathVariable Long usuarioId) {
        List<PedidoDTO> pedidos = pedidoService.getPedidosByUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }
}

