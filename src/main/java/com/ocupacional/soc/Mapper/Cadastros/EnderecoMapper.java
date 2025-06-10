package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Entities.Cadastros.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "id", ignore = true)
    EnderecoEntity toEntity(EnderecoDto dto);


    @Mapping(target = "id", source = "id")
    EnderecoDto toDto(EnderecoEntity entity);
}
