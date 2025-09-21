package com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Nr16AnexoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ltip_nr16_anexos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LtipNr16AnexoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ltip_id", nullable = false)
    private LtipEntity ltip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anexo_id", nullable = false)
    private Nr16AnexoEntity anexo;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String avaliacao;
}