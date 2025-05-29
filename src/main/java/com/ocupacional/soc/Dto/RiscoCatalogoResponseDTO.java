package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiscoCatalogoResponseDTO {
    private Long id;
    private GrupoRisco grupo;
    private String descricao;
}