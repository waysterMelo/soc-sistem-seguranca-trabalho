package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PgrService {
    PgrResponseDTO createPgr(PgrRequestDTO requestDTO, MultipartFile capa);
    PgrResponseDTO getPgrById(Long id);
    Page<PgrResponseDTO> getAllPgrsByEmpresa(Long empresaId, Pageable pageable);
    Page<PgrResponseDTO> getAllPgrsByEmpresaAndStatus(Long empresaId, StatusEmpresa status, Pageable pageable);
    PgrResponseDTO updatePgr(Long id, PgrRequestDTO requestDTO, MultipartFile capa);
    void deletePgr(Long id);
    PgrResponseDTO inactivatedPgr(Long id);
}