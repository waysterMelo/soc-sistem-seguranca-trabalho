package com.ocupacional.soc.Controllers.Medicina.PcmsoAnalitico;


import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoListDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoResponseDTO;
import com.ocupacional.soc.Services.Medicina.PcmsoAnalitico.PcmsoAnaliticoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pcmso-analitico")
@RequiredArgsConstructor
public class PcmsoAnaliticoController {

    private final PcmsoAnaliticoService service;

    @PostMapping
    public ResponseEntity<PcmsoAnaliticoResponseDTO> create(@Valid @RequestBody PcmsoAnaliticoRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PcmsoAnaliticoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PcmsoAnaliticoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<PcmsoAnaliticoListDTO>> findAll(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PcmsoAnaliticoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}