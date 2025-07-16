package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import lombok.Data;

@Data
public class EmpresaDto {


    private Long id;
    private TipoEmpresa tipoEmpresa;
    private String cpfOuCnpj;
    private String inscricaoEstadual;
    private StatusEmpresa status;
    private String razaoSocial;
    private String nomeFantasia;
    private String logomarcaUrl;
    private EnderecoDto endereco;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String email;
    private GrauRisco grauRisco;
    private Long cnaePrincipalId;
    private TipoMatrizFilial tipoMatrizFilial;
    private Long medicoResponsavelPcmssoId;
    private String observacoes;
}
