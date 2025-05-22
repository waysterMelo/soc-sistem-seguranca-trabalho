package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.*;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.FuncaoMapper;
import com.ocupacional.soc.Mapper.RiscoTrabalhistaPgrMapper;
import com.ocupacional.soc.Repositories.*;
import com.ocupacional.soc.Services.FuncaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuncaoServiceImpl implements FuncaoService {

    private final FuncaoRepository funcaoRepository;
    private final EmpresaRepository empresaRepository;
    private final SetorRepository setorRepository;
    private final CboRepository cboRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncaoMapper funcaoMapper;
    private final RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper;

    @Override
    @Transactional
    public FuncaoResponseDTO criarFuncao(FuncaoRequestDTO requestDTO) {
        log.info("Iniciando criação de nova função: {}", requestDTO.getNome());

        try {
            FuncaoEntity funcaoEntity = buildFuncaoEntity(requestDTO);
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
        return funcaoMapper.entityToResponseDTO(findFuncaoById(id));
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
            updateFuncaoEntity(funcaoExistente, requestDTO);
            FuncaoEntity updatedEntity = funcaoRepository.save(funcaoExistente);

            log.info("Função atualizada com sucesso. ID: {}", id);
            return funcaoMapper.entityToResponseDTO(updatedEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar função ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deletarFuncao(Long id) {
        log.info("Iniciando exclusão da função ID: {}", id);

        if (!funcaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Função não encontrada com ID: " + id);
        }

        funcaoRepository.deleteById(id);
        log.info("Função excluída com sucesso. ID: {}", id);
    }

    private FuncaoEntity buildFuncaoEntity(FuncaoRequestDTO requestDTO) {
        FuncaoEntity funcaoEntity = funcaoMapper.requestDtoToEntity(requestDTO);

        // Configuração das associações obrigatórias
        funcaoEntity.setEmpresa(findEmpresaById(requestDTO.getEmpresaId()));
        funcaoEntity.setSetor(findSetorById(requestDTO.getSetorId()));
        funcaoEntity.setCbo(findCboById(requestDTO.getCboId()));

        // Configuração dos riscos PGR
        processRiscosPGR(funcaoEntity, requestDTO);

        // Configuração dos profissionais responsáveis
        processProfissionaisResponsaveis(funcaoEntity, requestDTO);

        return funcaoEntity;
    }

    public void updateFuncaoEntity(FuncaoEntity funcaoExistente, FuncaoRequestDTO requestDTO){
        updateBasicFields(funcaoExistente, requestDTO);

        funcaoExistente.setEmpresa(findEmpresaById(requestDTO.getEmpresaId()));
        funcaoExistente.setSetor(findSetorById(requestDTO.getSetorId()));
        funcaoExistente.setCbo(findCboById(requestDTO.getCboId()));

        funcaoExistente.getRiscosPGR().clear();
        processRiscosPGR(funcaoExistente, requestDTO);

        funcaoExistente.getProfissionaisResponsaveis().clear();
        processProfissionaisResponsaveis(funcaoExistente, requestDTO);
    }









    private void updateBasicFields(FuncaoEntity funcaoEntity, FuncaoRequestDTO requestDTO) {
        Optional.ofNullable(requestDTO.getNome())
                .ifPresent(funcaoEntity::setNome);

        Optional.ofNullable(requestDTO.getQuantidadeFuncionarios())
                .ifPresent(funcaoEntity::setQuantidadeFuncionarios);

        Optional.ofNullable(requestDTO.getDescricaoFuncao())
                .ifPresent(funcaoEntity::setDescricao);

        Optional.ofNullable(requestDTO.getTipoGfip())
                .ifPresent(funcaoEntity::setTipoGfip);

        Optional.ofNullable(requestDTO.getAtividadesInsalubres())
                .ifPresent(funcaoEntity::setAtividadesInsalubres);

        Optional.ofNullable(requestDTO.getInformacoesComplementaresRegistrosAmbientais())
                .ifPresent(funcaoEntity::setInformacoesComplementaresRegistrosAmbientais);
    }

    private void processRiscosPGR(FuncaoEntity funcaoEntity, FuncaoRequestDTO requestDTO) {
        if (!CollectionUtils.isEmpty(requestDTO.getRiscosPGR())) {
            requestDTO.getRiscosPGR().forEach(riscoDTO -> {
                try {
                    funcaoEntity.addRiscoPGR(riscoTrabalhistaPgrMapper.toEntity(riscoDTO));
                } catch (Exception e) {
                    log.error("Erro ao processar risco PGR: {}", e.getMessage());
                    throw new RuntimeException("Erro ao processar riscos PGR", e);
                }
            });
        }
    }

    public void processProfissionaisResponsaveis(FuncaoEntity funcaoEntity, FuncaoRequestDTO requestDTO){
        if (!CollectionUtils.isEmpty(requestDTO.getProfissionaisResponsaveis())){
            requestDTO.getProfissionaisResponsaveis().forEach(profDTO -> {
                try{
                    ProfissionalRegistroAmbientalEntity profissionalRegistroAmbientalEntity =
                            createProfissionalEntity(profDTO.getFuncionarioId());
                    funcaoEntity.addProfissionalResponsavel(profissionalRegistroAmbientalEntity);
                } catch (RuntimeException e) {
                    log.error("Erro ao processar profissional responsavel ID {}: {}", profDTO
                            .getFuncionarioId(), e.getMessage());
                    throw new RuntimeException("Erro ao processar profissionais responsáveis");
                }
            });
        }
    }

    public ProfissionalRegistroAmbientalEntity createProfissionalEntity(Long funcionarioId){
        ProfissionalRegistroAmbientalEntity profissionalEntity = new ProfissionalRegistroAmbientalEntity();
        profissionalEntity.setFuncionario(findFuncionarioById(funcionarioId));
        return profissionalEntity;
    }



    private EmpresaEntity findEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Empresa não encontrada com ID: {}", id);
                    return new ResourceNotFoundException("Empresa não encontrada com ID: " + id);
                });
    }
    private SetorEntity findSetorById(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Setor não encontrado com ID: {}", id);
                    return new ResourceNotFoundException("Setor não encontrado com ID: " + id);
                } );
    }
    private CboEntity findCboById(Long id) {
        return cboRepository.findById(id)
                .orElseThrow(()-> {
                    log.error("CBO não encontrado com ID: {}", id);
                    return new ResourceNotFoundException("CBO não encontrado com ID: " + id);
                });
    }
    private FuncionarioEntity findFuncionarioById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Funcionário não encontrado com ID: {}", id);
                    return new ResourceNotFoundException("Funcionário não encontrado com ID: " + id);
                });
    }
    private FuncaoEntity findFuncaoById(Long id) {
        return funcaoRepository.findById(id)
                .orElseThrow( () -> {
                    log.error("Função não encontrada com ID: {}", id);
                    return new ResourceNotFoundException("Função não encontrada com ID: " + id);
                } );
    }
}
