package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ocupacional.soc.Entities.Cat.AgenteCausadorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenteCausadorRepository extends JpaRepository<AgenteCausadorEntity, Long> {
    List<AgenteCausadorEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
