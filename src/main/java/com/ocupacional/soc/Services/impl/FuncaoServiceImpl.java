package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoAgenteNocivoMapper;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoExamePcmsoMapper;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoMapper;
import com.ocupacional.soc.Repositories.Cadastros.*;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.LtcatRepository;
import com.ocupacional.soc.Services.Cadastros.FuncaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Slf4j
@Service
@RequiredArgsConstructor
public class FuncaoServiceImpl implements FuncaoService {

    private final FuncaoRepository funcaoRepository;
    private final EmpresaRepository empresaRepository;
    private final SetorRepository setorRepository;
    private final CboRepository cboRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AgenteNocivoCatalogoRepository agenteNocivoCatalogoRepository;
    private final ExameCatalogoRepository exameCatalogoRepository;
    private final RiscoCatalogoRepository riscoCatalogoRepository;
    private final FuncaoMapper funcaoMapper;
    private final FuncaoAgenteNocivoMapper funcaoAgenteNocivoMapper;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final FuncaoExamePcmsoMapper funcaoExamePcmsoMapper;
    private final LtcatRepository ltcatRepository;

    @Override
    @Transactional
    public FuncaoResponseDTO criarFuncao(FuncaoRequestDTO requestDTO) {
        log.info("Iniciando criação de nova função: {}", requestDTO.getNome());
        try {
            FuncaoEntity funcaoEntity = buildFuncaoEntity(null, requestDTO); // Passar null para id na criação
            FuncaoEntity savedEntity = funcaoRepository.save(funcaoEntity);
            log.info("Função criada com sucesso. ID: {}", savedEntity.getId());
            return funcaoMapper.entityToResponseDTO(savedEntity);
        } catch (Exception e) {
            log.error("Erro ao criar função: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public FuncaoResponseDTO buscarFuncaoPorId(Long id) {
        log.debug("Buscando função por ID: {}", id);
        FuncaoEntity funcaoEntity = findFuncaoById(id);
        return funcaoMapper.entityToResponseDTO(funcaoEntity);
    }


    @Override
    @Transactional
    public Page<FuncaoResponseDTO> listarFuncoes(Pageable pageable) {
        log.debug("Listando funções com paginação: página {}, tamanho {}",
                pageable.getPageNumber(), pageable.getPageSize());
        return funcaoRepository.findAll(pageable).map(funcaoMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public FuncaoResponseDTO atualizarFuncao(Long id, FuncaoRequestDTO requestDTO) {
        log.info("Iniciando atualização da função ID: {}", id);
        try {
            FuncaoEntity funcaoExistente = findFuncaoById(id);
            // O buildFuncaoEntity pode ser adaptado para atualizar uma entidade existente
            buildFuncaoEntity(funcaoExistente, requestDTO);
            FuncaoEntity updatedEntity = funcaoRepository.save(funcaoExistente);
            log.info("Função atualizada com sucesso. ID: {}", id);
            return funcaoMapper.entityToResponseDTO(updatedEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar função ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    private PrestadorServicoEntity findPrestadorServicoById(Long id) {
        return prestadorServicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestador de Serviço não encontrado com ID: " + id));
    }


    private FuncaoEntity buildFuncaoEntity(FuncaoEntity funcaoExistente, FuncaoRequestDTO requestDTO) {
        FuncaoEntity funcaoEntity;
        if (funcaoExistente == null) { // Criação
            funcaoEntity = funcaoMapper.requestDtoToEntity(requestDTO);
        } else { // Atualização
            funcaoEntity = funcaoExistente;

            funcaoEntity.setNome(requestDTO.getNome());
            funcaoEntity.setQuantidadeFuncionarios(requestDTO.getQuantidadeFuncionarios());
            funcaoEntity.setDescricao(requestDTO.getDescricaoFuncao()); // Mapeado de descricaoFuncao
            funcaoEntity.setTipoGfip(requestDTO.getTipoGfip());
            funcaoEntity.setAtividadesInsalubres(requestDTO.getAtividadesInsalubres());
            funcaoEntity.setInformacoesComplementaresRegistrosAmbientais(requestDTO.getInformacoesComplementaresRegistrosAmbientais());
        }

        // Configuração das associações obrigatórias
        funcaoEntity.setEmpresa(findEmpresaById(requestDTO.getEmpresaId()));
        funcaoEntity.setSetor(findSetorById(requestDTO.getSetorId()));
        funcaoEntity.setCbo(findCboById(requestDTO.getCboId()));

        // Limpar e processar coleções para garantir a atualização correta
        // Riscos PGR
        funcaoEntity.getRiscosPGR().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getRiscosPGR())) {
            requestDTO.getRiscosPGR().forEach(riscoDTO -> {
                // Busca o RiscoCatalogoEntity pelo ID fornecido no DTO
                RiscoCatalogoEntity riscoCatalogo = findRiscoCatalogoById(riscoDTO.getRiscoCatalogoId());

                // Cria a entidade de associação RiscoTrabalhistaPgrEntity
                RiscoTrabalhistaPgrEntity pgrEntity = new RiscoTrabalhistaPgrEntity();
                pgrEntity.setRiscoCatalogo(riscoCatalogo);
                // Não precisa mais do riscoTrabalhistaPgrMapper.toEntity(riscoDTO) aqui
                // se o DTO só tem o ID. Se tivesse outros campos, o mapper seria útil.

                funcaoEntity.addRiscoPGR(pgrEntity);
            });
        }

        // Profissionais Responsáveis
        funcaoEntity.getPrestadoresResponsaveis().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getPrestadoresResponsaveis())) {
            requestDTO.getPrestadoresResponsaveis().forEach(prestadorDTO -> {
                PrestadorServicoEntity prestadorEntity = findPrestadorServicoById(prestadorDTO.getPrestadorServicoId());
                funcaoEntity.addPrestadorResponsavel(prestadorEntity);
            });
        }


        // Agentes Nocivos eSocial (Novo)
        funcaoEntity.getAgentesNocivosEsocial().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getAgentesNocivosEsocial())) {
            requestDTO.getAgentesNocivosEsocial().forEach(agenteDTO -> {
                AgenteNocivoCatalogoEntity catalogoEntity = findAgenteNocivoCatalogoById(agenteDTO.getAgenteNocivoCatalogoId());
                FuncaoAgenteNocivoEntity funcaoAgenteEntity = funcaoAgenteNocivoMapper.requestDtoToEntity(agenteDTO);
                funcaoAgenteEntity.setAgenteNocivoCatalogo(catalogoEntity); // Garantir que a entidade completa está lá
                funcaoEntity.addAgenteNocivoEsocial(funcaoAgenteEntity);
            });
        }

        // Exames PCMSO (Novo)
        funcaoEntity.getExamesPcmso().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getExamesPcmso())) {
            requestDTO.getExamesPcmso().forEach(exameDTO -> {
                ExameCatalogoEntity catalogoExame = findExameCatalogoById(exameDTO.getExameCatalogoId());

                FuncaoExamePcmsoEntity funcaoExameEntity = funcaoExamePcmsoMapper.requestDtoToEntity(exameDTO);
                funcaoExameEntity.setExameCatalogo(catalogoExame);
                funcaoEntity.addExamePcmso(funcaoExameEntity);
            });
        }
        return funcaoEntity;
    }


    @Override
    @Transactional
    public void deletarFuncao(Long id) {
        if (!funcaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Função não encontrada com ID: " + id);
        }

        if (ltcatRepository.existsByFuncoes_Id(id)) {
            throw new BusinessException("Não é possível excluir a função, pois ela está sendo utilizada em um ou mais registros de LTCAT.");
        }

        try {
            funcaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {

            throw new BusinessException("Não é possível excluir a função, pois ela está sendo utilizada por outro registro (ex: Funcionário).");
        }
    }

    @Override
    public FuncaoResponseDTO inativarFuncao(Long id) {
        FuncaoEntity funcao = funcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Função não encontrada com id: " + id));
        funcao.setStatus(StatusEmpresa.INATIVO);
        FuncaoEntity funcaoInativada = funcaoRepository.save(funcao);
        return funcaoMapper.entityToResponseDTO(funcaoInativada);
    }

    @Override
    public Page<FuncaoResponseDTO> listarFuncaoPorSetor(Long setorId, Pageable pageable) {
        log.debug("Listando funções por setor ID: {} com paginação: página {}, tamanho {}",
                setorId, pageable.getPageNumber(), pageable.getPageSize());
        Page<FuncaoEntity> funcoes = funcaoRepository.findAllBySetorId(setorId, pageable);
        return funcoes.map(funcaoMapper::entityToResponseDTO);
    }

    private FuncaoEntity findFuncaoById(Long id) {
        return funcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + id));
    }

    private EmpresaEntity findEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + id));
    }

    private SetorEntity findSetorById(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado com ID: " + id));
    }

    private CboEntity findCboById(Long id) {
        return cboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CBO não encontrado com ID: " + id));
    }

    private FuncionarioEntity findFuncionarioById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));
    }

    private AgenteNocivoCatalogoEntity findAgenteNocivoCatalogoById(Long id) {
        return agenteNocivoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agente Nocivo (Catálogo) não encontrado com ID: " + id));
    }

    private ExameCatalogoEntity findExameCatalogoById(Long id) {
        return exameCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame (Catálogo) não encontrado com ID: " + id));
    }

    private RiscoCatalogoEntity findRiscoCatalogoById(Long id) {
        return riscoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Risco (Catálogo) não encontrado com ID: " + id));
    }


}