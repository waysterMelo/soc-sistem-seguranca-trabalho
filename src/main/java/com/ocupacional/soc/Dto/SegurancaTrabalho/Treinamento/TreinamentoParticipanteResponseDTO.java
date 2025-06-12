package com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import lombok.Data;

@Data
public class TreinamentoParticipanteResponseDTO {
    private Long id;
    private FuncionarioResponseDTO funcionario;
    private boolean concluiu;
    private boolean fezTreinamentoAnterior;
}