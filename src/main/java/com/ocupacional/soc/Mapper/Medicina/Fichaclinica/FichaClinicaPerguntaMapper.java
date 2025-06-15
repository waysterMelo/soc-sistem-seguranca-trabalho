package com.ocupacional.soc.Mapper.Medicina.Fichaclinica;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaPerguntaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FichaClinicaOpcaoPerguntaMapper.class})
public interface FichaClinicaPerguntaMapper {
    FichaClinicaResponseDTO.PerguntaResponseDTO toDto(FichaClinicaPerguntaEntity entity);
}