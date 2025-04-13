package com.ocupacional.soc.Dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnaePaginationRequestDTO {
      
    @Min(value = 0, message = "A página deve ser um número não negativo")
    private int page = 0;
    
    @Min(value = 1, message = "O tamanho da página deve ser pelo menos 1")
    @Max(value = 100, message = "O tamanho da página não pode ser maior que 100")
    private int size = 10;
    
    private String searchTerm;

    
}
