package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.FuncionarioResponseDTO;
import com.ocupacional.soc.Entities.EmpresaEntity;
import com.ocupacional.soc.Entities.FuncionarioEntity;
import com.ocupacional.soc.Entities.TelefoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import java.util.ArrayList;
import java.util.List; // Necessário para o mapeamento de telefones
import java.util.stream.Collectors; // Para o mapeamento de telefones na atualização


// Adicione TelefoneMapper.class aos uses se ele não for detectado automaticamente
// e se você tiver um TelefoneMapper.INSTANCE.toEntity() como na versão anterior.
// Se não, o mapeamento de telefones precisará ser feito manualmente ou com um @Named específico.
@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, EmpresaMapper.class, TelefoneMapper.class})
public interface FuncionarioMapper {

    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "mapEmpresaFromIdForFuncionario")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telefones", ignore = true) // Será tratado no AfterMapping
    FuncionarioEntity requestDtoToEntity(FuncionarioRequestDTO dto);

    @Mapping(source = "empresa", target = "empresa") // EmpresaMapper fará a conversão para EmpresaSimpleResponseDTO
    @Mapping(target = "idade", source = "idade") // Mapeia o getter transiente da entidade
    FuncionarioResponseDTO entityToResponseDto(FuncionarioEntity entity);

    @Mapping(target = "id", ignore = true) // Não se deve alterar o ID na atualização
    @Mapping(target = "empresa", ignore = true) // A mudança de empresa deve ser tratada com cuidado no service se permitida
    @Mapping(target = "telefones", ignore = true) // Tratar telefones separadamente na atualização
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

    // Este AfterMapping ajuda a configurar a relação bidirecional para telefones na criação.
    // Na atualização, a lógica de limpar e adicionar telefones é melhor tratada no service.
    @AfterMapping
    default void linkTelefonesOnCreate(@MappingTarget FuncionarioEntity entity, FuncionarioRequestDTO dto) {
        // Garante que a lista de telefones da entidade não seja nula
        if (entity.getTelefones() == null) {
            entity.setTelefones(new ArrayList<>());
        } else {
            // Se for um reuso do builder ou um mapeamento inesperado, limpa antes.
            // Para criação, geralmente a lista já estará vazia ou será nova.
            entity.getTelefones().clear();
        }

        if (dto.getTelefones() != null && TelefoneMapper.INSTANCE != null) {
            dto.getTelefones().forEach(telDto -> {
                TelefoneEntity telEntity = TelefoneMapper.INSTANCE.toEntity(telDto);
                telEntity.setFuncionario(entity); // Configura a referência de volta para o funcionário
                entity.getTelefones().add(telEntity);
            });
        }
    }
}