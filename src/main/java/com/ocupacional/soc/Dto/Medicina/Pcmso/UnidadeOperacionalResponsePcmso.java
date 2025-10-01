package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Cadastros.CnaeDto;
import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.UnidadeOperacional.SituacaoUnidadeOperacional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeOperacionalResponsePcmso {

    private Long id;
    private String nome;
    private String descricao;
    private SituacaoUnidadeOperacional situacao;
    private EmpresaDto empresa;
    private String cep;
    private String cidade;
    private String estado;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String regiao;
    private String emailContato;
    private String dddTelefone1;
    private String numeroTelefone1;
    private String dddTelefone2;
    private String numeroTelefone2;
    private GrauRisco grauRisco;
    private CnaeDto cnaePrincipal;
}
