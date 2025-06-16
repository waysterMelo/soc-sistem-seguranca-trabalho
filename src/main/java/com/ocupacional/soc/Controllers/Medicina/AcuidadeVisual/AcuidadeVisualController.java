package com.ocupacional.soc.Controllers.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualRequestDTO;
import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualResponseDTO;
import com.ocupacional.soc.Services.Medicina.AcuidadeVisual.AcuidadeVisualService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acuidade-visual")
@RequiredArgsConstructor
public class AcuidadeVisualController {

    private final AcuidadeVisualService acuidadeVisualService;

    @GetMapping
    @Operation(summary = "Lista todos os registros de acuidade visual de forma paginada")
    public ResponseEntity<Page<AcuidadeVisualResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataAvaliacao") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(acuidadeVisualService.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um registro de acuidade visual pelo ID")
    public ResponseEntity<AcuidadeVisualResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(acuidadeVisualService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo registro de acuidade visual")
    public ResponseEntity<AcuidadeVisualResponseDTO> create(@Valid @RequestBody AcuidadeVisualRequestDTO dto) {
        return new ResponseEntity<>(acuidadeVisualService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um registro de acuidade visual existente")
    public ResponseEntity<AcuidadeVisualResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AcuidadeVisualRequestDTO dto) {
        return ResponseEntity.ok(acuidadeVisualService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um registro de acuidade visual (soft delete)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        acuidadeVisualService.delete(id);
        return ResponseEntity.noContent().build();
    }
}