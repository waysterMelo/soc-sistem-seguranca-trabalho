package com.ocupacional.soc.Dto;

import java.util.ArrayList;

import javax.validation.constraints.*;

public class Empresa {
    
  
    @NotNull(message = "O tipo de empresa é obrigatório")
    private TipoEmpresa tipoEmpresa;
    
    @NotBlank(message = "O CPF/CNPJ é obrigatório")
    @Pattern(regexp = "^\\d{11}$|^\\d{14}$", message = "CPF deve ter 11 dígitos ou CNPJ deve ter 14 dígitos")
    private String cpfCnpj;
    
    private String inscricaoEstadual;
    
    @NotNull(message = "O status é obrigatório")
    private StatusEmpresa status;
    
    @NotBlank(message = "A razão social é obrigatória")
    @Size(max = 150, message = "A razão social deve ter no máximo 150 caracteres")
    private String razaoSocial;
    
    @Size(max = 150, message = "O nome fantasia deve ter no máximo 150 caracteres")
    private String nomeFantasia;
    
    private String logoUrl;
    
    // Endereço
    private EnderecoDTO endereco;
    
    // Contatos
    private List<TelefoneDTO> telefones = new ArrayList<>();
    
    @Email(message = "O e-mail deve ser válido")
    private String email;
    
    // Outras informações
    private GrauRisco grauRisco;
    
    private Long cnaePrincipalId;
    
    private List<Long> cnaesSecundariosIds = new ArrayList<>();
    
    private TipoMatrizFilial tipoMatrizFilial;
    
    private Long empresaMatrizId;
    
    private Long medicoResponsavelId;
    
    private String observacoes;

}
