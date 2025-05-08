package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.SetorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<SetorEntity, Long> {

    Optional<SetorEntity> findByNome(String nome);

}
