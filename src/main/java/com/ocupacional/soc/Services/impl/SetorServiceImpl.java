package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.SetorComFuncoesDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoMapper;
import com.ocupacional.soc.Mapper.Cadastros.SetorMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.SetorRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Services.Cadastros.SetorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SetorServiceImpl implements SetorService {


    private final SetorRepository setorRepository;
    private final SetorMapper setorMapper;
    private final EmpresaRepository empresaRepository;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final FuncaoRepository funcaoRepository;
    private final FuncaoMapper funcaoMapper;

    public SetorServiceImpl(SetorRepository setorRepository, SetorMapper setorMapper, EmpresaRepository empresaRepository,
                            UnidadeOperacionalRepository unidadeOperacionalRepository, FuncaoRepository funcaoRepository,
                            FuncaoMapper funcaoMapper) {
        this.setorRepository = setorRepository;
        this.setorMapper = setorMapper;
        this.empresaRepository = empresaRepository;
        this.unidadeOperacionalRepository = unidadeOperacionalRepository;
        this.funcaoRepository = funcaoRepository;
        this.funcaoMapper = funcaoMapper;
    }


    @Override
    @Transactional
    public SetorResponseDTO criar(SetorRequestDTO dto) {

        EmpresaEntity empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> {
                    return new EntityNotFoundException("Empresa não encontrada com ID: " + dto.getEmpresaId());
                });

        UnidadeOperacionalEntity unidadeOperacional = null;
        if (dto.getUnidadeOperacionalId() != null) {
            unidadeOperacional = unidadeOperacionalRepository.findById(dto.getUnidadeOperacionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));
        }


        setorRepository.findByNomeAndEmpresaId(dto.getNome(), dto.getEmpresaId()).ifPresent(existingSetor -> {
            throw new IllegalArgumentException(STR."Setor com o nome '\{dto.getNome()}' já existe na empresa \{empresa != null
                    ? empresa.getNomeFantasia() : STR."ID: \{dto.getEmpresaId()}"}.");
        });

        SetorEntity setorEntity = setorMapper.toEntity(dto);
        setorEntity.setStatus(StatusEmpresa.ATIVO);
        setorEntity.setEmpresa(empresa);
        setorEntity.setUnidadeOperacional(unidadeOperacional);


        try {
            setorEntity = setorRepository.save(setorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return setorMapper.toResponseDto(setorEntity);
    }

    @Override
    @Transactional
    public Optional<SetorResponseDTO> buscarPorId(Long id) {
        return setorRepository.findById(id).map(setorMapper::toResponseDto);
    }

    @Override
    @Transactional
    public List<SetorResponseDTO> listarTodos() {
        List<SetorEntity> setores = setorRepository.findAll();

        setores.forEach(setor -> {
            if (setor.getUnidadeOperacional() != null &&
                    setor.getUnidadeOperacional().getId() == 0) {
                setor.setUnidadeOperacional(null);
            }
        });
        return setorMapper.toResponseDtoList(setores);

    }

    @Override
    public List<SetorResponseDTO> listarPorEmpresa(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId);
        }
        List<SetorEntity> setores = setorRepository.findByEmpresaId(empresaId);
        return setorMapper.toResponseDtoList(setores);
    }

    @Override
    @Transactional
    public SetorResponseDTO atualizar(Long id, SetorRequestDTO dto) {

        SetorEntity setorEntity = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com ID: " + id));

        if (!setorEntity.getEmpresa().getId().equals(dto.getEmpresaId())) {
        }

        UnidadeOperacionalEntity unidadeOperacional = null;
        if (dto.getUnidadeOperacionalId() != null) {
            unidadeOperacional = unidadeOperacionalRepository.findById(dto.getUnidadeOperacionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));
        }

        Optional<SetorEntity> existingSetorWithNewName = setorRepository.findByNomeAndEmpresaId(dto.getNome(), setorEntity.getEmpresa().getId());
        if (existingSetorWithNewName.isPresent() && !existingSetorWithNewName.get().getId().equals(id)) {
            throw new IllegalArgumentException("Outro setor já utiliza o nome '" + dto.getNome() + "' na empresa " + setorEntity.getEmpresa().getNomeFantasia() + ".");
        }

        setorMapper.updateEntityFromDto(dto, setorEntity);
        setorEntity.setUnidadeOperacional(unidadeOperacional);

        setorEntity = setorRepository.save(setorEntity);
        return setorMapper.toResponseDto(setorEntity);

    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new EntityNotFoundException("Setor não encontrado com ID:" + id);
        }


        if (unidadeOperacionalRepository.existsBySetores_Id(id)) {
            throw new IllegalStateException("Setor não pode ser deletado pois está em uso por uma ou mais unidades operacionais.");
        }

        if (funcaoRepository.existsBySetorId(id)) {
            throw new IllegalStateException("Não é possível excluir o setor, pois ele está sendo utilizado por uma ou mais Funções.");
        }


        setorRepository.deleteById(id);
    }

    @Override
    public Optional<SetorResponseDTO> buscarPorNomeEEmpresa(String nome, Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId + ", portanto não é possível buscar o setor '" + nome + "'.");
        }
        return setorRepository.findByNomeAndEmpresaId(nome, empresaId).map(setorMapper::toResponseDto);
    }

    @Override
    public List<SetorComFuncoesDTO> listarSetoresComFuncoesPorEmpresa(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId);
        }

        List<SetorEntity> setores = setorRepository.findByEmpresaId(empresaId);
        
        return setores.stream().map(setor -> {
            SetorComFuncoesDTO dto = new SetorComFuncoesDTO();
            dto.setId(setor.getId());
            dto.setNome(setor.getNome());
            dto.setDescricao(setor.getDescricao());
            dto.setEmpresa(setorMapper.toResponseDto(setor).getEmpresa());
            dto.setUnidadeOperacional(setorMapper.toResponseDto(setor).getUnidadeOperacional());
            
            // Buscar funções do setor
            Pageable pageable = PageRequest.of(0, 100); // Limite de 100 funções por setor
            Page<FuncaoEntity> funcoesPage = funcaoRepository.findAllBySetorId(setor.getId(), pageable);
            List<FuncaoEntity> funcoes = funcoesPage.getContent();
            
            // Converter funções para DTO
            List<com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO> funcoesDto = funcoes.stream()
                    .map(funcaoMapper::entityToResponseDTO)
                    .collect(Collectors.toList());
            
            dto.setFuncoes(funcoesDto);
            return dto;
        }).collect(Collectors.toList());
    }
}