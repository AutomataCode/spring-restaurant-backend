package utp.edu.pe.restaurante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreateDetallePedidoRequest;
import utp.edu.pe.restaurante.entity.DetallePedido;
import utp.edu.pe.restaurante.entity.Pedido;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.exception.ResourceNotFoundException;
import utp.edu.pe.restaurante.mapper.DetallePedidoMapper;
import utp.edu.pe.restaurante.repository.DetallePedidoRepository;
import utp.edu.pe.restaurante.repository.PedidoRepository;
import utp.edu.pe.restaurante.repository.PlatoRepository;
import utp.edu.pe.restaurante.service.DetallePedidoService;

import java.util.List;

@Service
@Transactional
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> getDetallesByPedido(Long pedidoId) {
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedidoId);
        return detallePedidoMapper.toDTOList(detalles);
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePedidoDTO getDetalleById(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + id));
        return detallePedidoMapper.toDTO(detalle);
    }

    @Override
    public DetallePedidoDTO createDetalle(Long pedidoId, CreateDetallePedidoRequest request) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + pedidoId));

        Plato plato = platoRepository.findById(request.getPlatoId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Plato no encontrado con ID: " + request.getPlatoId()));

        DetallePedido detalle = detallePedidoMapper.toEntity(request);
        detalle.setPedido(pedido);
        detalle.setPlato(plato);
        detalle.calcularSubtotal();
        detalle = detallePedidoRepository.save(detalle);

        pedido.calcularTotal();
        pedidoRepository.save(pedido);

        return detallePedidoMapper.toDTO(detalle);
    }

    @Override
    public DetallePedidoDTO actualizarCantidad(Long id, Integer nuevaCantidad) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + id));

        detalle.actualizarCantidad(nuevaCantidad);
        detalle = detallePedidoRepository.save(detalle);

        return detallePedidoMapper.toDTO(detalle);
    }

    @Override
    public void deleteDetalle(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + id));

        Pedido pedido = detalle.getPedido();

        detallePedidoRepository.delete(detalle);

        if (pedido != null) {
            pedido.calcularTotal();
            pedidoRepository.save(pedido);
        }
    }
}
