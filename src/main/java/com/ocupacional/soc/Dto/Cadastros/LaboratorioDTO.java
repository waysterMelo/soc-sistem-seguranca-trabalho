package com.ocupacional.soc.Dto.Cadastros;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LaboratorioDTO {
    private Long id;

    @NotBlank(message = "O campo Razão Social é obrigatório")
    private String razaoSocial;

    @NotBlank(message = "O campo Nome Fantasia é obrigatório")
    private String nomeFantasia;

    private String cnpj;

    @Valid
    private EnderecoDto endereco;

    @Valid
    private List<TelefoneDto> telefones;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email com formato inválido")
    private String email;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}