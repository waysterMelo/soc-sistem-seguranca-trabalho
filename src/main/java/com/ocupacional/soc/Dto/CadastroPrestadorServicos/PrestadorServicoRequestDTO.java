package com.ocupacional.soc.Dto.CadastroPrestadorServicos;

import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Dto.Cadastros.TelefoneDto;
import com.ocupacional.soc.Enuns.CadastroPrestador.TipoConselho;
import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class PrestadorServicoRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank private String sobrenome;
    @CPF
    private String cpf;
    private String rg;
    private String orgaoEmissorRg;
    @NotNull
    private Sexo sexo;
    @Valid
    private EnderecoDto endereco;
    private TelefoneDto telefone1;
    private TelefoneDto telefone2;
    @Email
    private String email;
    @NotNull private Long cboId;
    private String nis;
    private TipoConselho conselho;
    private String numeroInscricaoConselho;
    private String estadoConselho;
}
