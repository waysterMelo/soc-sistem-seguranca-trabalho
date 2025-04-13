package com.ocupacional.soc.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponseDT {
       private Long id;
    private TipoEmpresa tipoEmpresa;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private StatusEmpresa status;
    private String razaoSocial;
    private String nomeFantasia;
    private String logoUrl;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones = new ArrayList<>();
    private String email;
    private GrauRisco grauRisco;
    private CnaeDTO cnaePrincipal;
    private List<CnaeDTO> cnaesSecundarios = new ArrayList<>();
    private TipoMatrizFilial tipoMatrizFilial;
    private EmpresaSimpleDTO empresaMatriz;
    private MedicoDTO medicoResponsavel;
    private String observacoes;
}
