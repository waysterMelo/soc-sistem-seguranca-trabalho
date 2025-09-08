package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PlanoAcaoRiscoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PlanoAcaoRiscoEntity;
import com.ocupacional.soc.Mapper.Cadastros.RiscoCatalogoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RiscoCatalogoMapper.class})
public interface PlanoAcaoRiscoMapper {

    @Mapping(source = "risco", target = "risco")
    PlanoAcaoRiscoResponseDTO toDto(PlanoAcaoRiscoEntity entity);
}