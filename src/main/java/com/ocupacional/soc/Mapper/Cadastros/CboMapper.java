package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.CboRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.CboResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CboEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CboMapper {

        CboEntity toEntity(CboRequestDTO dto);
        CboResponseDTO toDto(CboEntity entity);
        void updateEntityFromDto(CboRequestDTO dto, @MappingTarget CboEntity entity);

}
