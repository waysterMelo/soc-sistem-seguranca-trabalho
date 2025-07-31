package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, EmpresaMapper.class})
public interface FuncionarioMapper {

    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "mapEmpresaFromIdForFuncionario")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "idade", target = "idade")
    FuncionarioEntity requestDtoToEntity(FuncionarioRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa")
    @Mapping(target = "idade", source = "idade")
    @Mapping(source = "telefone", target = "telefone")
    FuncionarioResponseDTO entityToResponseDto(FuncionarioEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    @Mapping(source = "telefone", target = "telefone")
    void updateEntityFromDto(FuncionarioRequestDTO dto, @MappingTarget FuncionarioEntity entity);

    @Named("mapEmpresaFromIdForFuncionario")
    default EmpresaEntity mapEmpresaFromIdForFuncionario(Long id) {
        if (id == null) {
            return null;
        }
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }
}