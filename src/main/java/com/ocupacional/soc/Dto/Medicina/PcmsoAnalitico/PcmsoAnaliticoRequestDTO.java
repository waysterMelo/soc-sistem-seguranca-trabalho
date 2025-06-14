package com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PcmsoAnaliticoRequestDTO {

    private Long empresaId; // Opcional
    private Long unidadeOperacionalId; // Opcional

    @NotNull(message = "O Médico Responsável é obrigatório.")
    private Long medicoResponsavelId;

    @NotNull(message = "A Data de Início é obrigatória.")
    private LocalDate dataInicio;

    @NotNull(message = "A Data de Fim é obrigatória.")
    private LocalDate dataFim;

    private String discussaoResultados;
}