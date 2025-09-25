package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Services.impl.NaturezaLesaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/natureza-lesao")
@RequiredArgsConstructor
public class NaturezaLesaoController {

    private final NaturezaLesaoServiceImpl naturezaLesaoService;

    @GetMapping
    public ResponseEntity<List<CatalogoSimplesDTO>> getAll() {
        List<CatalogoSimplesDTO> list = naturezaLesaoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoSimplesDTO> getById(@PathVariable Long id) {
        CatalogoSimplesDTO naturezaLesao = naturezaLesaoService.findById(id);
        return ResponseEntity.ok(naturezaLesao);
    }

    @GetMapping("/buscar-descricao")
    public ResponseEntity<List<CatalogoSimplesDTO>> searchByDescricao(@RequestParam String descricao) {
        List<CatalogoSimplesDTO> list = naturezaLesaoService.findByDescricao(descricao);
        return ResponseEntity.ok(list);
    }
}