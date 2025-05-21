package com.ocupacional.soc.Mapper;


import com.ocupacional.soc.Dto.RiscoTrabalhistaPgrRequestDTO;
import com.ocupacional.soc.Dto.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Entities.RiscoTrabalhistaPgrEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RiscoTrabalhistaPgrMapper {

    RiscoTrabalhistaPgrMapper INSTANCE = Mappers.getMapper(RiscoTrabalhistaPgrMapper.class);

    RiscoTrabalhistaPgrEntity toEntity(RiscoTrabalhistaPgrRequestDTO dto);

    RiscoTrabalhistaPgrResponseDTO toResponseDTO(RiscoTrabalhistaPgrEntity entity);

}
