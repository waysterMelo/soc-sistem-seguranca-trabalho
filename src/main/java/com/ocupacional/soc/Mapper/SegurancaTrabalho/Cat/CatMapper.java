package com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatResponseDTO;
import com.ocupacional.soc.Entities.Cat.CatEntity;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        EnderecoMapper.class,
        FuncionarioMapper.class,
        PrestadorServicoMapper.class,
        CatalogoCatMapper.class, // Para os outros catálogos
        CidMapper.class          // <-- Adicionado para o mapeamento do CID
})
public interface CatMapper {

    @Mapping(source = "acidentado", target = "acidentado")
    @Mapping(source = "localAcidenteEndereco", target = "localAcidenteEndereco")
    @Mapping(source = "partesCorpoAtingidas", target = "partesCorpoAtingidas")
    @Mapping(source = "agenteCausador", target = "agenteCausador")
    @Mapping(source = "naturezaLesao", target = "naturezaLesao")
    @Mapping(source = "situacaoGeradora", target = "situacaoGeradora")
    @Mapping(source = "cid", target = "cid")
    @Mapping(source = "atestadoMedico", target = "atestadoMedico")
    CatResponseDTO toDto(CatEntity entity);
}