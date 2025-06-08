package com.ocupacional.soc.Dto.Cadastros;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameCatalogoRequestDTO {


    @Size(max = 20, message = "O código do exame deve ter no máximo 20 caracteres.")
    private String codigoExame; // Pode ser opcional se o sistema gerar

    @NotBlank(message = "O nome do exame não pode ser vazio.")
    private String nomeExame;

}
