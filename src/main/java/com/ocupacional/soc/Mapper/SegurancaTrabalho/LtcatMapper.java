package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatEntity;
import com.ocupacional.soc.Mapper.Cadastros.*;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import com.ocupacional.soc.Mapper.PrestadorServiços.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LtcatAgenteNocivoMapper.class,
        ProfissionalResponsavelMapper.class,
        PrestadorServicoMapper.class,
        AparelhoMapper.class,
        BibliografiaMapper.class,
        FuncaoMapper.class,
        EmpresaMapper.class
})
public interface LtcatMapper {

    @Mapping(source = "unidadeOperacional.id", target = "unidadeOperacionalId")
    @Mapping(source = "unidadeOperacional.nome", target = "nomeUnidadeOperacional")
    LtcatResponseDTO toDto(LtcatEntity entity);
}