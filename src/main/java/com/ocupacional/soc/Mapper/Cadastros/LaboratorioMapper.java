package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.LaboratorioDTO;
import com.ocupacional.soc.Entities.Cadastros.LaboratorioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface LaboratorioMapper {

    LaboratorioDTO toDto(LaboratorioEntity entity);
}