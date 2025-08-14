package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FuncaoSimplificadaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private StatusEmpresa status;
}