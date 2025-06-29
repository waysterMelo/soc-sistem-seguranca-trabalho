package com.ocupacional.soc.Mapper.Cadastros;


import com.ocupacional.soc.Dto.Cadastros.AgenteNocivoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.AgenteNocivoCatalogoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.AgenteNocivoCatalogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AgenteNocivoCatalogoMapper {
    AgenteNocivoCatalogoMapper INSTANCE = Mappers.getMapper(AgenteNocivoCatalogoMapper.class);

    AgenteNocivoCatalogoResponseDTO toResponseDto(AgenteNocivoCatalogoEntity entity);

    List<AgenteNocivoCatalogoResponseDTO> toResponseDtoList(List<AgenteNocivoCatalogoEntity> entities);

    @Mapping(target = "id", ignore = true) // Ignora o ID ao criar uma nova entidade a partir do DTO
    AgenteNocivoCatalogoEntity requestDtoToEntity(AgenteNocivoCatalogoRequestDTO dto);

    @Mapping(target = "id", ignore = true) // Não atualiza o ID
    void updateEntityFromDto(AgenteNocivoCatalogoRequestDTO dto, @MappingTarget AgenteNocivoCatalogoEntity entity);
}