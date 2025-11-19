package utp.edu.pe.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import utp.edu.pe.restaurante.dto.CategoriaDTO;
import utp.edu.pe.restaurante.dto.request.CreateCategoriaRequest;
import utp.edu.pe.restaurante.dto.request.UpdateCategoriaRequest;
import utp.edu.pe.restaurante.entity.Categoria;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

   
    CategoriaDTO toDTO(Categoria categoria);

    List<CategoriaDTO> toDTOList(List<Categoria> categorias);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "platos", ignore = true)
    Categoria toEntity(CreateCategoriaRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "platos", ignore = true)
    Categoria toEntity(UpdateCategoriaRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "platos", ignore = true)
    void updateEntity(CreateCategoriaRequest request, @MappingTarget Categoria categoria);
}