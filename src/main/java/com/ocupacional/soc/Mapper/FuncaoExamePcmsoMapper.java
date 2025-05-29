package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.FuncaoExamePcmsoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoExamePcmsoResponseDTO;
import com.ocupacional.soc.Entities.ExamesPCMSO;
import com.ocupacional.soc.Entities.FuncaoExamePcmsoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ExameCatalogoMapper.class})
public interface FuncaoExamePcmsoMapper {
    FuncaoExamePcmsoMapper INSTANCE = Mappers.getMapper(FuncaoExamePcmsoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funcao", ignore = true) // Será setada no service
    @Mapping(source = "exameCatalogoId", target = "exameCatalogo", qualifiedByName = "idToExameCatalogoEntity")
    FuncaoExamePcmsoEntity requestDtoToEntity(FuncaoExamePcmsoRequestDTO dto);

    @Mapping(source = "exameCatalogo", target = "exameCatalogo") // Usa ExameCatalogoMapper
    FuncaoExamePcmsoResponseDTO entityToResponseDto(FuncaoExamePcmsoEntity entity);

    @Named("idToExameCatalogoEntity")
    default ExamesPCMSO idToExameCatalogoEntity(Long id) {
        if (id == null) {
            return null;
        }
        ExamesPCMSO exame = new ExamesPCMSO();
        exame.setId(id);
        return exame;
    }
}