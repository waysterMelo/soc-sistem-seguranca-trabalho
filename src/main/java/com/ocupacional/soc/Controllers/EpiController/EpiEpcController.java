package com.ocupacional.soc.Controllers.EpiController;

import com.ocupacional.soc.Dto.EpiDto.EpiEpcRequestDTO;
import com.ocupacional.soc.Dto.EpiDto.EpiEpcResponseDTO;
import com.ocupacional.soc.Services.EpiService.EpiEpcService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/epi-epc")
@RequiredArgsConstructor
public class EpiEpcController {

    private final EpiEpcService epiEpcService;

    @GetMapping
    public ResponseEntity<Page<EpiEpcResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) String search) {
        Page<EpiEpcResponseDTO> page = epiEpcService.findAll(pageable, empresaId, search);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpiEpcResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(epiEpcService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EpiEpcResponseDTO> create(@Valid @RequestBody EpiEpcRequestDTO dto) {
        EpiEpcResponseDTO createdDto = epiEpcService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpiEpcResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EpiEpcRequestDTO dto) {
        EpiEpcResponseDTO updatedDto = epiEpcService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        epiEpcService.delete(id);
        return ResponseEntity.noContent().build();
    }
}