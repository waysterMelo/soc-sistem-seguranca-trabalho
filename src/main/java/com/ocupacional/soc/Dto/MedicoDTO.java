package com.ocupacional.soc.Dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private Long id;
    
    @NotBlank(message = "O nome do médico é obrigatório")
    private String nome;
    
    @Pattern(regexp = "^\\d{4,10}(-[A-Z]{2})?$", message = "CRM deve estar em formato válido")
    private String crm;
}
