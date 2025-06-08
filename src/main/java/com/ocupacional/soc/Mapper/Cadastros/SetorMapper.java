package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.SetorRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmpresaMapper.class})
public interface SetorMapper {

    SetorMapper INSTANCE = Mappers.getMapper(SetorMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    SetorEntity toEntity(SetorRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa")
    SetorResponseDTO toResponseDto(SetorEntity entity);

    List<SetorResponseDTO> toResponseDtoList(List<SetorEntity> entities);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    void updateEntityFromDto(SetorRequestDTO dto, @MappingTarget SetorEntity entity);
}
