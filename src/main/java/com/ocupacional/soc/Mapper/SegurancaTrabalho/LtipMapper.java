package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip.LtipResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip.LtipEntity;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoMapper;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        FuncaoMapper.class,
        PrestadorServicoMapper.class,
        AparelhoMapper.class,
        BibliografiaMapper.class,
        Nr16AnexoMapper.class,
        LtipNr16AnexoMapper.class,
        EmpresaMapper.class
})
public interface LtipMapper {

    @Mapping(source = "funcao", target = "funcao")
    @Mapping(source = "responsavelTecnico", target = "responsavelTecnico")
    @Mapping(source = "demaisElaboradores", target = "demaisElaboradores")
    @Mapping(source = "atividadesPericulosasAnexos", target = "atividadesPericulosasAnexos")
    @Mapping(source = "aparelhos", target = "aparelhos")
    LtipResponseDTO toDto(LtipEntity entity);
}