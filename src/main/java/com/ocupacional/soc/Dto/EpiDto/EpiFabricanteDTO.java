package com.ocupacional.soc.Dto.EpiDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpiFabricanteDTO {
    private Long id;
    @NotBlank(message = "O nome do fabricante é obrigatório.")
    private String nome;
}