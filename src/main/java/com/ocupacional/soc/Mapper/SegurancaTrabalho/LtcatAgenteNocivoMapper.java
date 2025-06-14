package com.ocupacional.soc.Mapper.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatAgenteNocivoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatAgenteNocivoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LtcatAgenteNocivoMapper {

    @Mapping(source = "agenteNocivo.id", target = "agenteNocivoId")
    @Mapping(source = "agenteNocivo.codigoEsocial", target = "agenteNocivoCodigo")
    @Mapping(source = "agenteNocivo.descricao", target = "agenteNocivoDescricao")
    @Mapping(source = "funcao.id", target = "funcaoId")
    @Mapping(source = "funcao.nome", target = "nomeFuncao")
    LtcatAgenteNocivoResponseDTO toDto(LtcatAgenteNocivoEntity entity);
}