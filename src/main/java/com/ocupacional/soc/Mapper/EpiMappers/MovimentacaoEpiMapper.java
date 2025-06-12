package com.ocupacional.soc.Mapper.EpiMappers;

import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiResponseDTO;
import com.ocupacional.soc.Entities.Epi.MovimentacaoEpiEntity;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { FuncionarioMapper.class, EpiEpcMapper.class })
public interface MovimentacaoEpiMapper {

    @Mapping(source = "funcionario", target = "funcionario")
    @Mapping(source = "epi", target = "epi")
    MovimentacaoEpiResponseDTO toDto(MovimentacaoEpiEntity entity);
}
