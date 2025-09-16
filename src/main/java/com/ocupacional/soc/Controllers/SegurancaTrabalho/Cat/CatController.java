package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.CatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<CatResponseDTO> create(@Valid @RequestBody CatRequestDTO dto) {
        return new ResponseEntity<>(catService.createCat(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CatResponseDTO>> findAll() {
        return ResponseEntity.ok(catService.findAll());
    }
}