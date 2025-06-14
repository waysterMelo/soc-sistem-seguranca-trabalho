package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import com.ocupacional.soc.Enuns.Funcionario.StatusFuncionario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório") @Size(max = 100) private String nome;
    @Size(max = 150) private String sobrenome;
    @NotBlank(message = "CPF é obrigatório") @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido (formato esperado: XXX.XXX.XXX-XX)") private String cpf;
    @Size(max = 20) private String rg;
    @Size(max = 50) private String orgaoEmissorRg;
    @PastOrPresent(message = "Data de emissão do RG deve ser no passado ou presente") private LocalDate dataEmissaoRg;
    @Size(min = 2, max = 2) private String estadoEmissorRg;
    @NotNull(message = "Status é obrigatório") private StatusFuncionario status;
    @Size(max = 50) private String raca;
    @NotNull(message = "Sexo é obrigatório") private Sexo sexo;
    @Size(max = 50) private String estadoCivil;
    @NotNull(message = "Data de nascimento é obrigatória") @Past(message = "Data de nascimento deve ser no passado") private LocalDate dataNascimento;
    @Size(max = 250) private String nomeMae;
    @Size(max = 250) private String nomePai;
    @Email(message = "Email inválido") @Size(max = 255) private String email;
    private String observacoes;
    private boolean criarRegistroProfissional;
    @NotBlank(message = "Matrícula é obrigatória") @Size(max = 50) private String matricula;
    @Valid @NotNull(message = "Endereço é obrigatório") private EnderecoDto endereco;
    @Valid private List<TelefoneDto> telefones;
    @NotNull(message = "ID da Empresa é obrigatório") private Long empresaId;
    @NotNull(message = "Data de admissão é obrigatório ")
    private LocalDate dataAdmissao;


}
