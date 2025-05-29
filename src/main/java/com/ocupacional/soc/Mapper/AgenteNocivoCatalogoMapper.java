package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.AgenteNocivoCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Entities.AgenteNocivoCatalogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgenteNocivoCatalogoMapper {
    AgenteNocivoCatalogoMapper INSTANCE = Mappers.getMapper(AgenteNocivoCatalogoMapper.class);

    AgenteNocivoCatalogoSimpleResponseDTO toSimpleResponseDto(AgenteNocivoCatalogoEntity entity);
}