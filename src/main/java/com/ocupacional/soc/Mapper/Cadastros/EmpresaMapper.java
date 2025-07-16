package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, CnaeMapper.class})
public interface EmpresaMapper {

    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    EmpresaSimpleResponseDTO toSimpleResponseDto(EmpresaEntity entity);

    @Mapping(source = "medicoResponsavelPcmsso.id", target = "medicoResponsavelPcmssoId")
    @Mapping(source = "cnaePrincipalId", target = "cnaePrincipalId", qualifiedByName = "cnaeToId")
    EmpresaDto toEmpresaDto(EmpresaEntity empresaEntity);

    @Mapping(source = "cnaePrincipalId", target = "cnaePrincipalId", qualifiedByName = "idToCnae")
    @Mapping(source = "endereco", target = "endereco")
    @Mapping(source = "medicoResponsavelPcmssoId", target = "medicoResponsavelPcmsso", qualifiedByName = "idToPrestadorServico")
    EmpresaEntity toEmpresaEntity(EmpresaDto empresaDto);

    @Named("idToPrestadorServico")
    default PrestadorServicoEntity idToPrestadorServico(Long id) {
        if (id == null) {
            return null;
        }
        PrestadorServicoEntity prestador = new PrestadorServicoEntity();
        prestador.setId(id);
        return prestador;
    }



    @Named("cnaeToId")
    default Long cnaeToId(CnaeEntity cnae) {
        if (cnae == null) {
            return null;
        }
        return cnae.getId();
    }

    @Named("idToCnae")
    default CnaeEntity idToCnae(Long id) {
        if (id == null) {
            return null;
        }
        CnaeEntity cnae = new CnaeEntity();
        cnae.setId(id);
        return cnae;
    }


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
