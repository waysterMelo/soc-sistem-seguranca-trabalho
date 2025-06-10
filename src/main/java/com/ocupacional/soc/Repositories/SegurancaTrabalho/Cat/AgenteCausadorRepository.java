package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ocupacional.soc.Entities.Cat.AgenteCausadorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteCausadorRepository extends JpaRepository<AgenteCausadorEntity, Long> {
}
