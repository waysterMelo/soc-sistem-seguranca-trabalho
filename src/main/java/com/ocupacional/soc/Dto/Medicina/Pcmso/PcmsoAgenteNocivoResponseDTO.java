package com.ocupacional.soc.Dto.Medicina.Pcmso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PcmsoAgenteNocivoResponseDTO {
    private Long id;
    private String nome;
}
