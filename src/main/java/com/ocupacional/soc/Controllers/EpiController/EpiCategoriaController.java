package com.ocupacional.soc.Controllers.EpiController;

import com.ocupacional.soc.Dto.EpiDto.EpiCategoriaDTO;
import com.ocupacional.soc.Services.EpiService.EpiCategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/epi-categorias")
@RequiredArgsConstructor
public class EpiCategoriaController {

    private final EpiCategoriaService epiCategoriaService;

    @GetMapping
    public ResponseEntity<List<EpiCategoriaDTO>> findAll() {
        return ResponseEntity.ok(epiCategoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpiCategoriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(epiCategoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EpiCategoriaDTO> create(@Valid @RequestBody EpiCategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(epiCategoriaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpiCategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody EpiCategoriaDTO dto) {
        return ResponseEntity.ok(epiCategoriaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        epiCategoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}