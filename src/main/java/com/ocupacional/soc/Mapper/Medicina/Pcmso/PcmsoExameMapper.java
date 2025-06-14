package com.ocupacional.soc.Mapper.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoExameResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Pcmso.PcmsoExameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PcmsoExameMapper {

    @Mapping(source = "funcao.id", target = "funcaoId")
    @Mapping(source = "funcao.nome", target = "nomeFuncao")
    @Mapping(source = "exame.id", target = "exameId")
    @Mapping(source = "exame.nomeExame", target = "nomeExame")
    @Mapping(source = "exame.codigoExame", target = "codigoExame")
    PcmsoExameResponseDTO toResponseDto(PcmsoExameEntity entity);
}