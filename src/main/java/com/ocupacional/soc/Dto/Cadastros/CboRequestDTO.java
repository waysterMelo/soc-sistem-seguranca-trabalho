package com.ocupacional.soc.Dto.Cadastros;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CboRequestDTO {

    @NotBlank(message = "Código é obrigatório")
    private String codigoCbo;

    @NotBlank(message = "Descrição é obrigatória")
    private String nomeOcupacao;

}
