package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoTrabalhistaPgrEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RiscoCatalogoMapper.class})
public interface RiscoTrabalhistaPgrMapper {

    RiscoTrabalhistaPgrMapper INSTANCE = Mappers.getMapper(RiscoTrabalhistaPgrMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funcao", ignore = true) // Será setada no FuncaoServiceImpl
    @Mapping(source = "riscoCatalogoId", target = "riscoCatalogo", qualifiedByName = "idToRiscoCatalogoEntity")
    RiscoTrabalhistaPgrEntity toEntity(RiscoTrabalhistaPgrRequestDTO dto);

    // Mapeia RiscoCatalogoEntity para RiscoCatalogoSimpleResponseDTO usando RiscoCatalogoMapper
    @Mapping(source = "riscoCatalogo", target = "riscoCatalogo")
    RiscoTrabalhistaPgrResponseDTO toResponseDTO(RiscoTrabalhistaPgrEntity entity);

    @Named("idToRiscoCatalogoEntity")
    default RiscoCatalogoEntity idToRiscoCatalogoEntity(Long id) {
        if (id == null) {
            return null;
        }
        RiscoCatalogoEntity riscoCatalogo = new RiscoCatalogoEntity();
        riscoCatalogo.setId(id);
        return riscoCatalogo;
    }
}