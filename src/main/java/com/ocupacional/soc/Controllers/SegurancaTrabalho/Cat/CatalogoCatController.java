package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CidMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.*;
import com.ocupacional.soc.Services.impl.CatalogoCatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat/catalogos")
@RequiredArgsConstructor
public class CatalogoCatController {

    private final CatalogoCatServiceImpl catalogoService;
    private final NaturezaLesaoRepository naturezaLesaoRepository;
    private final ParteCorpoAtingidaRepo parteCorpoAtingidaRepository;
    private final AgenteCausadorRepository agenteCausadorRepository;
    private final SituacaoGeradoraRepo situacaoGeradoraRepository;
    private final CidRepository cidRepository;
    private final CidMapper cidMapper; // <-- Dependência Adicionada

    @GetMapping("/natureza-lesao")
    public ResponseEntity<List<CatalogoSimplesDTO>> getNaturezaLesao() {
        return ResponseEntity.ok(catalogoService.findAll(naturezaLesaoRepository));
    }

    // ... outros endpoints de catálogos simples ...
    @GetMapping("/parte-corpo-atingida")
    public ResponseEntity<List<CatalogoSimplesDTO>> getParteCorpoAtingida() {
        return ResponseEntity.ok(catalogoService.findAll(parteCorpoAtingidaRepository));
    }

    @GetMapping("/agente-causador")
    public ResponseEntity<List<CatalogoSimplesDTO>> getAgenteCausador() {
        return ResponseEntity.ok(catalogoService.findAll(agenteCausadorRepository));
    }

    @GetMapping("/situacao-geradora")
    public ResponseEntity<List<CatalogoSimplesDTO>> getSituacaoGeradora() {
        return ResponseEntity.ok(catalogoService.findAll(situacaoGeradoraRepository));
    }


    // --- MÉTODO ESPECÍFICO E CORRIGIDO PARA CID-10 ---
    @GetMapping("/cid10")
    public ResponseEntity<List<CidDTO>> getCid10() {
        List<CidDTO> dtos = cidRepository.findAll().stream()
                .map(cidMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}