package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PcmsoFuncaoResponseDTO {
    private Long id;
    private String nome;
    private List<PcmsoExameResponseDTO> exames;
    private List<RiscoTrabalhistaPgrResponseDTO> riscos;
    private List<PcmsoAgenteNocivoResponseDTO> agentesNocivos;
}
