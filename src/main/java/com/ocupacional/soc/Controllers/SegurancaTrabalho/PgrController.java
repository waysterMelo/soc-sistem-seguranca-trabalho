package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.PgrService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pgr")
@RequiredArgsConstructor
public class PgrController {

    private final PgrService pgrService;

    @PostMapping
    public ResponseEntity<PgrResponseDTO> createPgr(@Valid @RequestBody PgrRequestDTO requestDTO) {
        return new ResponseEntity<>(pgrService.createPgr(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PgrResponseDTO> getPgrById(@PathVariable Long id) {
        return ResponseEntity.ok(pgrService.getPgrById(id));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Page<PgrResponseDTO>> getAllPgrsByEmpresa(
            @PathVariable Long empresaId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pgrService.getAllPgrsByEmpresa(empresaId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PgrResponseDTO> updatePgr(@PathVariable Long id, @Valid @RequestBody PgrRequestDTO requestDTO) {
        return ResponseEntity.ok(pgrService.updatePgr(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePgr(@PathVariable Long id) {
        pgrService.deletePgr(id);
        return ResponseEntity.noContent().build();
    }
}