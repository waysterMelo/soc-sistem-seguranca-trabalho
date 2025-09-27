package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class PcmsoRequestDTO {
    @NotNull(message = "O ID da Unidade Operacional é obrigatório.")
    private Long unidadeOperacionalId;
    @NotNull(message = "O Médico Responsável é obrigatório.")
    private Long medicoResponsavelId;
    private Set<Long> elaboradoresIds;
    @NotNull(message = "O status é obrigatório.")
    private PcmsoStatus status;

    private LocalDate dataDocumento;
    private LocalDate dataVencimento;

    private String capa;
    private String introducao;
    private String sobrePcmso;
    private String conclusao;

    private String imagemCapa;

    @Valid
    private List<PcmsoExameRequestDTO> exames;
}