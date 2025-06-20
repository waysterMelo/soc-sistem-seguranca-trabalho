package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Nr16AnexoDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Nr16AnexoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Nr16AnexoMapper {
    Nr16AnexoDTO toDto(Nr16AnexoEntity entity);
}