package com.ocupacional.soc.Dto.Medicina.Pcmso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PcmsoSetorResponseDTO {
    private Long id;
    private String nome;
    private List<PcmsoFuncaoResponseDTO> funcoes;
}
