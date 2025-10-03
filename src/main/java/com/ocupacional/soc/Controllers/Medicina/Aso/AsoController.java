package com.ocupacional.soc.Controllers.Medicina.Aso;


import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import com.ocupacional.soc.Services.impl.AsoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/aso")
@RequiredArgsConstructor
@Slf4j
public class AsoController {

    private final AsoServiceImpl asoService;


    @GetMapping
    public ResponseEntity<Page<AsoListDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataEmissao") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(asoService.findAll(pageable, search));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AsoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(asoService.findById(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<AsoListDTO>> findByFuncionarioId(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(asoService.findByFuncionarioId(funcionarioId));
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AsoResponseDTO> create(@RequestPart("aso") AsoRequestDTO dto,
                                                 @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                 HttpServletRequest request) {

        log.info("================= INICIANDO DEBUG DE UPLOAD ==================");
        log.info("Content-Type do Header: {}", request.getContentType());
        log.info("Número de arquivos recebidos na parte 'files': {}", (files != null ? files.size() : "0 (lista nula)"));
        if (files != null) {
            files.forEach(file -> log.info("Nome do arquivo recebido: {}", file.getOriginalFilename()));
        }
        log.info("================= FIM DO DEBUG DE UPLOAD ====================");

        AsoResponseDTO createdAso = asoService.create(dto, files);
        return new ResponseEntity<>(createdAso, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<AsoResponseDTO> update(@PathVariable Long id, @RequestBody AsoRequestDTO dto) {
        AsoResponseDTO updatedAso = asoService.update(id, dto);
        return ResponseEntity.ok(updatedAso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        asoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}