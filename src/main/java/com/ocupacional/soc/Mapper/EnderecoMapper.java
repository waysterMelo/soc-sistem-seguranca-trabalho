package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.EnderecoDto;
import com.ocupacional.soc.Entities.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "id", target = "id")
    EnderecoEntity toEntity(EnderecoDto dto);

    @Mapping(source = "id", target = "id") // Adicione se EnderecoDto também tiver id
    EnderecoDto toDto(EnderecoEntity entity);
}
