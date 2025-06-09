package com.ocupacional.soc.Mapper.Mapper;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoRequestDTO;
import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AparelhoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "certificadoUrl", ignore = true)
    AparelhoEntity toEntity(AparelhoRequestDTO dto);

    AparelhoResponseDTO toDto(AparelhoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "certificadoUrl", ignore = true)
    void updateEntityFromDto(AparelhoRequestDTO dto, @MappingTarget AparelhoEntity entity);
}