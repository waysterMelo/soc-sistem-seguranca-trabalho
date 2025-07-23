package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private LogoStorageService logoStorageService;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    public void testGerarRelatorioPdf() throws Exception {
        // Arrange
        List<EmpresaEntity> empresas = new ArrayList<>();
        when(empresaRepository.findAll()).thenReturn(empresas);

        // Act
        byte[] pdfBytes = empresaService.gerarRelatorioPdf();

        // Assert
        assertNotNull(pdfBytes);
        System.out.println("[DEBUG_LOG] PDF gerado com sucesso. Tamanho: " + pdfBytes.length + " bytes");
    }
}