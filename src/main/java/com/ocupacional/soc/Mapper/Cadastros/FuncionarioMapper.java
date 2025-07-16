package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


// Adicione TelefoneMapper.class aos uses se ele não for detectado automaticamente
// e se você tiver um TelefoneMapper.INSTANCE.toEntity() como na versão anterior.
// Se não, o mapeamento de telefones precisará ser feito manualmente ou com um @Named específico.
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, EmpresaMapper.class})
public interface FuncionarioMapper {

    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "mapEmpresaFromIdForFuncionario")
    @Mapping(target = "id", ignore = true)
    FuncionarioEntity requestDtoToEntity(FuncionarioRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa") // EmpresaMapper fará a conversão para EmpresaSimpleResponseDTO
    @Mapping(target = "idade", source = "idade") // Mapeia o getter transiente da entidade
    FuncionarioResponseDTO entityToResponseDto(FuncionarioEntity entity);

    @Mapping(target = "id", ignore = true) // Não se deve alterar o ID na atualização
    @Mapping(target = "empresa", ignore = true) // A mudança de empresa deve ser tratada com cuidado no service se permitida
    @Mapping(target = "idade", ignore = true)
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