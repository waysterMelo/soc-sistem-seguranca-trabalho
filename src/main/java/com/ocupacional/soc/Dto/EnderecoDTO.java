package com.ocupacional.soc.Dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    
      @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve estar no formato 00000-000")
    private String cep;
    
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    private String cidade;
    
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (UF)")
    private String estado;
    
    @Size(max = 150, message = "O logradouro deve ter no máximo 150 caracteres")
    private String logradouro;
    
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    private String numero;
    
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;
    
    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
    private String complemento;
    
    @Size(max = 50, message = "A região deve ter no máximo 50 caracteres")
    private String regiao;
    

}
