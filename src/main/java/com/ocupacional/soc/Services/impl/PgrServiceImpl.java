package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Relatorios.PgrRelatorioDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrMapaRiscoFuncaoResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrMapaRiscoFuncaoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PlanoAcaoRiscoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PgrMapaRiscoFuncaoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PgrMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PlanoAcaoRiscoMapper;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.RiscoCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.PgrRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.PgrService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PgrServiceImpl implements PgrService {

    private final PgrRepository pgrRepository;
    private final PgrMapper pgrMapper;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final FuncaoRepository funcaoRepository;
    private final RiscoCatalogoRepository riscoCatalogoRepository;
    private final PgrMapaRiscoFuncaoMapper mapaRiscoMapper;
    private final PlanoAcaoRiscoMapper planoAcaoMapper;
    private final UnidadeOperacionalMapper unidadeOperacionalMapper;

    private final Path root = Paths.get("uploads/pgr-capas");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível inicializar o diretório de upload!");
        }
    }

    @Override
    @Transactional
    public PgrResponseDTO createPgr(PgrRequestDTO requestDTO, MultipartFile capa) {
        PgrEntity pgrEntity = new PgrEntity();
        updatePgrFields(pgrEntity, requestDTO);

        if (capa != null && !capa.isEmpty()) {
            String filename = saveFile(capa);
            pgrEntity.setConteudoCapa(filename);
        }

        UnidadeOperacionalEntity unidade = findUnidadeById(requestDTO.getUnidadeOperacionalId());
        pgrEntity.setUnidadeOperacional(unidade);

        PgrEntity savedPgrEntity = pgrRepository.save(pgrEntity);

        updateMapaDeRiscos(savedPgrEntity, requestDTO);
        updatePlanoDeAcao(savedPgrEntity, requestDTO);

        return pgrMapper.toDto(pgrRepository.save(savedPgrEntity));
    }

    @Override
    public PgrResponseDTO getPgrById(Long id) {
        PgrEntity entity = pgrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + id));

        PgrResponseDTO dto = pgrMapper.toDto(entity);

        List<PgrMapaRiscoFuncaoResponseDTO> mapaRiscos = new ArrayList<>();
        if (entity.getUnidadeOperacional() != null && entity.getUnidadeOperacional().getSetores() != null) {
            mapaRiscos = entity.getUnidadeOperacional().getSetores().stream()
                    .flatMap(setor -> setor.getFuncoes().stream())
                    .map(funcao -> {
                        PgrMapaRiscoFuncaoResponseDTO mapaDto = new PgrMapaRiscoFuncaoResponseDTO();
                        mapaDto.setFuncaoId(funcao.getId());
                        mapaDto.setNomeFuncao(funcao.getNome());

                        if (funcao.getRiscosPGR() != null) {
                            Set<com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO> riscos = funcao.getRiscosPGR().stream()
                                    .map(riscoPgr -> {
                                        RiscoCatalogoEntity riscoCat = riscoPgr.getRiscoCatalogo();
                                        return new com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO(riscoCat.getId(), riscoCat.getGrupo(), riscoCat.getDescricao());
                                    })
                                    .collect(Collectors.toSet());
                            mapaDto.setRiscosDoCatalogo(riscos);
                        } else {
                            mapaDto.setRiscosDoCatalogo(new HashSet<>());
                        }
                        
                        return mapaDto;
                    })
                    .collect(Collectors.toList());
        }

        dto.setMapaRiscos(mapaRiscos);
        // O planoAcaoRiscos já deve ser mapeado corretamente pelo pgrMapper,
        // pois a lógica é direta. Se também vier vazio, precisará de uma abordagem similar.

        return dto;
    }

    @Override
    public Page<PgrResponseDTO> getAllPgrsByEmpresa(Long empresaId, Pageable pageable) {
        return pgrRepository.findByUnidadeOperacional_Empresa_Id(empresaId, pageable).map(pgrMapper::toDto);
    }

    @Override
    @Transactional
    public PgrResponseDTO updatePgr(Long id, PgrRequestDTO requestDTO, MultipartFile capa) {
        System.out.println("--- DEBUG: updatePgr ---");
        PgrEntity pgrEntity = pgrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + id));

        if (capa != null && !capa.isEmpty()) {
            System.out.println("Capa recebida: " + capa.getOriginalFilename());
            deleteFile(pgrEntity.getConteudoCapa());
            String filename = saveFile(capa);
            pgrEntity.setConteudoCapa(filename);
            System.out.println("Capa salva como: " + filename);
        } else {
            System.out.println("Capa NÃO recebida ou está vazia.");
        }

        updatePgrFields(pgrEntity, requestDTO);

        if (!pgrEntity.getUnidadeOperacional().getId().equals(requestDTO.getUnidadeOperacionalId())) {
            pgrEntity.setUnidadeOperacional(findUnidadeById(requestDTO.getUnidadeOperacionalId()));
        }

        updateMapaDeRiscos(pgrEntity, requestDTO);
        updatePlanoDeAcao(pgrEntity, requestDTO);
        System.out.println("-----------------------");
        return pgrMapper.toDto(pgrRepository.save(pgrEntity));
    }

    @Override
    @Transactional
    public void deletePgr(Long id) {
        PgrEntity pgrEntity = pgrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + id));
        deleteFile(pgrEntity.getConteudoCapa());
        pgrRepository.deleteById(id);
    }

    private void updateMapaDeRiscos(PgrEntity pgrEntity, PgrRequestDTO requestDTO) {
        pgrEntity.getMapaRiscos().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getMapaRiscos())) {
            requestDTO.getMapaRiscos().forEach(mapaDto -> {
                FuncaoEntity funcao = findFuncaoById(mapaDto.getFuncaoId());
                Set<RiscoCatalogoEntity> riscos = new HashSet<>();
                if (!CollectionUtils.isEmpty(mapaDto.getRiscoCatalogoIds())) {
                    riscos = new HashSet<>(riscoCatalogoRepository.findAllById(mapaDto.getRiscoCatalogoIds()));
                }

                PgrMapaRiscoFuncaoEntity mapaRisco = PgrMapaRiscoFuncaoEntity.builder()
                        .funcao(funcao)
                        .riscosDoCatalogo(riscos)
                        
                        .build();
                pgrEntity.addMapaRisco(mapaRisco);
            });
        }
    }

    private void updatePgrFields(PgrEntity entity, PgrRequestDTO dto) {
        entity.setTermoValidacao(dto.getTermoValidacao());
        entity.setDocumentoBase(dto.getDocumentoBase());
        entity.setResponsavelEmpresa(dto.getResponsavelEmpresa());
        entity.setDataDocumento(dto.getDataDocumento());
        entity.setDataRevisao(dto.getDataRevisao());
        entity.setTermoCiencia(dto.getTermoCiencia());
        entity.setPlanoAcaoMetodologia(dto.getPlanoAcaoMetodologia());
        entity.setPlanoAcaoOrientacoes(dto.getPlanoAcaoOrientacoes());
        entity.setConsideracoesFinais(dto.getConsideracoesFinais());
    }

    private UnidadeOperacionalEntity findUnidadeById(Long id) {
        return unidadeOperacionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + id));
    }

    private FuncaoEntity findFuncaoById(Long id) {
        return funcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + id));
    }

    private RiscoCatalogoEntity findRiscoById(Long id) {
        return riscoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Risco do Catálogo não encontrado com ID: " + id));
    }

    private void updatePlanoDeAcao(PgrEntity pgrEntity, PgrRequestDTO requestDTO) {
        pgrEntity.getPlanoAcaoRiscos().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getPlanoAcaoRiscos())) {
            requestDTO.getPlanoAcaoRiscos().forEach(planoDto -> {
                RiscoCatalogoEntity risco = findRiscoById(planoDto.getRiscoId());
                PlanoAcaoRiscoEntity planoAcao = PlanoAcaoRiscoEntity.builder()
                        .risco(risco)
                        .acao(planoDto.getAcao())
                        .responsavel(planoDto.getResponsavel())
                        .prazo(planoDto.getPrazo())
                        .status(planoDto.getStatus())
                        .build();
                pgrEntity.addPlanoAcaoRisco(planoAcao);
            });
        }
    }

    private String saveFile(MultipartFile file) {
        try {
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID() + extension;
            Files.copy(file.getInputStream(), this.root.resolve(filename));
            return filename;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar o arquivo. Erro: " + e.getMessage());
        }
    }

    private void deleteFile(String filename) {
        if (filename == null || filename.isBlank()) {
            return;
        }
        try {
            Files.deleteIfExists(this.root.resolve(filename));
        } catch (IOException e) {
            System.err.println("Falha ao deletar o arquivo: " + filename + " Erro: " + e.getMessage());
        }
    }

    public byte[] gerarRelatorioPdf(Long pgrId) throws JRException, IOException {
        PgrEntity pgr = pgrRepository.findById(pgrId)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + pgrId));

        // Cria o DTO principal que servirá de fonte de dados
        PgrRelatorioDTO pgrRelatorioDTO = new PgrRelatorioDTO(pgr, unidadeOperacionalMapper, mapaRiscoMapper, planoAcaoMapper);

        // Carrega o template .jrxml (você precisará criá-lo)
        InputStream reportStream = new ClassPathResource("relatorios/Relatorio_PGR.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Parâmetros para o relatório (logo, datas, etc.)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("LOGO_PATH", "classpath:imagens/logo.png");
        parameters.put("DATA_GERACAO", new Date());

        // A fonte de dados é uma coleção contendo apenas nosso DTO principal
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(pgrRelatorioDTO));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}