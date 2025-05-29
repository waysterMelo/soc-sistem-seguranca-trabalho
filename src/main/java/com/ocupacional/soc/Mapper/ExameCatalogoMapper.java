package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.ExameCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Entities.ExamesPCMSO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExameCatalogoMapper {
    ExameCatalogoMapper INSTANCE = Mappers.getMapper(ExameCatalogoMapper.class);

    ExameCatalogoSimpleResponseDTO toSimpleResponseDto(ExamesPCMSO entity);
}
