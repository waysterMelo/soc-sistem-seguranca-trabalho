package com.ocupacional.soc.Dto.Medicina.Pcmso;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PcmsoFuncaoResponseDTO {
    private Long id;
    private String nome;
    private List<PcmsoExameResponseDTO> exames;
    private List<PcmsoRiscoResponseDTO> riscos;
    private List<PcmsoAgenteNocivoResponseDTO> agentesNocivos;
}