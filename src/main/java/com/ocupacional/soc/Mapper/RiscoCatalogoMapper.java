package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Dto.RiscoCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Entities.RiscoCatalogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RiscoCatalogoMapper {
    RiscoCatalogoMapper INSTANCE = Mappers.getMapper(RiscoCatalogoMapper.class);

    RiscoCatalogoResponseDTO toResponseDTO(RiscoCatalogoEntity entity);
    RiscoCatalogoSimpleResponseDTO toSimpleResponseDTO(RiscoCatalogoEntity entity);
    List<RiscoCatalogoResponseDTO> toResponseDTOList(List<RiscoCatalogoEntity> entities);

    @Mapping(target = "id", ignore = true)
    RiscoCatalogoEntity requestDtoToEntity(RiscoCatalogoRequestDTO dto);
}