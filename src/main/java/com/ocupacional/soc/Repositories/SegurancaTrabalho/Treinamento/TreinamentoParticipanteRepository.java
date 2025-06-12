package com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoParticipante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoParticipanteRepository extends JpaRepository<TreinamentoParticipante, Long> {}