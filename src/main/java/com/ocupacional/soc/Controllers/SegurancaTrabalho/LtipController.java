package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ltip")
@RequiredArgsConstructor
public class LtipController {

    private final LtipService ltipService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LtipResponseDTO> create(@Valid @RequestPart("ltip") LtipRequestDTO dto, @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return new ResponseEntity<>(ltipService.createLtip(dto, imagemCapa), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LtipResponseDTO> update(@PathVariable Long id, @Valid @RequestPart("ltip") LtipRequestDTO dto, @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return ResponseEntity.ok(ltipService.updateLtip(id, dto, imagemCapa));
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