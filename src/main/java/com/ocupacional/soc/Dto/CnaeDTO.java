package com.ocupacional.soc.Dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnaeDTO {
    private Long id;
    
    @NotBlank(message = "O código do CNAE é obrigatório")
    @Pattern(regexp = "^\\d{4}-\\d{1}/\\d{2}$", message = "Código de CNAE deve estar no formato 0000-0/00")
    private String codigo;
    
    @NotBlank(message = "A descrição do CNAE é obrigatória")
    private String descricao;
}
