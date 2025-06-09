package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnidadeOperacionalMapper.class, PgrMapaRiscoFuncaoMapper.class})
public interface PgrMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unidadeOperacional", ignore = true)
    @Mapping(target = "mapaRiscos", ignore = true)
    PgrEntity toEntity(PgrRequestDTO dto);

    PgrResponseDTO toDto(PgrEntity entity);
}