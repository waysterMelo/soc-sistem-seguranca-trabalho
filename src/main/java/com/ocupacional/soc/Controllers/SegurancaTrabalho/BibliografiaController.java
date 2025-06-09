package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.BibliografiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bibliografias")
@RequiredArgsConstructor
public class BibliografiaController {

    private final BibliografiaService service;

    @GetMapping
    public ResponseEntity<Page<BibliografiaResponseDTO>> findAll(
            Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliografiaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BibliografiaResponseDTO> create(@Valid @RequestBody BibliografiaRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliografiaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BibliografiaRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}