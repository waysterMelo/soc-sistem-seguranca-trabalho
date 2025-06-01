package com.ocupacional.soc.Dto;

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
public class AgenteNocivoCatalogoRequestDTO {

    @NotBlank(message = "O código eSocial do agente nocivo não pode ser vazio.")
    @Size(max = 20, message = "O código eSocial deve ter no máximo 20 caracteres.")
    private String codigoEsocial;

    @NotBlank(message = "A descrição do agente nocivo não pode ser vazia.")
    private String descricao;


}