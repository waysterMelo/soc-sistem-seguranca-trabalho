package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Nr16AnexoDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.Nr16AnexoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/nr16-anexos")
@RequiredArgsConstructor
public class Nr16AnexoController {

    private final Nr16AnexoService service;

    @GetMapping
    public ResponseEntity<List<Nr16AnexoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}