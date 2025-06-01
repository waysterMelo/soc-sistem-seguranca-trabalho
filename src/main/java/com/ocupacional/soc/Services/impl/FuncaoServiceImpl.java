package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.*;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.FuncaoAgenteNocivoMapper;
import com.ocupacional.soc.Mapper.FuncaoExamePcmsoMapper;
import com.ocupacional.soc.Mapper.FuncaoMapper;
import com.ocupacional.soc.Mapper.RiscoTrabalhistaPgrMapper;
import com.ocupacional.soc.Repositories.*;
import com.ocupacional.soc.Services.FuncaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper;
    private final FuncaoAgenteNocivoMapper funcaoAgenteNocivoMapper;
    private final FuncaoExamePcmsoMapper funcaoExamePcmsoMapper;

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
            throw e; // Permite que o GlobalExceptionHandler trate
        }
    }

    @Override
    @Transactional // ReadOnly não é apropriado para busca que pode popular lazy collections para o mapper
    public FuncaoResponseDTO buscarFuncaoPorId(Long id) {
        log.debug("Buscando função por ID: {}", id);
        FuncaoEntity funcaoEntity = findFuncaoById(id);
        // Aqui, as coleções lazy serão carregadas se o mapper tentar acessá-las.
        // Se houver problemas com LazyInitializationException, pode ser necessário usar @EntityGraph
        // ou buscar explicitamente as coleções antes de mapear.
        // Por ora, vamos confiar que o mapper dentro da transação resolva.
        return funcaoMapper.entityToResponseDTO(funcaoEntity);
    }


    @Override
    @Transactional // Mesma observação do buscarPorId sobre lazy loading e transações
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

    private FuncaoEntity buildFuncaoEntity(FuncaoEntity funcaoExistente, FuncaoRequestDTO requestDTO) {
        FuncaoEntity funcaoEntity;
        if (funcaoExistente == null) { // Criação
            funcaoEntity = funcaoMapper.requestDtoToEntity(requestDTO);
        } else { // Atualização
            funcaoEntity = funcaoExistente;
            // Atualizar campos básicos da FuncaoEntity a partir do DTO
            // O MapStruct não faz merge por padrão, então fazemos manualmente ou usamos um método de update no mapper
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
        funcaoEntity.getProfissionaisResponsaveis().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getProfissionaisResponsaveis())) {
            requestDTO.getProfissionaisResponsaveis().forEach(profDTO -> {
                ProfissionalRegistroAmbientalEntity profissionalEntity = new ProfissionalRegistroAmbientalEntity();
                profissionalEntity.setFuncionario(findFuncionarioById(profDTO.getFuncionarioId()));
                funcaoEntity.addProfissionalResponsavel(profissionalEntity);
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
                funcaoExameEntity.setExameCatalogo(catalogoExame); // Garantir que a entidade completa está lá
                // O tipoExame, periodicidade e obrigatorio já são mapeados pelo funcaoExamePcmsoMapper
                funcaoEntity.addExamePcmso(funcaoExameEntity);
            });
        }
        return funcaoEntity;
    }


    @Override
    @Transactional
    public void deletarFuncao(Long id) {
        log.info("Iniciando exclusão da função ID: {}", id);
        if (!funcaoRepository.existsById(id)) {
            log.warn("Tentativa de deletar função não existente com ID: {}", id);
            throw new ResourceNotFoundException("Função não encontrada com ID: " + id);
        }
        funcaoRepository.deleteById(id);
        log.info("Função excluída com sucesso. ID: {}", id);
    }

    // Métodos auxiliares find...
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