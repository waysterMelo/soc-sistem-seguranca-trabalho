package com.ocupacional.soc.Mapper.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Pcmso.PcmsoEntity;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnidadeOperacionalMapper.class, PcmsoExameMapper.class})
public interface PcmsoMapper {
    @Mapping(source = "unidadeOperacional", target = "unidadeOperacional")
    @Mapping(source = "exames", target = "exames")
    @Mapping(target = "riscos", ignore = true)
    PcmsoResponseDTO toResponseDto(PcmsoEntity entity);

    @Mapping(source = "unidadeOperacional.empresa.nomeFantasia", target = "nomeEmpresa")
    @Mapping(source = "unidadeOperacional.nome", target = "nomeUnidadeOperacional")
    PcmsoListDTO toListDto(PcmsoEntity entity);
}