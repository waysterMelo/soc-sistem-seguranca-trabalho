package com.ocupacional.soc.Controllers.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Aso.MotivoAfastamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motivos-afastamento")
@RequiredArgsConstructor
public class MotivoAfastamentoController {

    private final MotivoAfastamentoService service;

    @PostMapping
    public ResponseEntity<MotivoAfastamentoResponseDTO> create(@Valid @RequestBody MotivoAfastamentoRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotivoAfastamentoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MotivoAfastamentoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotivoAfastamentoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<MotivoAfastamentoResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "descricao") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}