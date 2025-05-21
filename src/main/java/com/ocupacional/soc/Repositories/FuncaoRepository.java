package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.FuncaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncaoRepository extends JpaRepository<FuncaoEntity, Long> {




}
