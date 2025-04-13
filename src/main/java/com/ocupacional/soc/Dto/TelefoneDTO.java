package com.ocupacional.soc.Dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {
 
    @Pattern(regexp = "^\\d{2,3}$", message = "DDD deve ter 2 ou 3 dígitos")
    private String ddd;
    
    @Pattern(regexp = "^\\d{8,9}$", message = "Número deve ter 8 ou 9 dígitos")
    private String numero;
}
