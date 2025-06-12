package com.ocupacional.soc.Mapper.SegurancaTrabalho.Treinamento;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoParticipanteResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoParticipante;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FuncionarioMapper.class})
public interface TreinamentoParticipanteMapper {
    TreinamentoParticipanteResponseDTO toDto(TreinamentoParticipante entity);
}