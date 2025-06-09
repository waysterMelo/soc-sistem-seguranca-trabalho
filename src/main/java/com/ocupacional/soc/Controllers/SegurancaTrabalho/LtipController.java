package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ltip")
@RequiredArgsConstructor
public class LtipController {

    private final LtipService ltipService;

    @PostMapping
    public ResponseEntity<LtipResponseDTO> create(@Valid @RequestBody LtipRequestDTO dto) {
        return new ResponseEntity<>(ltipService.createLtip(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LtipResponseDTO> update(@PathVariable Long id, @Valid @RequestBody LtipRequestDTO dto) {
        return ResponseEntity.ok(ltipService.updateLtip(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LtipResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ltipService.findLtipById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LtipResponseDTO>> findAll(
            Pageable pageable,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) Long setorId,
            @RequestParam(required = false) Long funcaoId) {

        return ResponseEntity.ok(ltipService.findAllLtips(pageable, empresaId, setorId, funcaoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ltipService.deleteLtip(id);
        return ResponseEntity.noContent().build();
    }
}