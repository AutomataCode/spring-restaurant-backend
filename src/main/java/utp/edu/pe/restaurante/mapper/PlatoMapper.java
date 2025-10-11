package utp.edu.pe.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.restaurante.dto.PlatoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePlatoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePlatoRequest;
import utp.edu.pe.restaurante.entity.Plato;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlatoMapper {

    PlatoMapper INSTANCE = Mappers.getMapper(PlatoMapper.class);

    /**
     * Convierte una entidad Plato a PlatoDTO
     */
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    PlatoDTO toDTO(Plato plato);

    /**
     * Convierte una lista de entidades Plato a lista de PlatoDTO
     */
    List<PlatoDTO> toDTOList(List<Plato> platos);

    /**
     * Convierte CreatePlatoRequest a entidad Plato
     * Nota: categoriaId se maneja por separado en el servicio
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Plato toEntity(CreatePlatoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Plato toEntity(UpdatePlatoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(CreatePlatoRequest request, @MappingTarget Plato plato);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    void updateEntity(UpdatePlatoRequest request, @MappingTarget Plato plato);
}
