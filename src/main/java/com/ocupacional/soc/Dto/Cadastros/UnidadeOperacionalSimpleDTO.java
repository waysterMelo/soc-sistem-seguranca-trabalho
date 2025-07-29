package com.ocupacional.soc.Dto.Cadastros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeOperacionalSimpleDTO {
    private Long id;
    private String nome;
    private String descricao;
}
