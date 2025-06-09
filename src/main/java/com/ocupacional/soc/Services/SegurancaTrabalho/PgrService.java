package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PgrService {
    PgrResponseDTO createPgr(PgrRequestDTO requestDTO);
    PgrResponseDTO getPgrById(Long id);
    Page<PgrResponseDTO> getAllPgrsByEmpresa(Long empresaId, Pageable pageable);
    PgrResponseDTO updatePgr(Long id, PgrRequestDTO requestDTO);
    void deletePgr(Long id);
}