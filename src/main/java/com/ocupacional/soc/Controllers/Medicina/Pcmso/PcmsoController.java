package com.ocupacional.soc.Controllers.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Pcmso.PcmsoService;
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
@RequestMapping("/pcmso")
@RequiredArgsConstructor
public class PcmsoController {

    private final PcmsoService pcmsoService;

    @GetMapping
    public ResponseEntity<Page<PcmsoListDTO>> findAll(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(pcmsoService.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PcmsoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pcmsoService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PcmsoResponseDTO> create(
            @Valid @RequestPart("pcmso") PcmsoRequestDTO dto,
            @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return new ResponseEntity<>(pcmsoService.create(dto, imagemCapa), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PcmsoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestPart("pcmso") PcmsoRequestDTO dto,
            @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return ResponseEntity.ok(pcmsoService.update(id, dto, imagemCapa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pcmsoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}