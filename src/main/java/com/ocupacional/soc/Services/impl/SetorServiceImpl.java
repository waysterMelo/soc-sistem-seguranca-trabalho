package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SetorRequestDTO;
import com.ocupacional.soc.Dto.SetorResponseDTO;
import com.ocupacional.soc.Entities.SetorEntity;
import com.ocupacional.soc.Mapper.SetorMapper;
import com.ocupacional.soc.Repositories.SetorRepository;
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

    public SetorServiceImpl(SetorRepository setorRepository, SetorMapper setorMapper) {
        this.setorRepository = setorRepository;
        this.setorMapper = setorMapper;
    }


    @Override
    @Transactional
    public SetorResponseDTO criar(SetorRequestDTO dto) {

        setorRepository.findByNome(dto.getNome()).ifPresent(existingSetor -> {
            throw new IllegalArgumentException("Setor com o nome '" + dto.getNome() + "' já existe.");
        });
        SetorEntity setorEntity = setorMapper.toEntity(dto);
        setorEntity = setorRepository.save(setorEntity);
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
    @Transactional
    public SetorResponseDTO atualizar(Long id, SetorRequestDTO dto) {
        SetorEntity setorEntity = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com ID: " + id));

        // Verifica se o novo nome já existe em outro setor
        Optional<SetorEntity> existingSetorWithNewName = setorRepository.findByNome(dto.getNome());
        if (existingSetorWithNewName.isPresent() && !existingSetorWithNewName.get().getId().equals(id)) {
            throw new IllegalArgumentException("Outro setor já utiliza o nome '" + dto.getNome() + "'.");
        }

        setorMapper.updateEntityFromDto(dto, setorEntity);
        setorEntity = setorRepository.save(setorEntity);
        return setorMapper.toResponseDto(setorEntity);
    }

    @Override
    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new EntityNotFoundException("Setor não encontrado com ID: " + id);
        }
        // Adicionar lógica para verificar se o setor está em uso antes de deletar, se necessário
        setorRepository.deleteById(id);
    }

    @Override
    public Optional<SetorResponseDTO> buscarPorNome(String nome) {
        return setorRepository.findByNome(nome).map(setorMapper::toResponseDto);
    }
}
