package com.ocupacional.soc.Mapper.SegurancaTrabalho.Treinamento;


import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoRealizadoEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Mapper.PrestadorServiços.PrestadorServicoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        EmpresaMapper.class,
        TreinamentoCatalogoMapper.class,
        PrestadorServicoMapper.class,
        TreinamentoParticipanteMapper.class
})
public interface TreinamentoRealizadoMapper {
    TreinamentoRealizadoResponseDTO toDto(TreinamentoRealizadoEntity entity);
}