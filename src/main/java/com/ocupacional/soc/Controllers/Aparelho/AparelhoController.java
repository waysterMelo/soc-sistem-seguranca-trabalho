package com.ocupacional.soc.Controllers.Aparelho;


import com.ocupacional.soc.Dto.Aparelhos.AparelhoRequestDTO;
import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Services.Aparelhos.AparelhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/aparelhos")
@RequiredArgsConstructor
public class AparelhoController {

    private final AparelhoService service;

    @GetMapping
    public ResponseEntity<Page<AparelhoResponseDTO>> findAll(
            @PageableDefault(size = 5, sort = "descricao") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AparelhoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AparelhoResponseDTO> create(
            @Valid @RequestPart("aparelho") AparelhoRequestDTO dto,
            @RequestPart(value = "certificado", required = false) MultipartFile certificado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto, certificado));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AparelhoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestPart("aparelho") AparelhoRequestDTO dto,
            @RequestPart(value = "certificado", required = false) MultipartFile certificado) {
        return ResponseEntity.ok(service.update(id, dto, certificado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}