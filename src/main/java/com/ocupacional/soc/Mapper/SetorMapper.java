package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.SetorRequestDTO;
import com.ocupacional.soc.Dto.SetorResponseDTO;
import com.ocupacional.soc.Entities.SetorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SetorMapper {

    SetorMapper INSTANCE = Mappers.getMapper(SetorMapper.class);

    SetorEntity toEntity(SetorRequestDTO dto);

    SetorResponseDTO toResponseDto(SetorEntity entity);

    List<SetorResponseDTO> toResponseDtoList(List<SetorEntity> entities);

    void updateEntityFromDto(SetorRequestDTO dto, @MappingTarget SetorEntity entity);
}
