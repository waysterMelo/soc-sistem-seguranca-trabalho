package com.ocupacional.soc.Controllers.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento.TreinamentoRealizadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treinamentos")
@RequiredArgsConstructor
public class TreinamentoRealizadoController {

    private final TreinamentoRealizadoService treinamentoRealizadoService;

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoRealizadoResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(treinamentoRealizadoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TreinamentoRealizadoResponseDTO>> findAll(
            Pageable pageable,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) Long unidadeId) {
        return ResponseEntity.ok(treinamentoRealizadoService.findAll(pageable, empresaId, unidadeId));
    }

    @PostMapping("/realizar")
    public ResponseEntity<TreinamentoRealizadoResponseDTO> create(@Valid @RequestBody TreinamentoRealizadoRequestDTO dto) {
        return new ResponseEntity<>(treinamentoRealizadoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/realizar/{id}")
    public ResponseEntity<TreinamentoRealizadoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TreinamentoRealizadoRequestDTO dto) {
        return ResponseEntity.ok(treinamentoRealizadoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        treinamentoRealizadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}