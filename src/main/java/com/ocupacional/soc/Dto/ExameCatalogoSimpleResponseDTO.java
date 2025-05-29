package com.ocupacional.soc.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameCatalogoSimpleResponseDTO {

    private Long id;
    private String codigoExame;
    private String nome;


}
