package com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Pcmso.PcmsoStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PcmsoResponseDTO {
    private Long id;
    private UnidadeOperacionalResponseDTO unidadeOperacional;
    private PcmsoStatus status;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private String capaImagemUrl;
    private String capa;
    private String introducao;
    private String sobrePcmso;
    private String conclusao;
    private List<PcmsoExameResponseDTO> exames;
    private List<RiscoTrabalhistaPgrResponseDTO> riscos;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}