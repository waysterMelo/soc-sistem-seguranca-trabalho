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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FuncaoServiceImpl implements FuncaoService {

    private final FuncaoRepository funcaoRepository;
    private final EmpresaRepository empresaRepository;
    private final SetorRepository setorRepository;
    private final CboRepository cboRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncaoMapper funcaoMapper;
    private final RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper;

    @Autowired
    public FuncaoServiceImpl(
            FuncaoRepository funcaoRepository,
            EmpresaRepository empresaRepository,
            SetorRepository setorRepository,
            CboRepository cboRepository,
            FuncionarioRepository funcionarioRepository,
            FuncaoMapper funcaoMapper,
            RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper
    ) {
        this.funcaoRepository = funcaoRepository;
        this.empresaRepository = empresaRepository;
        this.setorRepository = setorRepository;
        this.cboRepository = cboRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.funcaoMapper = funcaoMapper;
        this.riscoTrabalhistaPgrMapper = riscoTrabalhistaPgrMapper;
    }

    @Override
    @Transactional
    public FuncaoResponseDTO criarFuncao(FuncaoRequestDTO requestDTO) {
        FuncaoEntity funcaoEntity = funcaoMapper.requestDtoToEntity(requestDTO);
        funcaoEntity.setEmpresa(findEmpresa(requestDTO.getEmpresaId()));
        funcaoEntity.setSetor(findSetor(requestDTO.getSetorId()));
        funcaoEntity.setCbo(findCbo(requestDTO.getCboId()));

        if (requestDTO.getRiscosPGR() != null) {
            requestDTO.getRiscosPGR().forEach(riscoDTO ->
                    funcaoEntity.addRiscoPGR(riscoTrabalhistaPgrMapper.toEntity(riscoDTO))
            );
        }
        if (requestDTO.getProfissionaisResponsaveis() != null) {
            requestDTO.getProfissionaisResponsaveis().forEach(profDTO -> {
                ProfissionalRegistroAmbientalEntity profissionalEntity = new ProfissionalRegistroAmbientalEntity();
                profissionalEntity.setFuncionario(findFuncionario(profDTO.getFuncionarioId()));
                funcaoEntity.addProfissionalResponsavel(profissionalEntity);
            });
        }
        return funcaoMapper.entityToResponseDTO(funcaoRepository.save(funcaoEntity));
    }

    @Override
    @Transactional
    public FuncaoResponseDTO buscarFuncaoPorId(Long id) {
        return funcaoMapper.entityToResponseDTO(findFuncao(id));
    }

    @Override
    @Transactional
    public Page<FuncaoResponseDTO> listarFuncoes(Pageable pageable) {
        return funcaoRepository.findAll(pageable).map(funcaoMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public FuncaoResponseDTO atualizarFuncao(Long id, FuncaoRequestDTO requestDTO) {
        FuncaoEntity funcaoExistente = findFuncao(id);
        funcaoExistente.setNome(requestDTO.getNome());
        funcaoExistente.setQuantidadeFuncionarios(requestDTO.getQuantidadeFuncionarios());
        funcaoExistente.setDescricao(requestDTO.getDescricaoFuncao());
        funcaoExistente.setTipoGfip(requestDTO.getTipoGfip());
        funcaoExistente.setAtividadesInsalubres(requestDTO.getAtividadesInsalubres());
        funcaoExistente.setInformacoesComplementaresRegistrosAmbientais(requestDTO.getInformacoesComplementaresRegistrosAmbientais());
        funcaoExistente.setEmpresa(findEmpresa(requestDTO.getEmpresaId()));
        funcaoExistente.setSetor(findSetor(requestDTO.getSetorId()));
        funcaoExistente.setCbo(findCbo(requestDTO.getCboId()));

        funcaoExistente.getRiscosPGR().clear();
        if (requestDTO.getRiscosPGR() != null) {
            requestDTO.getRiscosPGR().forEach(riscoDTO ->
                    funcaoExistente.addRiscoPGR(riscoTrabalhistaPgrMapper.toEntity(riscoDTO))
            );
        }
        funcaoExistente.getProfissionaisResponsaveis().clear();
        if (requestDTO.getProfissionaisResponsaveis() != null) {
            requestDTO.getProfissionaisResponsaveis().forEach(profDTO -> {
                ProfissionalRegistroAmbientalEntity profissionalEntity = new ProfissionalRegistroAmbientalEntity();
                profissionalEntity.setFuncionario(findFuncionario(profDTO.getFuncionarioId()));
                funcaoExistente.addProfissionalResponsavel(profissionalEntity);
            });
        }
        return funcaoMapper.entityToResponseDTO(funcaoRepository.save(funcaoExistente));
    }

    @Override
    @Transactional
    public void deletarFuncao(Long id) {
        if (!funcaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Função não encontrada com ID: " + id);
        }
        funcaoRepository.deleteById(id);
    }

    private EmpresaEntity findEmpresa(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + id));
    }
    private SetorEntity findSetor(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado com ID: " + id));
    }
    private CboEntity findCbo(Long id) {
        return cboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CBO não encontrado com ID: " + id));
    }
    private FuncionarioEntity findFuncionario(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));
    }
    private FuncaoEntity findFuncao(Long id) {
        return funcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + id));
    }
}
