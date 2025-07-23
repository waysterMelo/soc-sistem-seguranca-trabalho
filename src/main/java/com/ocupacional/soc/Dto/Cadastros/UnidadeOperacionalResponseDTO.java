package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.UnidadeOperacional.SituacaoUnidadeOperacional;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeOperacionalResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private SituacaoUnidadeOperacional situacao;
    private EmpresaSimpleResponseDTO empresa;
    private String cep;
    private String cidade;
    private String estado;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String regiao;
    private boolean usarEnderecoEmpresa;
    private String emailContato;
    private String dddTelefone1;
    private String numeroTelefone1;
    private String dddTelefone2;
    private String numeroTelefone2;
    private GrauRisco grauRisco;
    private CnaeDto cnaePrincipal;
    private boolean alocadaEmEmpresaTerceira;
    private TipoMatrizFilial tipoConfiguracaoUnidade;
    private String cnpjEmpresaTerceira;
    private String razaoSocialEmpresaTerceira;
    private List<SetorResponseDTO> setores;
}
