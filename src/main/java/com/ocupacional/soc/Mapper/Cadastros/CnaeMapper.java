package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.CnaeDto;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CnaeMapper {

    CnaeMapper INSTANCE = Mappers.getMapper(CnaeMapper.class);

    CnaeDto toDto(CnaeEntity entity);

    CnaeEntity toEntity(CnaeDto dto);

    List<CnaeDto> toDtoList(List<CnaeEntity> entities);

    List<CnaeEntity> toEntityList(List<CnaeDto> dtos);
}
