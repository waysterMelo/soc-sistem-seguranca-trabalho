package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import com.ocupacional.soc.Enuns.Funcionario.StatusFuncionario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FuncionarioResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String orgaoEmissorRg;
    private LocalDate dataEmissaoRg;
    private String estadoEmissorRg;
    private StatusFuncionario status;
    private String raca;
    private Sexo sexo;
    private String estadoCivil;
    private LocalDate dataNascimento;
    private Integer idade;
    private String nomeMae;
    private String nomePai;
    private String email;
    private String observacoes;
    private String matricula;
    private EnderecoDto endereco;
    private String telefone;
    private EmpresaSimpleResponseDTO empresa;
    private LocalDate dataAdmissao;

}
