package com.ocupacional.soc.Dto.Medicina.Aso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotivoAfastamentoResponseDTO {

    private Long id;
    private String codigo;
    private String descricao;
}
