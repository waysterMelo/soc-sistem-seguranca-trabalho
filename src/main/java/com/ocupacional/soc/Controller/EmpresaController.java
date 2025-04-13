package com.ocupacional.soc.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ocupacional.soc.Dto.CnaeDTO;
import com.ocupacional.soc.Dto.EmpresaSimpleDTO;
import com.ocupacional.soc.Dto.EstadoDTO;
import com.ocupacional.soc.Dto.GrauRiscoDTO;
import com.ocupacional.soc.Dto.MedicoDTO;
import com.ocupacional.soc.Dto.PageResponseDTO;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController 
{
    
      @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private CnaeService cnaeService;

    // Endpoint para criar uma nova empresa
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaResponseDTO empresa = empresaService.criarEmpresa(empresaDTO);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    // Endpoint para atualizar uma empresa existente
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizarEmpresa(
            @PathVariable Long id, 
            @Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaResponseDTO empresa = empresaService.atualizarEmpresa(id, empresaDTO);
        return ResponseEntity.ok(empresa);
    }

    // Endpoint para buscar uma empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarEmpresaPorId(@PathVariable Long id) {
        EmpresaResponseDTO empresa = empresaService.buscarEmpresaPorId(id);
        return ResponseEntity.ok(empresa);
    }

    // Endpoint para buscar empresas com paginação e filtros
    @GetMapping
    public ResponseEntity<PageResponseDTO<EmpresaResponseDTO>> buscarEmpresasPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String razaoSocial,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) TipoEmpresa tipoEmpresa) {
        PageResponseDTO<EmpresaResponseDTO> empresas = empresaService.buscarEmpresasPaginado(
                page, size, razaoSocial, cpfCnpj, tipoEmpresa);
        return ResponseEntity.ok(empresas);
    }

    // Endpoint para excluir uma empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para fazer upload de logomarca
    @PostMapping("/{id}/logo")
    public ResponseEntity<EmpresaResponseDTO> uploadLogo(
            @PathVariable Long id, 
            @RequestParam("file") MultipartFile file) {
        EmpresaResponseDTO empresa = empresaService.uploadLogo(id, file);
        return ResponseEntity.ok(empresa);
    }

    // Endpoint para remover a logomarca
    @DeleteMapping("/{id}/logo")
    public ResponseEntity<EmpresaResponseDTO> removerLogo(@PathVariable Long id) {
        EmpresaResponseDTO empresa = empresaService.removerLogo(id);
        return ResponseEntity.ok(empresa);
    }
    
    // Endpoint para buscar CNAEs com paginação e filtro
    @GetMapping("/cnaes")
    public ResponseEntity<PageResponseDTO<CnaeDTO>> buscarCnaesPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchTerm) {
        PageResponseDTO<CnaeDTO> cnaes = cnaeService.buscarCnaesPaginado(page, size, searchTerm);
        return ResponseEntity.ok(cnaes);
    }
    
    // Endpoint para obter lista de tipos de empresa
    @GetMapping("/tipos-empresa")
    public ResponseEntity<List<String>> listarTiposEmpresa() {
        List<String> tiposEmpresa = empresaService.listarTiposEmpresa();
        return ResponseEntity.ok(tiposEmpresa);
    }
    
    // Endpoint para obter lista de estados brasileiros
    @GetMapping("/estados")
    public ResponseEntity<List<EstadoDTO>> listarEstados() {
        List<EstadoDTO> estados = EstadoDTO.getTodosEstados();
        return ResponseEntity.ok(estados);
    }
    
    // Endpoint para obter lista de graus de risco
    @GetMapping("/graus-risco")
    public ResponseEntity<List<GrauRiscoDTO>> listarGrausRisco() {
        List<GrauRiscoDTO> grausRisco = GrauRiscoDTO.getTodosGrausRisco();
        return ResponseEntity.ok(grausRisco);
    }
    
    // Endpoint para obter lista de tipos matriz/filial
    @GetMapping("/tipos-matriz-filial")
    public ResponseEntity<List<String>> listarTiposMatrizFilial() {
        List<String> tiposMatrizFilial = empresaService.listarTiposMatrizFilial();
        return ResponseEntity.ok(tiposMatrizFilial);
    }
    
    // Endpoint para buscar empresas matriz para seleção
    @GetMapping("/matrizes")
    public ResponseEntity<List<EmpresaSimpleDTO>> listarEmpresasMatriz() {
        List<EmpresaSimpleDTO> empresasMatriz = empresaService.listarEmpresasMatriz();
        return ResponseEntity.ok(empresasMatriz);
    }
    
    // Endpoint para buscar médicos para seleção
    @GetMapping("/medicos")
    public ResponseEntity<PageResponseDTO<MedicoDTO>> buscarMedicosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchTerm) {
        PageResponseDTO<MedicoDTO> medicos = empresaService.buscarMedicosPaginado(page, size, searchTerm);
        return ResponseEntity.ok(medicos);
    }



}
