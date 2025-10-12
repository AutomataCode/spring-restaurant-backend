package utp.edu.pe.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import utp.edu.pe.restaurante.dto.PedidoDTO;
import utp.edu.pe.restaurante.dto.DetallePedidoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePedidoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePedidoRequest;
import utp.edu.pe.restaurante.entity.Pedido;
import utp.edu.pe.restaurante.entity.DetallePedido;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "detalles", target = "detalles")
    PedidoDTO toDTO(Pedido pedido);

    
    List<PedidoDTO> toDTOList(List<Pedido> pedidos);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", expression = "java(mapUsuario(request.getUsuarioId()))")
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaPedido", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "fechaConfirmacion", ignore = true)
    @Mapping(target = "fechaPreparacion", ignore = true)
    @Mapping(target = "fechaEnvio", ignore = true)
    @Mapping(target = "fechaEntrega", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    Pedido toEntity(CreatePedidoRequest request);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "tipoPedido", ignore = true)
    @Mapping(target = "fechaPedido", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "fechaConfirmacion", ignore = true)
    @Mapping(target = "fechaPreparacion", ignore = true)
    @Mapping(target = "fechaEnvio", ignore = true)
    @Mapping(target = "fechaEntrega", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    void updateEntity(@MappingTarget Pedido pedido, UpdatePedidoRequest request);

    
    default utp.edu.pe.restaurante.entity.Usuario mapUsuario(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        utp.edu.pe.restaurante.entity.Usuario usuario = new utp.edu.pe.restaurante.entity.Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }

   
    default List<DetallePedidoDTO> mapDetalles(List<DetallePedido> detalles) {
        if (detalles == null) {
            return null;
        }
        return detalles.stream()
                .map(this::detalleToDTO)
                .toList();
    }

    
    @Mapping(source = "pedido.id", target = "pedidoId")
    @Mapping(source = "plato.id", target = "platoId")
    @Mapping(source = "plato.nombre", target = "platoNombre")
    DetallePedidoDTO detalleToDTO(DetallePedido detalle);
}
