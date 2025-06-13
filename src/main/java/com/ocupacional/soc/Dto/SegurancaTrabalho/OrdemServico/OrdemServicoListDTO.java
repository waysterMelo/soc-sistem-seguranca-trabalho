package com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoListDTO {
    private Long id;
    private String nomeFuncionario;
    private String nomeFuncao;
    private LocalDate dataElaboracao;
    private String revisao;
}