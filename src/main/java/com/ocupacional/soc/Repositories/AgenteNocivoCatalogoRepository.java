package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.AgenteNocivoCatalogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteNocivoCatalogoRepository extends JpaRepository<AgenteNocivoCatalogoEntity, Long>  {
}
