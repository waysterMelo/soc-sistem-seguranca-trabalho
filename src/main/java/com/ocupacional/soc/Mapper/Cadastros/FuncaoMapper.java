package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CboEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring", uses = {
        RiscoTrabalhistaPgrMapper.class,
        ProfissionalResponsavelMapper.class,
        EmpresaMapper.class,
        SetorMapper.class,
        FuncaoAgenteNocivoMapper.class, // Novo
        FuncaoExamePcmsoMapper.class    // Novo
})
public interface FuncaoMapper {

    FuncaoMapper INSTANCE = Mappers.getMapper(FuncaoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "longIdToEmpresaEntity") // Renomeado para clareza
    @Mapping(source = "setorId", target = "setor", qualifiedByName = "longIdToSetorEntity")       // Renomeado para clareza
    @Mapping(source = "cboId", target = "cbo", qualifiedByName = "longIdToCboEntity")             // Renomeado para clareza
    @Mapping(source = "descricaoFuncao", target = "descricao") // Mapeamento explícito
    @Mapping(target = "riscosPGR", ignore = true) // Processado no service
    @Mapping(target = "profissionaisResponsaveis", ignore = true) // Processado no service
    @Mapping(target = "agentesNocivosEsocial", ignore = true) // Processado no service
    @Mapping(target = "examesPcmso", ignore = true) // Processado no service
    FuncaoEntity requestDtoToEntity(FuncaoRequestDTO dto);


    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "setor", target = "setor")
    @Mapping(source = "cbo.id", target = "cboId")
    @Mapping(source = "cbo.nomeOcupacao", target = "nomeCbo")
    @Mapping(source = "descricao", target = "descricaoFuncao") // Mapeamento explícito
    @Mapping(source = "riscosPGR", target = "riscosPGR") // Usa RiscoTrabalhistaPgrMapper
    @Mapping(source = "profissionaisResponsaveis", target = "profissionaisResponsaveis") // Usa ProfissionalResponsavelMapper
    @Mapping(source = "agentesNocivosEsocial", target = "agentesNocivosEsocial") // Usa FuncaoAgenteNocivoMapper
    @Mapping(source = "examesPcmso", target = "examesPcmso") // Usa FuncaoExamePcmsoMapper
    FuncaoResponseDTO entityToResponseDTO(FuncaoEntity entity);

    @Named("longIdToEmpresaEntity") // Renomeado para clareza
    default EmpresaEntity longIdToEmpresaEntity(Long id) {
        if (id == null) return null;
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }

    @Named("longIdToSetorEntity") // Renomeado para clareza
    default SetorEntity longIdToSetorEntity(Long id) {
        if (id == null) return null;
        SetorEntity setor = new SetorEntity();
        setor.setId(id);
        return setor;
    }

    @Named("longIdToCboEntity") // Renomeado para clareza
    default CboEntity longIdToCboEntity(Long id) {
        if (id == null) return null;
        CboEntity cbo = new CboEntity();
        cbo.setId(id);
        return cbo;
    }
}