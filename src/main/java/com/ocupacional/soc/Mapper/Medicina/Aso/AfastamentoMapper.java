package com.ocupacional.soc.Mapper.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Aso.AfastamentoEntity;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        PrestadorServicoMapper.class,
        MotivoAfastamentoMapper.class,
        CidMapper.class
})
public interface AfastamentoMapper {

    @Mapping(target = "totalDias", ignore = true)
    AfastamentoResponseDTO toResponseDto(AfastamentoEntity entity);

    @Mapping(source = "criadoEm", target = "dataCriacao")
    @Mapping(source = "registroProfissional.funcao.empresa.nomeFantasia", target = "nomeEmpresa")
    @Mapping(source = "emitente.nome", target = "nomeResponsavel")
    AfastamentoListDTO toListDto(AfastamentoEntity entity);
}