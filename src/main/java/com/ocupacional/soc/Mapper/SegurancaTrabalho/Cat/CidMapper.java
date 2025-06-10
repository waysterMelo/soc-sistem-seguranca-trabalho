package com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat;


import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Entities.Cat.CidEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CidMapper {
    CidDTO toDto(CidEntity entity);
}