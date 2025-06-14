package com.ocupacional.soc.Dto.Cadastros;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ContextoProfissionalResponseDTO {
    private String nomeFuncionario;
    private String nomeEmpresa;
    private String nomeSetor;
    private String nomeFuncao;
    private LocalDate dataAdmissao;
    private LocalDate dataEntradaCargo;
}