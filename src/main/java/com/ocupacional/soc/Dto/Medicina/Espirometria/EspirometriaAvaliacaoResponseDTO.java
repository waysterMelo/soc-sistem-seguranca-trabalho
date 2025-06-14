package com.ocupacional.soc.Dto.Medicina.Espirometria;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Espirometria.ConclusaoEspirometria;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EspirometriaAvaliacaoResponseDTO {
    private Long id;
    private FuncionarioResponseDTO funcionario;
    private AparelhoResponseDTO aparelhoUtilizado;
    private LocalDate dataExame;
    private TipoExameFuncao tipoExame;
    private BigDecimal altura;
    private BigDecimal peso;
    private BigDecimal pef;
    private BigDecimal fev1;
    private BigDecimal fvc;
    private BigDecimal relacaoFev1Fvc; // Campo calculado
    private ConclusaoEspirometria conclusao;
    private String outraConclusao;
}