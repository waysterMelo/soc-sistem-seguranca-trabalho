package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
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