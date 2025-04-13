package com.ocupacional.soc.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaSimpleDTO {
    
    private Long id;
    private String razaoSocial;
    private String cpfCnpj;
    
}
