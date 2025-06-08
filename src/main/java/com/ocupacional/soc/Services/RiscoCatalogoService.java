package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RiscoCatalogoService {
    RiscoCatalogoResponseDTO criarRisco(RiscoCatalogoRequestDTO requestDTO);
    RiscoCatalogoResponseDTO buscarRiscoPorId(Long id);
    Page<RiscoCatalogoResponseDTO> listarRiscos(Pageable pageable, GrupoRisco grupo);
    List<RiscoCatalogoResponseDTO> listarTodosRiscos(GrupoRisco grupo); // Para popular selects, por exemplo
    RiscoCatalogoResponseDTO atualizarRisco(Long id, RiscoCatalogoRequestDTO requestDTO);
    void deletarRisco(Long id);
}