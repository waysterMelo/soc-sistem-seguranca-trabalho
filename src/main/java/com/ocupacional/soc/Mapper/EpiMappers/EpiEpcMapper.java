package com.ocupacional.soc.Mapper.EpiMappers;

import com.ocupacional.soc.Dto.EpiDto.EpiEpcRequestDTO;
import com.ocupacional.soc.Dto.EpiDto.EpiEpcResponseDTO;
import com.ocupacional.soc.Entities.Epi.EpiEpcEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EpiCategoriaMapper.class, EpiFabricanteMapper.class, EmpresaMapper.class})
public interface EpiEpcMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "fabricante", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    EpiEpcEntity toEntity(EpiEpcRequestDTO dto);

    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "fabricante", target = "fabricante")
    @Mapping(source = "empresa", target = "empresa")
    EpiEpcResponseDTO toDto(EpiEpcEntity entity);
}