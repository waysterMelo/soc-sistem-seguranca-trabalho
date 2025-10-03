package com.ocupacional.soc.Dto.Medicina.Aso;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsoExameRequestDTO {
    @NotNull
    private Long exameCatalogoId;
    private String nomeArquivoOriginal;
}