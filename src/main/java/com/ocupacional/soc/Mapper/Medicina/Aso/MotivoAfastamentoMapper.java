package com.ocupacional.soc.Mapper.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Aso.MotivoAfastamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MotivoAfastamentoMapper {

    MotivoAfastamentoEntity toEntity(MotivoAfastamentoRequestDTO dto);

    MotivoAfastamentoResponseDTO toResponseDto(MotivoAfastamentoEntity entity);

    void updateEntityFromDto(MotivoAfastamentoRequestDTO dto, @MappingTarget MotivoAfastamentoEntity entity);
}