package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrMapaRiscoFuncaoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrMapaRiscoFuncaoEntity;
import com.ocupacional.soc.Mapper.Cadastros.RiscoCatalogoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RiscoCatalogoMapper.class})
public interface PgrMapaRiscoFuncaoMapper {
    @Mapping(source = "funcao.id", target = "funcaoId")
    @Mapping(source = "funcao.nome", target = "nomeFuncao")
    @Mapping(source = "riscosDoCatalogo", target = "riscosDoCatalogo")
    @Mapping(source = "riscosPersonalizados", target = "riscosPersonalizados")
    PgrMapaRiscoFuncaoResponseDTO toDto(PgrMapaRiscoFuncaoEntity entity);
}