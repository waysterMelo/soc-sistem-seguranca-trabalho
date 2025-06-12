package com.ocupacional.soc.Mapper.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoCatalogoDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoCatalogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TreinamentoCatalogoMapper {

        TreinamentoCatalogoDTO toDto(TreinamentoCatalogoEntity entity);

        TreinamentoCatalogoEntity toEntity(TreinamentoCatalogoDTO dto);

        void updateEntityFromDto(TreinamentoCatalogoDTO dto, @MappingTarget TreinamentoCatalogoEntity entity);
    }
