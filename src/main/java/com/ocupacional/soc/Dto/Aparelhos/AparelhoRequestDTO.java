package com.ocupacional.soc.Dto.Aparelhos;

import com.ocupacional.soc.Enuns.Aparelho.StatusAparelho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AparelhoRequestDTO {

    @NotBlank
    private String descricao;
    @NotBlank
    private String modelo;
    @NotBlank
    private String marca;
    private String calibracao;
    @NotNull
    private LocalDate dataCalibracao;
    private LocalDate validadeCalibracao;
    private String criteriosAvaliacao;
    @NotNull
    private StatusAparelho status;
}