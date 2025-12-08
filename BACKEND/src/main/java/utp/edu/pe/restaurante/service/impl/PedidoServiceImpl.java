package utp.edu.pe.restaurante.service.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePedidoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePedidoRequest;
import utp.edu.pe.restaurante.dto.request.CreateDetallePedidoRequest;
import utp.edu.pe.restaurante.entity.Pedido;
import utp.edu.pe.restaurante.entity.DetallePedido;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.entity.Usuario;
import utp.edu.pe.restaurante.exception.ResourceNotFoundException;
import utp.edu.pe.restaurante.mapper.PedidoMapper;
import utp.edu.pe.restaurante.mapper.DetallePedidoMapper;
import utp.edu.pe.restaurante.repository.PedidoRepository;
import utp.edu.pe.restaurante.repository.DetallePedidoRepository;
import utp.edu.pe.restaurante.repository.PlatoRepository;
import utp.edu.pe.restaurante.repository.UsuarioRepository;
import utp.edu.pe.restaurante.service.PedidoService;

import java.util.List;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO> getAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toDTOList(pedidos);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
        return pedidoMapper.toDTO(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO> getPedidosByUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return pedidoMapper.toDTOList(pedidos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO> getPedidosByEstado(String estado) {
        List<Pedido> pedidos = pedidoRepository.findByEstado(estado);
        return pedidoMapper.toDTOList(pedidos);
    }

    @Override
    public PedidoDTO createPedido(CreatePedidoRequest request) {
        System.out.println("DEBUG: CreatePedidoRequest recibido: " + request);
        System.out.println("DEBUG: usuarioId = " + request.getUsuarioId());
        System.out.println(
                "DEBUG: detalles = " + (request.getDetalles() != null ? request.getDetalles().size() : "null"));

        // Validar que el usuario existe
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        // Crear el pedido
        Pedido pedido = pedidoMapper.toEntity(request);
        pedido.setUsuario(usuario);
        pedido = pedidoRepository.save(pedido);

        // Crear los detalles del pedido
        if (request.getDetalles() != null && !request.getDetalles().isEmpty()) {
            for (CreateDetallePedidoRequest detalleRequest : request.getDetalles()) {
                // Validar que el plato existe
                Plato plato = platoRepository.findById(detalleRequest.getPlatoId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Plato no encontrado con ID: " + detalleRequest.getPlatoId()));

                // Crear el detalle
                DetallePedido detalle = detallePedidoMapper.toEntity(detalleRequest);
                detalle.setPedido(pedido);
                detalle.setPlato(plato);
                detalle.setPrecioUnitario(plato.getPrecio()); // ✅ Tomar precio del plato
                detalle.calcularSubtotal();
                detallePedidoRepository.save(detalle);
            }
        }

        // Forzar flush de la transacción para asegurar que los detalles se guarden
        pedidoRepository.flush();

        // Forzar flush para asegurar que los detalles se guarden
        pedidoRepository.flush();

        // Recargar el pedido con los detalles usando findById normal
        Pedido pedidoCompleto = pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        // Cargar los detalles manualmente
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedido.getId());

        // Limpiar la colección existente y agregar los nuevos detalles
        pedidoCompleto.getDetalles().clear();
        pedidoCompleto.getDetalles().addAll(detalles);

        // Calcular el total manualmente sumando los subtotales de los detalles
        BigDecimal totalCalculado = BigDecimal.ZERO;
        System.out.println("DEBUG: Número de detalles cargados: " + pedidoCompleto.getDetalles().size());
        for (DetallePedido detalle : pedidoCompleto.getDetalles()) {
            System.out.println("DEBUG: Detalle - Cantidad: " + detalle.getCantidad() +
                    ", Precio: " + detalle.getPrecioUnitario() +
                    ", Subtotal: " + detalle.getSubtotal());
            totalCalculado = totalCalculado.add(detalle.getSubtotal());
        }
        System.out.println("DEBUG: Total calculado: " + totalCalculado);
        pedidoCompleto.setTotal(totalCalculado);
        pedidoCompleto = pedidoRepository.save(pedidoCompleto);

        // Forzar flush final para asegurar que todo se guardó
        pedidoRepository.flush();
        detallePedidoRepository.flush();

        PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedidoCompleto);

        // Log importante para verificar que el pedido se guardó
        System.out.println("✅ PEDIDO GUARDADO EXITOSAMENTE:");
        System.out.println("   ID: " + pedidoDTO.getId());
        System.out.println("   UsuarioId: " + pedidoDTO.getUsuarioId());
        System.out.println("   Estado: " + pedidoDTO.getEstado());
        System.out.println("   Total: " + pedidoDTO.getTotal());
        System.out.println("   Detalles: " + (pedidoDTO.getDetalles() != null ? pedidoDTO.getDetalles().size() : 0));

        // Notificar al admin sobre el nuevo pedido
        try {
            messagingTemplate.convertAndSend("/topic/admin/pedidos", pedidoDTO);
            System.out.println("DEBUG: Notificación WebSocket enviada a /topic/admin/pedidos");
        } catch (Exception e) {
            System.err.println("ERROR: No se pudo enviar notificación WebSocket: " + e.getMessage());
        }

        return pedidoDTO;
    }

    @Override
    public PedidoDTO updatePedido(Long id, UpdatePedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));

        // Actualizar solo los campos que vienen en el request
        if (request.getEstado() != null) {
            pedido.actualizarEstado(request.getEstado());
        }
        if (request.getDireccionEntrega() != null) {
            pedido.setDireccionEntrega(request.getDireccionEntrega());
        }
        if (request.getInstruccionesEntrega() != null) {
            pedido.setInstruccionesEntrega(request.getInstruccionesEntrega());
        }
        if (request.getTelefonoContacto() != null) {
            pedido.setTelefonoContacto(request.getTelefonoContacto());
        }
        if (request.getMetodoPago() != null) {
            pedido.setMetodoPago(request.getMetodoPago());
        }
        if (request.getCambioPara() != null) {
            pedido.setCambioPara(request.getCambioPara());
        }

        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    @Override
    public void deletePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
        pedidoRepository.delete(pedido);
    }

    @Override
    public PedidoDTO actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));

        pedido.actualizarEstado(nuevoEstado);
        pedido = pedidoRepository.save(pedido);

        return pedidoMapper.toDTO(pedido);
    }
}
