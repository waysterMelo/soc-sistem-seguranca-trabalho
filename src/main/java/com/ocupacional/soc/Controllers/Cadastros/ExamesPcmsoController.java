package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoResponseDTO;
import com.ocupacional.soc.Services.impl.ExamePcmsoImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/exames")
@RestController
@RequiredArgsConstructor
public class ExamesPcmsoController {

    private final ExamePcmsoImpl service;

    @GetMapping("/buscar")
    public ResponseEntity<Page<ExameCatalogoResponseDTO>>
    listarExamesPorNome(@RequestParam(required = false)
                        String nome, @PageableDefault(size = 10) Pageable pageable) {
        Page<ExameCatalogoResponseDTO> exames = service.listarExamesPorDescricao(nome, pageable);
      return exames.hasContent()
              ? ResponseEntity.ok(exames)
              : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExameCatalogoResponseDTO>> listarTodos(){
        List<ExameCatalogoResponseDTO> result = service.listarTodos();
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }



}
