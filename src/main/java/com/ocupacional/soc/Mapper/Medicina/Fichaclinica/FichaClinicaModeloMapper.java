package com.ocupacional.soc.Mapper.Medicina.Fichaclinica;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloListDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaModeloEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FichaClinicaQuadroMapper.class})
public interface FichaClinicaModeloMapper {

    // Para a resposta detalhada de um único modelo
    FichaClinicaResponseDTO toResponseDto(FichaClinicaModeloEntity entity);

    // Para a lista de todos os modelos
    FichaClinicaModeloListDTO toListDto(FichaClinicaModeloEntity entity);

    List<FichaClinicaModeloListDTO> toListDto(List<FichaClinicaModeloEntity> entities);
}