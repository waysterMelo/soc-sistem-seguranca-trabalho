package com.ocupacional.soc.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalResponsavelResponseDTO {

    private Long id;
    private Long funcionarioId;
    private String nomeFuncionario;

}
