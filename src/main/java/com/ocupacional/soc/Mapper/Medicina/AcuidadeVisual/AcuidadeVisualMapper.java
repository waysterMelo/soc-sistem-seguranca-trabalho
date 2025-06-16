package com.ocupacional.soc.Mapper.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualRequestDTO;
import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualResponseDTO;
import com.ocupacional.soc.Entities.Medicina.AcuidadeVisual.AcuidadeVisualEntity;
import com.ocupacional.soc.Mapper.Medicina.Aso.RegistroProfissionalMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        RegistroProfissionalMapper.class,
        PrestadorServicoMapper.class
})
public interface AcuidadeVisualMapper {

    AcuidadeVisualResponseDTO toDto(AcuidadeVisualEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "excluido", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "registroProfissional", ignore = true) // Será definido no service
    @Mapping(target = "medicoResponsavel", ignore = true) // Será definido no service
    void updateEntityFromDto(AcuidadeVisualRequestDTO dto, @MappingTarget AcuidadeVisualEntity entity);
}