package com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoCatalogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoCatalogoRepository extends JpaRepository<TreinamentoCatalogoEntity, Long> {}