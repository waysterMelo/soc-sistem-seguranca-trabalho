package com.ocupacional.soc.Mapper.EpiMappers;


import com.ocupacional.soc.Dto.EpiDto.EpiFabricanteDTO;
import com.ocupacional.soc.Entities.Epi.EpiFabricanteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpiFabricanteMapper {
    EpiFabricanteEntity toEntity(EpiFabricanteDTO dto);
    EpiFabricanteDTO toDto(EpiFabricanteEntity entity);
}