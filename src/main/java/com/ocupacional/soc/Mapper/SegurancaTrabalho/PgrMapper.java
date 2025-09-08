package com.ocupacional.soc.Mapper.SegurancaTrabalho;


import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UnidadeOperacionalMapper.class,
        PrestadorServicoMapper.class,
        PgrMapaRiscoFuncaoMapper.class,
        PlanoAcaoRiscoMapper.class
})
public interface PgrMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unidadeOperacional", ignore = true)
    @Mapping(target = "prestadorServico", ignore = true)
    @Mapping(target = "mapaRiscos", ignore = true)
    @Mapping(target = "planoAcaoRiscos", ignore = true)
    PgrEntity toEntity(PgrRequestDTO dto);

    @Mapping(source = "conteudoCapa", target = "conteudoCapa")
    @Mapping(source = "mapaRiscos", target = "mapaRiscos")
    @Mapping(source = "planoAcaoRiscos", target = "planoAcaoRiscos")
    @Mapping(source = "prestadorServico", target = "prestadorServico")
    PgrResponseDTO toDto(PgrEntity entity);
}