package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Services.impl.ParteCorpoAtingidaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parte-corpo-atingida")
@RequiredArgsConstructor
public class ParteCorpoAtingidaController {

    private final ParteCorpoAtingidaServiceImpl parteCorpoAtingidaService;

    @GetMapping
    public ResponseEntity<List<CatalogoSimplesDTO>> getAll() {
        List<CatalogoSimplesDTO> list = parteCorpoAtingidaService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoSimplesDTO> getById(@PathVariable Long id) {
        CatalogoSimplesDTO parteCorpoAtingida = parteCorpoAtingidaService.findById(id);
        return ResponseEntity.ok(parteCorpoAtingida);
    }

    @GetMapping("/buscar-descricao")
    public ResponseEntity<List<CatalogoSimplesDTO>> searchByDescricao(@RequestParam String descricao) {
        List<CatalogoSimplesDTO> list = parteCorpoAtingidaService.findByDescricao(descricao);
        return ResponseEntity.ok(list);
    }
}