package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CnaeMapper.class, SetorMapper.class, EmpresaMapper.class})
public interface UnidadeOperacionalMapper {

    UnidadeOperacionalMapper INSTANCE = Mappers.getMapper(UnidadeOperacionalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "mapEmpresaFromId")
    @Mapping(source = "cnaePrincipalId", target = "cnaePrincipal", qualifiedByName = "mapCnaeFromId")
    @Mapping(source = "setoresIds", target = "setores", qualifiedByName = "mapSetoresFromIds")
    UnidadeOperacionalEntity toEntity(UnidadeOperacionalRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "cnaePrincipal", target = "cnaePrincipal")
    @Mapping(source = "setores", target = "setores")
    UnidadeOperacionalResponseDTO toResponseDto(UnidadeOperacionalEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    @Mapping(source = "cnaePrincipalId", target = "cnaePrincipal", qualifiedByName = "mapCnaeFromId")
    @Mapping(source = "setoresIds", target = "setores", qualifiedByName = "mapSetoresFromIds")
    void updateEntityFromDto(UnidadeOperacionalRequestDTO dto, @MappingTarget UnidadeOperacionalEntity entity);

    List<UnidadeOperacionalResponseDTO> toResponseDto(List<UnidadeOperacionalEntity> entities);


    @Named("mapEmpresaFromId")
    default EmpresaEntity mapEmpresaFromId(Long id) {
        if (id == null) {
            return null;
        }
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }

    @Named("mapCnaeFromId")
    default CnaeEntity mapCnaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CnaeEntity cnae = new CnaeEntity();
        cnae.setId(id);
        return cnae;
    }

    @Named("mapCnaesFromIds")
    default List<CnaeEntity> mapCnaesFromIds(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream().map(id -> {
            CnaeEntity cnae = new CnaeEntity();
            cnae.setId(id);
            return cnae;
        }).collect(Collectors.toList());
    }

    @Named("mapSetoresFromIds")
    default List<SetorEntity> mapSetoresFromIds(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream().map(id -> {
            SetorEntity setor = new SetorEntity();
            setor.setId(id);
            return setor;
        }).collect(Collectors.toList());
    }
}