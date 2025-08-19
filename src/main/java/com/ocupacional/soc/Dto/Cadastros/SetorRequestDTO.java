package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoDocumento;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoEmpresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorRequestDTO {

    @NotBlank(message = "O nome do setor é obrigatório.")
    private String nome;
    private String descricao;
    @NotNull(message = "O ID da empresa é obrigatório.")
    private Long empresaId;
    private Long unidadeOperacionalId;
    private StatusEmpresa status;
    private TipoEmpresa tipoEmpresa;
    private TipoDocumento tipoDocumento;
    private Long numeroDocumento;

}