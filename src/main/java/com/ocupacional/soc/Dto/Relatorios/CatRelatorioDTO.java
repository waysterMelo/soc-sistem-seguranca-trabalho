package com.ocupacional.soc.Dto.Relatorios;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Entities.Cat.CatEntity;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Cat.*;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CatalogoCatMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CidMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatRelatorioDTO {

    // Dados da CAT
    private Long id;
    private String numeroReciboCat;
    private TipoCat tipoCat;
    private IniciativaCat iniciativaCat;
    private String numeroCatOrigem;

    // Dados do Acidentado
    private FuncionarioResponseDTO acidentado;

    // Dados do Acidente/Doença
    private String dataAcidenteFormatada;
    private String horaAcidenteFormatada;
    private Integer horasTrabalhadas;
    private TipoAcidente tipoAcidente;
    private Boolean houveAfastamento;
    private String ultimoDiaTrabalhado;
    private String localAcidenteEspecificacao;
    private EnderecoDto localAcidenteEndereco;
    private TipoLocalAcidente tipoLocalAcidente;
    private TipoInscricaoLocalAcidente tipoInscricaoLocalAcidente;
    private String inscricaoLocalAcidente;

    // Partes do corpo e causas
    private Set<CatalogoSimplesDTO> partesCorpoAtingidas;
    private CatalogoSimplesDTO agenteCausador;
    private CatalogoSimplesDTO naturezaLesao;
    private CatalogoSimplesDTO situacaoGeradora;

    // Informações complementares
    private Boolean houveRegistroPolicial;
    private Boolean houveObito;
    private String dataObitoFormatada;
    private String observacoes;

    // Dados do Atendimento
    private String dataAtendimentoFormatada;
    private String horaAtendimentoFormatada;
    private Boolean houveInternacao;
    private Integer duracaoTratamentoDias;
    private Boolean provavelAfastamento;
    private String diagnosticoProvavel;
    private CidDTO cid;
    private PrestadorServicoResponseDTO atestadoMedico;

    // Construtor que aceita CatEntity e mappers
    public CatRelatorioDTO(CatEntity entity,
                          FuncionarioMapper funcionarioMapper,
                          EnderecoMapper enderecoMapper,
                          CatalogoCatMapper catalogoMapper,
                          CidMapper cidMapper,
                          PrestadorServicoMapper prestadorMapper) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        this.id = entity.getId();
        this.numeroReciboCat = entity.getNumeroReciboCat();
        this.tipoCat = entity.getTipoCat();
        this.iniciativaCat = entity.getIniciativaCat();
        this.numeroCatOrigem = entity.getNumeroCatOrigem();

        // Mapear acidentado
        if (entity.getAcidentado() != null) {
            this.acidentado = funcionarioMapper.entityToResponseDto(entity.getAcidentado());
        }

        // Dados do acidente com formatação
        if (entity.getDataAcidente() != null) {
            this.dataAcidenteFormatada = entity.getDataAcidente().format(dateFormatter);
        }
        if (entity.getHoraAcidente() != null) {
            this.horaAcidenteFormatada = entity.getHoraAcidente().format(timeFormatter);
        }

        this.horasTrabalhadas = entity.getHorasTrabalhadas();
        this.tipoAcidente = entity.getTipoAcidente();
        this.houveAfastamento = entity.getHouveAfastamento();

        if (entity.getUltimoDiaTrabalhado() != null) {
            this.ultimoDiaTrabalhado = entity.getUltimoDiaTrabalhado().format(dateFormatter);
        }

        this.localAcidenteEspecificacao = entity.getLocalAcidenteEspecificacao();

        // Mapear endereço do local do acidente
        if (entity.getLocalAcidenteEndereco() != null) {
            this.localAcidenteEndereco = enderecoMapper.toDto(entity.getLocalAcidenteEndereco());
        }

        this.tipoLocalAcidente = entity.getTipoLocalAcidente();
        this.tipoInscricaoLocalAcidente = entity.getTipoInscricaoLocalAcidente();
        this.inscricaoLocalAcidente = entity.getInscricaoLocalAcidente();

        // Mapear partes do corpo atingidas
        if (entity.getPartesCorpoAtingidas() != null) {
            this.partesCorpoAtingidas = entity.getPartesCorpoAtingidas().stream()
                    .map(parte -> new CatalogoSimplesDTO(parte.getId(), parte.getDescricao()))
                    .collect(Collectors.toSet());
        }

        // Mapear agente causador
        if (entity.getAgenteCausador() != null) {
            this.agenteCausador = new CatalogoSimplesDTO(
                    entity.getAgenteCausador().getId(),
                    entity.getAgenteCausador().getDescricao()
            );
        }

        // Mapear natureza da lesão
        if (entity.getNaturezaLesao() != null) {
            this.naturezaLesao = new CatalogoSimplesDTO(
                    entity.getNaturezaLesao().getId(),
                    entity.getNaturezaLesao().getDescricao()
            );
        }

        // Mapear situação geradora
        if (entity.getSituacaoGeradora() != null) {
            this.situacaoGeradora = new CatalogoSimplesDTO(
                    entity.getSituacaoGeradora().getId(),
                    entity.getSituacaoGeradora().getDescricao()
            );
        }

        this.houveRegistroPolicial = entity.getHouveRegistroPolicial();
        this.houveObito = entity.getHouveObito();

        if (entity.getDataObito() != null) {
            this.dataObitoFormatada = entity.getDataObito().format(dateFormatter);
        }

        this.observacoes = entity.getObservacoes();

        // Dados do atendimento
        if (entity.getDataAtendimento() != null) {
            this.dataAtendimentoFormatada = entity.getDataAtendimento().format(dateFormatter);
        }
        if (entity.getHoraAtendimento() != null) {
            this.horaAtendimentoFormatada = entity.getHoraAtendimento().format(timeFormatter);
        }

        this.houveInternacao = entity.getHouveInternacao();
        this.duracaoTratamentoDias = entity.getDuracaoTratamentoDias();
        this.provavelAfastamento = entity.getProvavelAfastamento();
        this.diagnosticoProvavel = entity.getDiagnosticoProvavel();

        // Mapear CID
        if (entity.getCid() != null) {
            this.cid = cidMapper.toDto(entity.getCid());
        }

        // Mapear atestado médico
        if (entity.getAtestadoMedico() != null) {
            this.atestadoMedico = prestadorMapper.toDto(entity.getAtestadoMedico());
        }
    }
}