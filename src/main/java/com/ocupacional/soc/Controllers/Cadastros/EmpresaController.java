package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Services.Cadastros.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    public EmpresaController(EmpresaService empresaService, EmpresaMapper empresaMapper) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> listarTodas() {
        List<EmpresaEntity> empresas = empresaService.listarTodas();
        List<EmpresaDto> dtos= empresas.stream().map(empresaMapper::toEmpresaDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id)
                .map(empresa -> ResponseEntity.ok(empresaMapper.toEmpresaDto(empresa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresaDto> criar(@RequestBody EmpresaDto empresaDto) {
        EmpresaEntity empresa = empresaMapper.toEmpresaEntity(empresaDto);
        EmpresaEntity salva = empresaService.salvar(empresa);
        return ResponseEntity.ok(empresaMapper.toEmpresaDto(salva));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> atualizar(@PathVariable Long id, @RequestBody EmpresaDto empresaDto) {
        EmpresaEntity empresa = empresaMapper.toEmpresaEntity(empresaDto);
        EmpresaEntity atualizada = empresaService.atualizar(id, empresa);
        return ResponseEntity.ok(empresaMapper.toEmpresaDto(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logo")
    public ResponseEntity<Map<String, String>> uploadLogo(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = empresaService.uploadLogo(file);
        return ResponseEntity.ok(response);
    }

} 