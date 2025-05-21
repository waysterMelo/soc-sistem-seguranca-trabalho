package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.CboEntity;
import com.ocupacional.soc.Entities.EmpresaEntity;
import com.ocupacional.soc.Entities.FuncaoEntity;
import com.ocupacional.soc.Entities.SetorEntity;
import org.mapstruct.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {RiscoTrabalhistaPgrMapper.class, ProfissionalResponsavelMapper.class, EmpresaMapper.class, SetorMapper.class})
public interface FuncaoMapper {

    FuncaoMapper INSTANCE = Mappers.getMapper(FuncaoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "uuidToEmpresaEntity")
    @Mapping(source = "setorId", target = "setor", qualifiedByName = "uuidToSetorEntity")
    @Mapping(source = "cboId", target = "cbo", qualifiedByName = "uuidToCboEntity")
    @Mapping(target = "riscosPGR", ignore = true)
    @Mapping(target = "profissionaisResponsaveis", ignore = true)
    FuncaoEntity requestDtoToEntity(FuncaoRequestDTO dto);


    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "setor", target = "setor")
    @Mapping(source = "cbo.id", target = "cboId")
    @Mapping(source = "cbo.titulo", target = "nomeCbo")
    @Mapping(source = "riscosPGR", target = "riscosPGR")
    @Mapping(source = "profissionaisResponsaveis", target = "profissionaisResponsaveis")
    FuncaoResponseDTO entityToResponseDTO(FuncaoEntity entity);

    @Named("uuidToEmpresaEntity")
    default EmpresaEntity uuidToEmpresaEntity(UUID id) {
        if (id == null) return null;
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }

    @Named("uuidToSetorEntity")
    default SetorEntity uuidToSetorEntity(UUID id) {
        if (id == null) return null;
        SetorEntity setor = new SetorEntity();
        setor.setId(id);
        return setor;
    }

    @Named("uuidToCboEntity")
    default CboEntity uuidToCboEntity(UUID id) {
        if (id == null) return null;
        CboEntity cbo = new CboEntity();
        cbo.setId(id);
        return cbo;
    }

}
