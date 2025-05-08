package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.GrauRisco;
import com.ocupacional.soc.Enuns.SituacaoUnidadeOperacional;
import com.ocupacional.soc.Enuns.TipoMatrizFilial;
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
    private String endereco;
    private boolean usarEnderecoEmpresa;
    private String emailContato;
    private String dddTelefone1;
    private String numeroTelefone1;
    private String dddTelefone2;
    private String numeroTelefone2;
    private GrauRisco grauRisco;
    private CnaeDto cnaePrincipal;
    private List<CnaeDto> cnaesSecundarios;
    private boolean alocadaEmEmpresaTerceira;
    private TipoMatrizFilial tipoConfiguracaoUnidade;
    private String cnpjEmpresaTerceira;
    private String razaoSocialEmpresaTerceira;
    private List<SetorResponseDTO> setores;
}
