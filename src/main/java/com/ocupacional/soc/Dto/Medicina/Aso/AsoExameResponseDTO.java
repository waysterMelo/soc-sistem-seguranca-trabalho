package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsoExameResponseDTO {
    private Long id;
    private ExameCatalogoResponseDTO exame;
    private String resultadoUrl;
}
