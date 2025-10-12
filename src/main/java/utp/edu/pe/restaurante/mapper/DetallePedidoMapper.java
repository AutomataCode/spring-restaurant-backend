package utp.edu.pe.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreateDetallePedidoRequest;
import utp.edu.pe.restaurante.entity.DetallePedido;
import utp.edu.pe.restaurante.entity.Pedido;
import utp.edu.pe.restaurante.entity.Plato;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    
    @Mapping(source = "pedido.id", target = "pedidoId")
    @Mapping(source = "plato.id", target = "platoId")
    @Mapping(source = "plato.nombre", target = "platoNombre")
    DetallePedidoDTO toDTO(DetallePedido detalle);

    
    List<DetallePedidoDTO> toDTOList(List<DetallePedido> detalles);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "plato", expression = "java(mapPlato(request.getPlatoId()))")
    @Mapping(target = "subtotal", ignore = true)
    DetallePedido toEntity(CreateDetallePedidoRequest request);

    
    default Plato mapPlato(Long platoId) {
        if (platoId == null) {
            return null;
        }
        Plato plato = new Plato();
        plato.setId(platoId);
        return plato;
    }

    
    default Pedido mapPedido(Long pedidoId) {
        if (pedidoId == null) {
            return null;
        }
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        return pedido;
    }
}
