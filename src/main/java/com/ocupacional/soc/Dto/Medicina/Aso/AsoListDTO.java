package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AsoListDTO {
    private Long id;
    private String nomeFuncionario;
    private String nomeEmpresa;
    private TipoExameFuncao tipoAso;
    private LocalDate dataEmissao;
}
