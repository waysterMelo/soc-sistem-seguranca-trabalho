package com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.Pcmso.PcmsoStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PcmsoRequestDTO {
    @NotNull(message = "O ID da Unidade Operacional é obrigatório.")
    private Long unidadeOperacionalId;

    @NotNull(message = "O status é obrigatório.")
    private PcmsoStatus status;

    private LocalDate dataDocumento;
    private LocalDate dataVencimento;

    private String capa;
    private String introducao;
    private String sobrePcmso;
    private String conclusao;

    @Valid
    private List<PcmsoExameRequestDTO> exames;
}