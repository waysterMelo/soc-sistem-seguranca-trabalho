package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.TelefoneDto;
import com.ocupacional.soc.Entities.Cadastros.TelefoneEntity;
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
