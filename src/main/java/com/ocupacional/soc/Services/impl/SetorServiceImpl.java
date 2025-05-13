package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SetorRequestDTO;
import com.ocupacional.soc.Dto.SetorResponseDTO;
import com.ocupacional.soc.Entities.EmpresaEntity;
import com.ocupacional.soc.Entities.SetorEntity;
import com.ocupacional.soc.Entities.UnidadeOperacionalEntity;
import com.ocupacional.soc.Mapper.SetorMapper;
import com.ocupacional.soc.Repositories.EmpresaRepository;
import com.ocupacional.soc.Repositories.SetorRepository;
import com.ocupacional.soc.Repositories.UnidadeOperacionalRepository;
import com.ocupacional.soc.Services.SetorService;
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
        System.out.println("DEBUG: Iniciando criar setor. DTO empresaId: " + dto.getEmpresaId()); // Ponto de Debug 1

        EmpresaEntity empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> {
                    // Log antes de lançar a exceção pode ser útil
                    System.err.println("DEBUG: Empresa não encontrada com ID: " + dto.getEmpresaId());
                    return new EntityNotFoundException("Empresa não encontrada com ID: " + dto.getEmpresaId());
                });

        // Ponto de Debug 2: Verificar a empresa recuperada
        if (empresa != null) {
            System.out.println("DEBUG: Empresa recuperada: ID=" + empresa.getId() + ", Nome=" + empresa.getNomeFantasia());
            if (empresa.getId() == null) {
                System.err.println("DEBUG: ALERTA! ID da empresa recuperada é NULL!");
            }
        } else {
            System.err.println("DEBUG: ALERTA! Objeto Empresa é NULL após findById (não deveria acontecer devido ao orElseThrow).");
        }


        setorRepository.findByNomeAndEmpresaId(dto.getNome(), dto.getEmpresaId()).ifPresent(existingSetor -> {
            throw new IllegalArgumentException("Setor com o nome '" + dto.getNome() + "' já existe na empresa " + (empresa != null ? empresa.getNomeFantasia() : "ID: "+dto.getEmpresaId()) + ".");
        });

        SetorEntity setorEntity = setorMapper.toEntity(dto);
        // Ponto de Debug 3: Verificar setorEntity.getEmpresa() ANTES de setar
        System.out.println("DEBUG: setorEntity.getEmpresa() ANTES de setar: " + setorEntity.getEmpresa());


        setorEntity.setEmpresa(empresa); // Linha crucial!

        // Ponto de Debug 4: Verificar setorEntity.getEmpresa() DEPOIS de setar
        if (setorEntity.getEmpresa() != null) {
            System.out.println("DEBUG: setorEntity.getEmpresa() DEPOIS de setar: ID=" + setorEntity.getEmpresa().getId());
            if (setorEntity.getEmpresa().getId() == null) {
                System.err.println("DEBUG: ALERTA! ID da empresa em setorEntity é NULL DEPOIS de setar!");
            }
        } else {
            System.err.println("DEBUG: ALERTA! setorEntity.getEmpresa() é NULL DEPOIS de setar!");
        }

        try {
            setorEntity = setorRepository.save(setorEntity);
        } catch (Exception e) {
            System.err.println("DEBUG: Erro ao salvar setorEntity: " + e.getMessage());
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
        return setorMapper.toResponseDtoList(setorRepository.findAll());
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

        Optional<SetorEntity> existingSetorWithNewName = setorRepository.findByNomeAndEmpresaId(dto.getNome(), setorEntity.getEmpresa().getId());
        if (existingSetorWithNewName.isPresent() && !existingSetorWithNewName.get().getId().equals(id)) {
            throw new IllegalArgumentException("Outro setor já utiliza o nome '" + dto.getNome() + "' na empresa " + setorEntity.getEmpresa().getNomeFantasia() + ".");
        }

        setorMapper.updateEntityFromDto(dto, setorEntity);

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
