package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.EmpresaDto;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Services.Cadastros.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empresas")
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

    @GetMapping("/relatorio/pdf")
    public ResponseEntity<byte[]> gerarRelatorioPdf() {
        try {
            byte[] relatorio = empresaService.gerarRelatorioPdf();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio-empresas.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log completo do erro

            // Criar uma mensagem mais detalhada com a pilha de chamadas
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("Erro ao gerar relatório: ").append(e.getMessage()).append("\n");

            for (StackTraceElement element : e.getStackTrace()) {
                errorMsg.append(element.toString()).append("\n");
            }

            if (e.getCause() != null) {
                errorMsg.append("Caused by: ").append(e.getCause().getMessage());
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMsg.toString().getBytes());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<EmpresaDto>>
    buscarPorNome(@RequestParam(required = false) String termo,
                  @PageableDefault(size = 10, sort = "razaoSocial")
    Pageable pageable) {
        Page<EmpresaEntity> empresasPage = empresaService.buscarPorTermo(termo, pageable);
        List<EmpresaDto> empresaDtos = empresasPage.getContent().stream().map(empresaMapper::toEmpresaDto)
                .toList();

    Page<EmpresaDto> dtoPage = new PageImpl<>(
            empresaDtos, pageable, empresasPage.getTotalElements()
    );

    return ResponseEntity.ok(dtoPage);
    }

}