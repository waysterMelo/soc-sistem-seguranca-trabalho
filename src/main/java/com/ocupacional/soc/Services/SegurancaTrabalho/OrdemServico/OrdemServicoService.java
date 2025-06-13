package com.ocupacional.soc.Services.SegurancaTrabalho.OrdemServico;

import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdemServicoService {
    Page<OrdemServicoListDTO> findAll(Pageable pageable, Long funcionarioId, String search);
    OrdemServicoResponseDTO findById(Long id);
    OrdemServicoResponseDTO create(OrdemServicoRequestDTO dto);
    OrdemServicoResponseDTO update(Long id, OrdemServicoRequestDTO dto);
    void delete(Long id);
}