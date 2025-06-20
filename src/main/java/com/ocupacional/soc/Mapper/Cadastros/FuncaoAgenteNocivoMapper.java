package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoAgenteNocivoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoAgenteNocivoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.AgenteNocivoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoAgenteNocivoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AgenteNocivoCatalogoMapper.class})
public interface FuncaoAgenteNocivoMapper {
    FuncaoAgenteNocivoMapper INSTANCE = Mappers.getMapper(FuncaoAgenteNocivoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funcao", ignore = true) // Será setada no service
    @Mapping(source = "agenteNocivoCatalogoId", target = "agenteNocivoCatalogo", qualifiedByName = "idToAgenteNocivoCatalogoEntity")
    FuncaoAgenteNocivoEntity requestDtoToEntity(FuncaoAgenteNocivoRequestDTO dto);

    @Mapping(source = "agenteNocivoCatalogo", target = "agenteNocivoCatalogo") // Usa AgenteNocivoCatalogoMapper
    FuncaoAgenteNocivoResponseDTO entityToResponseDto(FuncaoAgenteNocivoEntity entity);

    @Named("idToAgenteNocivoCatalogoEntity")
    default AgenteNocivoCatalogoEntity idToAgenteNocivoCatalogoEntity(Long id) {
        if (id == null) {
            return null;
        }
        AgenteNocivoCatalogoEntity agente = new AgenteNocivoCatalogoEntity();
        agente.setId(id);
        return agente;
    }
}