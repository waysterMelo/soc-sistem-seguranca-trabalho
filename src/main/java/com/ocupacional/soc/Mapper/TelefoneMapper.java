package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.TelefoneDto;
import com.ocupacional.soc.Entities.TelefoneEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {


    TelefoneMapper INSTANCE = Mappers.getMapper(TelefoneMapper.class);

    TelefoneDto toDto(TelefoneEntity entity);

    TelefoneEntity toEntity(TelefoneDto dto);

    List<TelefoneDto> toDtoList(List<TelefoneEntity> entities);

    List<TelefoneEntity> toEntityList(List<TelefoneDto> dtos);
}
