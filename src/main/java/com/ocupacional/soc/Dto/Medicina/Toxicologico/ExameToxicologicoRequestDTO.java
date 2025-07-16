package com.ocupacional.soc.Dto.Medicina.Toxicologico;

import com.ocupacional.soc.Enuns.Medicina.Toxicologico.TipoRetificacao;
import com.ocupacional.soc.Enuns.Medicina.Toxicologico.UfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExameToxicologicoRequestDTO {

    @NotNull(message = "O registro profissional é obrigatório")
    private Long registroProfissionalId;

    @NotNull(message = "A data do exame é obrigatória")
    private LocalDate dataExame;

    @NotBlank(message = "O código do exame é obrigatório")
    private String codigoExame;

    @NotNull(message = "O laboratório responsável é obrigatório")
    private Long laboratorioId;

    @NotNull(message = "O médico responsável é obrigatório")
    private Long medicoResponsavelId;

    @NotNull(message = "O estado é obrigatório")
    private UfEnum estado;

    @NotNull(message = "O tipo de retificação é obrigatório")
    private TipoRetificacao tipoRetificacao;

    private String numeroReciboEsocial;
}