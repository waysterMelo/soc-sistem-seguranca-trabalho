package com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TreinamentoRealizadoService {
    TreinamentoRealizadoResponseDTO create(TreinamentoRealizadoRequestDTO dto);
    TreinamentoRealizadoResponseDTO update(Long id, TreinamentoRealizadoRequestDTO dto);
    Page<TreinamentoRealizadoResponseDTO> findAll(Pageable pageable, Long empresaId, Long unidadeId);
    TreinamentoRealizadoResponseDTO getById(Long id);
    void delete(Long id);
}