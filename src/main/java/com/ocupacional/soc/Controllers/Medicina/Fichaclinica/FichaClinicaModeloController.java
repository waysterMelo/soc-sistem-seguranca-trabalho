package com.ocupacional.soc.Controllers.Medicina.Fichaclinica;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloListDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloRequestDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Services.Medicina.Fichaclinica.FichaClinicaModeloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichaclinica-modelos")
@RequiredArgsConstructor
public class FichaClinicaModeloController {

    private final FichaClinicaModeloService service;

    @PostMapping
    public ResponseEntity<FichaClinicaResponseDTO> create(@Valid @RequestBody FichaClinicaModeloRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaClinicaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody FichaClinicaModeloRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaClinicaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FichaClinicaModeloListDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}