package com.ocupacional.soc.Mapper.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.AsoExameResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoExameEntity;
import com.ocupacional.soc.Mapper.Cadastros.ExameCatalogoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExameCatalogoMapper.class})
public interface AsoExameMapper {

    @Mapping(source = "exame", target = "exame")
    AsoExameResponseDTO toResponseDto(AsoExameEntity entity);
}