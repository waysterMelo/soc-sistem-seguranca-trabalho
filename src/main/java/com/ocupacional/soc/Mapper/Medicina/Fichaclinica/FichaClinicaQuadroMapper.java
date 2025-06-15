package com.ocupacional.soc.Mapper.Medicina.Fichaclinica;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaQuadroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FichaClinicaPerguntaMapper.class})
public interface FichaClinicaQuadroMapper {
    FichaClinicaResponseDTO.QuadroResponseDTO toDto(FichaClinicaQuadroEntity entity);
}
