package com.ocupacional.soc.Dto.Medicina.Espirometria;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Espirometria.ConclusaoEspirometria;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EspirometriaAvaliacaoRequestDTO {
    @NotNull
    private Long funcionarioId;
    private Long aparelhoUtilizadoId;
    @NotNull
    private LocalDate dataExame;
    @NotNull
    private TipoExameFuncao tipoExame;
    private BigDecimal altura;
    private BigDecimal peso;
    private BigDecimal pef;
    private BigDecimal fev1;
    private BigDecimal fvc;
    @NotNull
    private ConclusaoEspirometria conclusao;
    private String outraConclusao;

}
