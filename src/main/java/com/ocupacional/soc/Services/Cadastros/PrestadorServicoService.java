package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoRequestDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface PrestadorServicoService {

    PrestadorServicoResponseDTO create(PrestadorServicoRequestDTO dto);

    PrestadorServicoResponseDTO update(Long id, PrestadorServicoRequestDTO dto);

    Page<PrestadorServicoResponseDTO> list(String filtro, Pageable pageable);

    PrestadorServicoResponseDTO get(Long id);

    void delete(Long id);

    void importar(MultipartFile file);
}
