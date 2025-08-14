package com.ocupacional.soc.Services.Cadastros;


import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamePcmsoService {

    Page<ExameCatalogoResponseDTO> listarExamesPorDescricao(String nome, Pageable pageable);
    List<ExameCatalogoResponseDTO> listarTodos();

}
