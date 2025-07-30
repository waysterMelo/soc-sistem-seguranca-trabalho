package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.SetorRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Mapper.Cadastros.SetorMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.SetorRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Services.Cadastros.SetorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorServiceImpl implements SetorService {


    private final SetorRepository setorRepository;
    private final SetorMapper setorMapper;
    private final EmpresaRepository empresaRepository;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;

    public SetorServiceImpl(SetorRepository setorRepository, SetorMapper setorMapper, EmpresaRepository empresaRepository, UnidadeOperacionalRepository unidadeOperacionalRepository) {
        this.setorRepository = setorRepository;
        this.setorMapper = setorMapper;
        this.empresaRepository = empresaRepository;
        this.unidadeOperacionalRepository = unidadeOperacionalRepository;
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
            throw new IllegalArgumentException("Setor com o nome '" + dto.getNome() + "' já existe na empresa " + (empresa != null ? empresa.getNomeFantasia() : "ID: "+dto.getEmpresaId()) + ".");
        });

        SetorEntity setorEntity = setorMapper.toEntity(dto);
        setorEntity.setEmpresa(empresa);
        setorEntity.setUnidadeOperacional(unidadeOperacional);


        try {
            setorEntity = setorRepository.save(setorEntity);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace completo do erro no console do servidor
            throw e; // Relança a exceção para ser tratada pelo GlobalExceptionHandler
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

        // ← Adicionar lógica para UnidadeOperacional
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
        setorEntity.setUnidadeOperacional(unidadeOperacional); // ← Adicionar esta linha

        setorEntity = setorRepository.save(setorEntity);
        return setorMapper.toResponseDto(setorEntity);

    }


    @Override
    @Transactional
    public void deletar(Long id) {
        SetorEntity setor = setorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Setor não encontrado com ID:" + id));
        if (unidadeOperacionalRepository.existsById(id)){
            throw new IllegalStateException("Setor não pode ser deletado pois está em uso por uma ou mais unidades operacionais.");
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
}