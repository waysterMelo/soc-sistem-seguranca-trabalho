package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Nr16AnexoDTO;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Nr16AnexoMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.*;
import com.ocupacional.soc.Services.SegurancaTrabalho.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Nr16AnexoServiceImpl implements Nr16AnexoService {

    private final Nr16AnexoRepository repository;
    private final Nr16AnexoMapper nr16AnexoMapper;

    @Override
    public List<Nr16AnexoDTO> findAll() {
        return repository.findAll().stream().map(nr16AnexoMapper::toDto).collect(Collectors.toList());
    }
}