package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipNr16AnexoDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip.LtipNr16AnexoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {Nr16AnexoMapper.class})
public interface LtipNr16AnexoMapper {

    @Mapping(source = "anexo", target = "anexo")
    LtipNr16AnexoDTO toDto(LtipNr16AnexoEntity entity);
}