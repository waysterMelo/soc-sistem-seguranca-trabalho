package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoCatalogoSimpleResponseDTO {
    private Long id;
    private GrupoRisco grupo;
    private String descricao;
}