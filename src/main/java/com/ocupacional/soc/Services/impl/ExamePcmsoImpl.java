package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Mapper.Cadastros.ExameCatalogoMapper;
import com.ocupacional.soc.Repositories.Cadastros.ExameCatalogoRepository;
import com.ocupacional.soc.Services.Cadastros.ExamePcmsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamePcmsoImpl implements ExamePcmsoService {

    private final ExameCatalogoRepository repository;
    private final ExameCatalogoMapper mapper;

    @Override
    public Page<ExameCatalogoResponseDTO> listarExamesPorDescricao(String nome, Pageable pageable) {
        Page<ExameCatalogoEntity> lista = repository.findByNomeExame(nome, pageable);
        return lista.map(mapper::toResponseDto);
    }

    @Override
    public List<ExameCatalogoResponseDTO> listarTodos() {
        List<ExameCatalogoEntity> lista = repository.findAll();
        return lista.stream().map(mapper::toResponseDto).toList();
    }
}
