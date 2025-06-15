package com.ocupacional.soc.Mapper.Medicina.Fichaclinica;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaOpcaoPerguntaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FichaClinicaOpcaoPerguntaMapper {
    FichaClinicaResponseDTO.OpcaoResponseDTO toDto(FichaClinicaOpcaoPerguntaEntity entity);
}