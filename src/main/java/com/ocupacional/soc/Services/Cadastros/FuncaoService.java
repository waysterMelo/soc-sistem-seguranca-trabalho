package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncaoService {

    FuncaoResponseDTO criarFuncao(FuncaoRequestDTO funcaoRequestDTO);
    FuncaoResponseDTO buscarFuncaoPorId(Long id);
    Page<FuncaoResponseDTO> listarFuncoes(Pageable pageable);
    FuncaoResponseDTO atualizarFuncao(Long id, FuncaoRequestDTO funcaoRequestDTO);
    void deletarFuncao(Long id);
}
