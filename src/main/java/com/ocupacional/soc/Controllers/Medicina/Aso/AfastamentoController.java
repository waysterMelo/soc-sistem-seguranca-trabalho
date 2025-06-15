package com.ocupacional.soc.Controllers.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Aso.AfastamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/afastamentos")
@RequiredArgsConstructor
public class AfastamentoController {

    private final AfastamentoService service;

    @PostMapping
    public ResponseEntity<AfastamentoResponseDTO> create(@Valid @RequestBody AfastamentoRequestDTO dto) {
        return new ResponseEntity<>(service.createAfastamento(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AfastamentoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AfastamentoRequestDTO dto) {
        return ResponseEntity.ok(service.updateAfastamento(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfastamentoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AfastamentoListDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataInicio") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAfastamento(id);
        return ResponseEntity.noContent().build();
    }
}