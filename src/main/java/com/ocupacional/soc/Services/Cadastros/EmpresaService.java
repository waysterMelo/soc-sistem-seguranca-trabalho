package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Relatorios.EmpresaRelatorioDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private LogoStorageService logoStorageService;

    @Autowired
    private EmpresaRepository empresaRepository;


    public List<EmpresaEntity> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<EmpresaEntity> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public EmpresaEntity salvar(EmpresaEntity empresa) {
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    public EmpresaEntity atualizar(Long id, EmpresaEntity empresaAtualizada) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setTipoEmpresa(empresaAtualizada.getTipoEmpresa());
                    empresa.setCpfOuCnpj(empresaAtualizada.getCpfOuCnpj());
                    empresa.setInscricaoEstadual(empresaAtualizada.getInscricaoEstadual());
                    empresa.setStatus(empresaAtualizada.getStatus());
                    empresa.setRazaoSocial(empresaAtualizada.getRazaoSocial());
                    empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
                    empresa.setLogomarcaUrl(empresaAtualizada.getLogomarcaUrl());
                    empresa.setEndereco(empresaAtualizada.getEndereco());
                    empresa.setTelefonePrincipal(empresaAtualizada.getTelefonePrincipal());
                    empresa.setTelefoneSecundario(empresaAtualizada.getTelefoneSecundario());
                    empresa.setEmail(empresaAtualizada.getEmail());
                    empresa.setGrauRisco(empresaAtualizada.getGrauRisco());
                    empresa.setCnaePrincipalId(empresaAtualizada.getCnaePrincipalId());
                    empresa.setTipoMatrizFilial(empresaAtualizada.getTipoMatrizFilial());
                    empresa.setMedicoResponsavelPcmsso(empresaAtualizada.getMedicoResponsavelPcmsso());
                    empresa.setObservacoes(empresaAtualizada.getObservacoes());
                    return empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    public Map<String, String> uploadLogo(MultipartFile file) {
        String fileName = logoStorageService.storeLogo(file);
        String fileUrl = logoStorageService.getLogoUrl(fileName);
        return Map.of("url", fileUrl);
    }

    public byte[] gerarRelatorioPdf() throws Exception {
        try {
            List<EmpresaEntity> empresas = listarTodas();

            // Converter entidades para DTOs
            List<EmpresaRelatorioDTO> empresasDTO = empresas.stream()
                    .map(empresa -> {
                        try {
                            return new EmpresaRelatorioDTO(empresa);
                        } catch (Exception e) {
                            // Loggar o erro mas continuar com o processamento
                            System.err.println("Erro ao converter empresa para DTO: " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Preparar estatísticas para os gráficos
            Map<String, Integer> riscoCount = new HashMap<>();
            Map<String, Integer> statusCount = new HashMap<>();

            // Calcular contagens para os gráficos
            for (EmpresaRelatorioDTO dto : empresasDTO) {
                // Contar por grau de risco
                String risco = dto.getGrauRisco().isEmpty() ? "Não informado" : dto.getGrauRisco();
                riscoCount.put(risco, riscoCount.getOrDefault(risco, 0) + 1);

                // Contar por status
                String status = dto.getStatus().isEmpty() ? "Não informado" : dto.getStatus();
                statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
            }

            // Converter mapas para listas de mapas (cada mapa com chaves "key" e "value")
            List<Map<String, Object>> riscoData = riscoCount.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("key", entry.getKey());
                        item.put("value", entry.getValue());
                        return item;
                    })
                    .collect(Collectors.toList());

            List<Map<String, Object>> statusData = statusCount.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("key", entry.getKey());
                        item.put("value", entry.getValue());
                        return item;
                    })
                    .collect(Collectors.toList());

            // Carregar o relatório compilado (jasper)
            InputStream jasperStream = new ClassPathResource("relatorios/Relatorio_Empresas.jasper").getInputStream();

            // Se o arquivo .jasper não existe, compilar o .jrxml
            JasperReport jasperReport;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            } catch (Exception e) {
                // Fallback para compilação manual
                System.out.println("Arquivo .jasper não encontrado, compilando .jrxml...");
                InputStream jrxmlStream = new ClassPathResource("relatorios/Relatorio_Empresas.jrxml").getInputStream();
                jasperReport = JasperCompileManager.compileReport(jrxmlStream);
            }

            // Preparar os parâmetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TITULO_RELATORIO", "RELATÓRIO DE EMPRESAS");
            parameters.put("DATA_GERACAO", new Date());
            parameters.put("LOGO_PATH", "classpath:imagens/logo.png");
            parameters.put("USUARIO", "Sistema");

            // Adicionar estatísticas como parâmetros - agora usando listas de mapas
            parameters.put("TOTAL_EMPRESAS", empresasDTO.size());
            parameters.put("RISCO_COUNT", riscoData);  // Agora é uma lista de mapas
            parameters.put("STATUS_COUNT", statusData); // Agora é uma lista de mapas

            // Criar a fonte de dados usando os DTOs
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empresasDTO);

            // Gerar o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource
            );

            // Exportar para PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório PDF: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


} 