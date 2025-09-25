package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Services.impl.CidServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cid")
@RequiredArgsConstructor
public class CidController {

    private final CidServiceImpl cidService;

    @GetMapping
    public ResponseEntity<List<CidDTO>> getAll() {
        List<CidDTO> list = cidService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidDTO> getById(@PathVariable Long id) {
        CidDTO cid = cidService.findById(id);
        return ResponseEntity.ok(cid);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CidDTO> getByCodigo(@PathVariable String codigo) {
        CidDTO cid = cidService.findByCodigo(codigo);
        return ResponseEntity.ok(cid);
    }

    @GetMapping("/buscar-codigo")
    public ResponseEntity<List<CidDTO>> searchByCodigo(
            @RequestParam String codigo,
            @RequestParam(defaultValue = "true") boolean parcial) {
        List<CidDTO> list = cidService.findByCodigo(codigo, parcial);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar-descricao")
    public ResponseEntity<List<CidDTO>> searchByDescricao(@RequestParam String descricao) {
        List<CidDTO> list = cidService.findByDescricao(descricao);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CidDTO>> search(@RequestParam String termo) {
        List<CidDTO> list = cidService.search(termo);
        return ResponseEntity.ok(list);
    }
}