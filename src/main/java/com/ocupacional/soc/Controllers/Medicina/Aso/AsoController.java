package com.ocupacional.soc.Controllers.Medicina.Aso;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import com.ocupacional.soc.Services.impl.AsoServiceImpl;
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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/aso")
@RequiredArgsConstructor
@Slf4j
public class AsoController {

    private final AsoServiceImpl asoService;
    private final ObjectMapper objectMapper;


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


    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AsoResponseDTO> create(
            @RequestPart("aso") String asoRequestDtoStr,
            @RequestPart(value = "exame_resultados", required = false) List<MultipartFile> exameResultados) {

        AsoRequestDTO dto;
        try {
            // Converte a string JSON recebida para o objeto DTO
            dto = objectMapper.readValue(asoRequestDtoStr, AsoRequestDTO.class);
        } catch (IOException e) {
            log.error("Erro ao deserializar o DTO do ASO a partir da string JSON.", e);
            return ResponseEntity.badRequest().build();
        }

        AsoResponseDTO createdAso = asoService.create(dto, exameResultados);
        return new ResponseEntity<>(createdAso, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AsoResponseDTO> update(
            @PathVariable Long id,
            @RequestPart("aso") String asoRequestDtoStr,
            @RequestPart(value = "exame_resultados", required = false) List<MultipartFile> exameResultados) {

        AsoRequestDTO dto;
        try {
            dto = objectMapper.readValue(asoRequestDtoStr, AsoRequestDTO.class);
        } catch (IOException e) {
            log.error("Erro ao deserializar o DTO do ASO a partir da string JSON para atualização.", e);
            return ResponseEntity.badRequest().build();
        }

        AsoResponseDTO updatedAso = asoService.update(id, dto, exameResultados);
        return ResponseEntity.ok(updatedAso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        asoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}