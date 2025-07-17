package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {EnderecoMapper.class, CnaeMapper.class, PrestadorServicoMapper.class})
public interface EmpresaMapper {

    // Mapeamento para DTO completo
    @Mapping(source = "cnaePrincipalId", target = "cnaePrincipal")
    @Mapping(source = "medicoResponsavelPcmsso", target = "medicoResponsavel")
    EmpresaDto toEmpresaDto(EmpresaEntity entity);

    // Mapeamento para Entity
    @Mapping(source = "cnaePrincipal.id", target = "cnaePrincipalId")
    @Mapping(source = "medicoResponsavel.id", target = "medicoResponsavelPcmsso")
    EmpresaEntity toEmpresaEntity(EmpresaDto dto);

    EmpresaSimpleResponseDTO toSimpleResponseDto(EmpresaEntity entity);


    default CnaeEntity map(Long cnaeId) {
        if (cnaeId == null) {
            return null;
        }
        CnaeEntity cnaeEntity = new CnaeEntity();
        cnaeEntity.setId(cnaeId);
        return cnaeEntity;
    }

    default PrestadorServicoEntity mapPrestador(Long prestadorId) {
        if (prestadorId == null) {
            return null;
        }
        PrestadorServicoEntity prestadorEntity = new PrestadorServicoEntity();
        prestadorEntity.setId(prestadorId);
        return prestadorEntity;
    }

}