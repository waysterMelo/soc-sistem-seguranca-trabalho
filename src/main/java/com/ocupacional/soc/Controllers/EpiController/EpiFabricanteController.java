package com.ocupacional.soc.Controllers.EpiController;

import com.ocupacional.soc.Dto.EpiDto.EpiFabricanteDTO;
import com.ocupacional.soc.Services.EpiService.EpiFabricanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/epi-fabricantes")
@RequiredArgsConstructor
public class EpiFabricanteController {

    private final EpiFabricanteService epiFabricanteService;

    @GetMapping
    public ResponseEntity<List<EpiFabricanteDTO>> findAll() {
        return ResponseEntity.ok(epiFabricanteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpiFabricanteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(epiFabricanteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EpiFabricanteDTO> create(@Valid @RequestBody EpiFabricanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(epiFabricanteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpiFabricanteDTO> update(@PathVariable Long id, @Valid @RequestBody EpiFabricanteDTO dto) {
        return ResponseEntity.ok(epiFabricanteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        epiFabricanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}