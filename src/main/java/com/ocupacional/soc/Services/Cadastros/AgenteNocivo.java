package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.AgenteNocivoCatalogoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgenteNocivo {

    Page<AgenteNocivoCatalogoResponseDTO> listarAgentes(Pageable pageable);

}
