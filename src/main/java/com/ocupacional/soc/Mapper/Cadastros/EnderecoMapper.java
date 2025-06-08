package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Entities.Cadastros.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "id", target = "id")
    EnderecoEntity toEntity(EnderecoDto dto);

    @Mapping(source = "id", target = "id") // Adicione se EnderecoDto também tiver id
    EnderecoDto toDto(EnderecoEntity entity);
}
