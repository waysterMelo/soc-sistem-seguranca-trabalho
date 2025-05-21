package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository  extends JpaRepository<FuncionarioEntity, Long> {


}
