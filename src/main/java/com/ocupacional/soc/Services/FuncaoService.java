package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface FuncaoService {

    FuncaoResponseDTO criarFuncao(FuncaoRequestDTO funcaoRequestDTO);
    FuncaoResponseDTO buscarFuncaoPorId(Long id);
    Page<FuncaoResponseDTO> listarFuncoes(Pageable pageable);
    FuncaoResponseDTO atualizarFuncao(Long id, FuncaoRequestDTO funcaoRequestDTO);
    void deletarFuncao(Long id);
}
