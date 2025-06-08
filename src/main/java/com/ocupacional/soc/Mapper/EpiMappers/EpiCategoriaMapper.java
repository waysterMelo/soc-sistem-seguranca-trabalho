package com.ocupacional.soc.Mapper.EpiMappers;

import com.ocupacional.soc.Dto.EpiDto.EpiCategoriaDTO;
import com.ocupacional.soc.Entities.Epi.EpiCategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpiCategoriaMapper {
    EpiCategoriaEntity toEntity(EpiCategoriaDTO dto);
    EpiCategoriaDTO toDto(EpiCategoriaEntity entity);
}