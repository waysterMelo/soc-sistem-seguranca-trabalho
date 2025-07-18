
package com.ocupacional.soc.Dto.Relatorios;

import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import lombok.Data;

@Data
public class EmpresaRelatorioDTO {
    private Long id;
    private String tipoEmpresa;
    private String cpfOuCnpj;
    private String inscricaoEstadual;
    private String status; // Convertido para String
    private String razaoSocial;
    private String nomeFantasia;
    private String logomarcaUrl;
    private String endereco;
    private String telefonePrincipal;
    private String telefoneSecundario;
    private String email;
    private String grauRisco;
    private Long cnaePrincipalId;
    private String tipoMatrizFilial;
    private String medicoResponsavelPcmsso;
    private String medicoResponsavel; // Para o relatório
    private String cidade; // Para o relatório
    private String estado; // Para o relatório
    private String observacoes;

    // Construtor para converter da entidade
    public EmpresaRelatorioDTO(EmpresaEntity entity) {
        try {
            this.id = entity.getId();
            this.tipoEmpresa = entity.getTipoEmpresa() != null ? entity.getTipoEmpresa().name() : "";
            this.cpfOuCnpj = entity.getCpfOuCnpj() != null ? entity.getCpfOuCnpj() : "";
            this.inscricaoEstadual = entity.getInscricaoEstadual() != null ? entity.getInscricaoEstadual() : "";
            this.status = entity.getStatus() != null ? entity.getStatus().name() : "";
            this.razaoSocial = entity.getRazaoSocial() != null ? entity.getRazaoSocial() : "";
            this.nomeFantasia = entity.getNomeFantasia() != null ? entity.getNomeFantasia() : "";
            this.logomarcaUrl = entity.getLogomarcaUrl() != null ? entity.getLogomarcaUrl() : "";

            // Tratamento de endereço para evitar NullPointerException
            if (entity.getEndereco() != null) {
                String logradouro = entity.getEndereco().getLogradouro() != null ? entity.getEndereco().getLogradouro() : "";
                String numero = entity.getEndereco().getNumero() != null ? entity.getEndereco().getNumero() : "";
                this.endereco = logradouro + ", " + numero;
                this.cidade = entity.getEndereco().getCidade() != null ? entity.getEndereco().getCidade() : "";
                this.estado = entity.getEndereco().getEstado() != null ? entity.getEndereco().getEstado() : "";
            } else {
                this.endereco = "";
                this.cidade = "";
                this.estado = "";
            }

            this.telefonePrincipal = entity.getTelefonePrincipal() != null ? entity.getTelefonePrincipal() : "";
            this.telefoneSecundario = entity.getTelefoneSecundario() != null ? entity.getTelefoneSecundario() : "";
            this.email = entity.getEmail() != null ? entity.getEmail() : "";
            this.grauRisco = entity.getGrauRisco() != null ? entity.getGrauRisco().name() : "";

            // Tratamento de CNAE para evitar NullPointerException
            if (entity.getCnaePrincipalId() != null) {
                this.cnaePrincipalId = entity.getCnaePrincipalId().getId();
            } else {
                this.cnaePrincipalId = null;
            }

            this.tipoMatrizFilial = entity.getTipoMatrizFilial() != null ? entity.getTipoMatrizFilial().name() : "";

            // Tratamento de médico responsável
            if (entity.getMedicoResponsavelPcmsso() != null && entity.getMedicoResponsavelPcmsso().getNome() != null) {
                this.medicoResponsavelPcmsso = entity.getMedicoResponsavelPcmsso().getNome();
                this.medicoResponsavel = entity.getMedicoResponsavelPcmsso().getNome(); // Para o campo no relatório
            } else {
                this.medicoResponsavelPcmsso = "";
                this.medicoResponsavel = "";
            }

            this.observacoes = entity.getObservacoes() != null ? entity.getObservacoes() : "";
        } catch (Exception e) {
            System.err.println("Erro ao converter empresa para DTO: " + e.getMessage());
            // Inicializando valores padrão em caso de erro
            this.id = entity.getId();
            this.razaoSocial = entity.getRazaoSocial() != null ? entity.getRazaoSocial() : "ERRO DE CONVERSÃO";
            this.status = "";
            this.medicoResponsavel = "";
            this.cidade = "";
            this.estado = "";
            // Demais campos com valores padrão
        }
    }
}