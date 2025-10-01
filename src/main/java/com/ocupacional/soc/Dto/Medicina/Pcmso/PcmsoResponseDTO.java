package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class PcmsoResponseDTO {
    private Long id;
    private UnidadeOperacionalResponseDTO unidadeOperacional;
    private PcmsoStatus status;
    private PrestadorServicoPcmsoDto medicoResponsavel;
    private Set<PrestadorServicoPcmsoDto> elaboradores;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private String imagemCapa;
    private String introducao;
    private String sobrePcmso;
    private String conclusao;
    private List<PcmsoSetorResponseDTO> setores;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}