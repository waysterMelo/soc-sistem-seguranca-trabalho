package com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class TreinamentoRealizadoResponseDTO {
    private Long id;
    private EmpresaSimpleResponseDTO empresa;
    private TreinamentoCatalogoDTO treinamentoCatalogo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDate dataValidade;
    private String observacoes;
    private Set<PrestadorServicoResponseDTO> responsaveis;
    private Set<TreinamentoParticipanteResponseDTO> participantes;
}