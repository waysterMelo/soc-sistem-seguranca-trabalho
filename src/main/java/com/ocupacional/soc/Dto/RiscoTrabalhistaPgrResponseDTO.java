package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoTrabalhistaPgrResponseDTO {

    private Long id;
    private GrupoRisco grupo;
    private String descricao;
}
