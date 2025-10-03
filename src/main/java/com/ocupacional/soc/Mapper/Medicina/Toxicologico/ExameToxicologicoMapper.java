package com.ocupacional.soc.Mapper.Medicina.Toxicologico;

import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Medicina.Toxicologico.ExameToxicologicoEntity;
import com.ocupacional.soc.Mapper.Cadastros.LaboratorioMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        LaboratorioMapper.class,
        PrestadorServicoMapper.class
})
public interface ExameToxicologicoMapper {

    @Mapping(source = "registroProfissional", target = "registroProfissional")
    @Mapping(source = "laboratorio", target = "laboratorio")
    @Mapping(source = "medicoResponsavel", target = "medicoResponsavel")
    @Mapping(target = "crmMedicoResponsavel", ignore = true) // Será preenchido no @AfterMapping
    ExameToxicologicoResponseDTO toResponseDto(ExameToxicologicoEntity entity);

    @AfterMapping
    default void preencherCrm(@MappingTarget ExameToxicologicoResponseDTO dto, ExameToxicologicoEntity entity) {
        PrestadorServicoEntity medico = entity.getMedicoResponsavel();
        if (medico != null && medico.getNumeroInscricaoConselho() != null && medico.getEstadoConselho() != null) {
            String crmFormatado = String.format("%s/%s", medico.getNumeroInscricaoConselho(), medico.getEstadoConselho());
            dto.setCrmMedicoResponsavel(crmFormatado);
        }
    }
}