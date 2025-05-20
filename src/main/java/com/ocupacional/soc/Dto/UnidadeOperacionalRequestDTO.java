package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.UnidadeOperacional.SituacaoUnidadeOperacional;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeOperacionalRequestDTO {

    @NotBlank(message = "O nome da unidade é obrigatório.")
    @Size(max = 255, message = "O nome da unidade deve ter no máximo 255 caracteres.")
    private String nome;

    @Size(max = 1000, message = "A descrição da unidade deve ter no máximo 1000 caracteres.")
    private String descricao;

    @NotNull(message = "A situação da unidade é obrigatória.")
    private SituacaoUnidadeOperacional situacao;

    @NotNull(message = "O ID da empresa é obrigatório.")
    private Long empresaId;

    @Valid
    private EnderecoDto endereco;

    private boolean usarEnderecoEmpresa = false;

    @Email(message = "Formato de e-mail inválido.")
    @Size(max = 255, message = "O e-mail de contato deve ter no máximo 255 caracteres.")
    private String emailContato;

    @Size(max = 3, message = "O DDD do telefone 1 deve ter no máximo 3 caracteres.")
    private String dddTelefone1;

    @Size(max = 10, message = "O número do telefone 1 deve ter no máximo 10 caracteres.")
    private String numeroTelefone1;

    @Size(max = 3, message = "O DDD do telefone 2 deve ter no máximo 3 caracteres.")
    private String dddTelefone2;

    @Size(max = 10, message = "O número do telefone 2 deve ter no máximo 10 caracteres.")
    private String numeroTelefone2;

    @NotNull(message = "O grau de risco é obrigatório.")
    private GrauRisco grauRisco;

    @NotNull(message = "O CNAE principal é obrigatório.")
    private Long cnaePrincipalId;

    private boolean alocadaEmEmpresaTerceira = false;

    private TipoMatrizFilial tipoConfiguracaoUnidade;

    @Size(max = 18, message = "O CNPJ da empresa terceira deve ter no máximo 18 caracteres.")
    private String cnpjEmpresaTerceira;

    @Size(max = 255, message = "A razão social da empresa terceira deve ter no máximo 255 caracteres.")
    private String razaoSocialEmpresaTerceira;

    private List<Long> setoresIds;


}
