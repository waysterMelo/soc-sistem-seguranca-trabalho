package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaResponseDTO;
import com.ocupacional.soc.Entities.BibliografiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BibliografiaMapper {
    BibliografiaEntity toEntity(BibliografiaRequestDTO dto);
    BibliografiaResponseDTO toDto(BibliografiaEntity entity);
    void updateEntityFromDto(BibliografiaRequestDTO dto, @MappingTarget BibliografiaEntity entity);
}