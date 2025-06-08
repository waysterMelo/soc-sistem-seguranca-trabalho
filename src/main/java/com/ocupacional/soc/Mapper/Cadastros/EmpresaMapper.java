package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, CnaeMapper.class, TelefoneMapper.class}) // Adicione TelefoneMapper se necessário
public interface EmpresaMapper {

    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    EmpresaSimpleResponseDTO toSimpleResponseDto(EmpresaEntity entity);

    @Mapping(source = "endereco", target = "endereco") // Mapeado por EnderecoMapper
    @Mapping(source = "telefones", target = "telefones") // Mapeado por TelefoneMapper
    @Mapping(source = "cnaePrincipal.id", target = "cnaePrincipalId")
    @Mapping(source = "cnaesSecundarios", target = "cnaesSecundariosIds", qualifiedByName = "cnaesToIds")
    @Mapping(source = "empresaMatriz.id", target = "empresaMatrizId")
    @Mapping(source = "medicoResponsavelPcmsso.id", target = "medicoResponsavelPcmssoId")
    EmpresaDto toEmpresaDto(EmpresaEntity empresaEntity);

    @Mapping(source = "endereco", target = "endereco") // Mapeado por EnderecoMapper
    @Mapping(source = "telefones", target = "telefones") // Mapeado por TelefoneMapper
    @Mapping(target = "cnaePrincipal", ignore = true) // Ignorar para preencher manualmente no serviço (buscar por cnaePrincipalId)
    @Mapping(target = "cnaesSecundarios", ignore = true) // Ignorar para preencher manualmente no serviço (buscar por cnaesSecundariosIds)
    @Mapping(target = "empresaMatriz", ignore = true) // Ignorar para preencher manualmente no serviço (buscar por empresaMatrizId)
    @Mapping(target = "medicoResponsavelPcmsso", ignore = true) // Ignorar para preencher manualmente no serviço (buscar por medicoResponsavelPcmssoId)
    EmpresaEntity toEmpresaEntity(EmpresaDto empresaDto);


    @Named("cnaesToIds")
    default List<Long> cnaesToIds(List<CnaeEntity> cnaes) {
        if (cnaes == null) {
            return null;
        }
        return cnaes.stream()
                .map(CnaeEntity::getId)
                .collect(Collectors.toList());
    }

}
