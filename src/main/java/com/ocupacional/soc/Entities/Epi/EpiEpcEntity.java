package com.ocupacional.soc.Entities.Epi;

import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Enuns.CadastroEpi.TipoEpiEpc;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "epi_epc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpiEpcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEpiEpc tipo;

    private String modelo;

    private LocalDate validadeCA;

    private boolean apenasParaPGR;

    @Column(nullable = false)
    private String certificadoAvaliacao;

    private String periodicidadeUso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private EpiCategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_id")
    private EpiFabricanteEntity fabricante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    @Column(name="criado_por")
    private String criadoPor;
}
