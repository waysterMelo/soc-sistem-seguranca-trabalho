package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoSimplificadaDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, EmpresaMapper.class})
public abstract class FuncionarioMapper {

    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "mapEmpresaFromIdForFuncionario")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "idade", target = "idade")
    @Mapping(target = "funcao", source = "funcaoId", qualifiedByName = "mapFuncaoFromId")
    public abstract FuncionarioEntity requestDtoToEntity(FuncionarioRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa")
    @Mapping(target = "idade", source = "idade")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(target = "funcao", source = "funcao", qualifiedByName = "funcaoToFuncaoSimplificadaDTO")
    public abstract FuncionarioResponseDTO entityToResponseDto(FuncionarioEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(target = "funcao", ignore = true)
    public abstract void updateEntityFromDto(FuncionarioRequestDTO dto, @MappingTarget FuncionarioEntity entity);

    @Named("mapEmpresaFromIdForFuncionario")
    protected EmpresaEntity mapEmpresaFromIdForFuncionario(Long id) {
        if (id == null) {
            return null;
        }
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }

    @Named("mapFuncaoFromId")
    protected FuncaoEntity mapFuncaoFromId(Long funcaoId) {
        if (funcaoId == null) {
            return null;
        }
        FuncaoEntity funcao = new FuncaoEntity();
        funcao.setId(funcaoId);
        return funcao;
    }

    @Named("funcaoToFuncaoSimplificadaDTO")
    protected FuncaoSimplificadaDTO funcaoToFuncaoSimplificadaDTO(FuncaoEntity funcao) {
        if (funcao == null) {
            return null;
        }
        return FuncaoSimplificadaDTO.builder()
                .id(funcao.getId())
                .nome(funcao.getNome())
                .descricao(funcao.getDescricao())
                .build();
    }
}