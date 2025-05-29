package com.ocupacional.soc.Dto;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoTrabalhistaPgrResponseDTO {
    private Long id;
    private RiscoCatalogoSimpleResponseDTO riscoCatalogo;

}