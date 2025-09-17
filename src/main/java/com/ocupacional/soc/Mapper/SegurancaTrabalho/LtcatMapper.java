package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Ltcat.LtcatEntity;
import com.ocupacional.soc.Mapper.Cadastros.*;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        LtcatAgenteNocivoMapper.class,
        ProfissionalResponsavelMapper.class,
        PrestadorServicoMapper.class,
        AparelhoMapper.class,
        BibliografiaMapper.class,
        FuncaoMapper.class,
        EmpresaMapper.class,UnidadeOperacionalMapper.class
})
public interface LtcatMapper {

    @Mapping(source = "unidadeOperacional", target = "unidadeOperacional")
    LtcatResponseDTO toDto(LtcatEntity entity);

    @Mapping(source = "unidadeOperacional.nome", target = "nomeUnidadeOperacional")
    @Mapping(source = "unidadeOperacional.empresa.nomeFantasia", target = "nomeEmpresa")
    LtcatListDTO toListDto(LtcatEntity entity);
}