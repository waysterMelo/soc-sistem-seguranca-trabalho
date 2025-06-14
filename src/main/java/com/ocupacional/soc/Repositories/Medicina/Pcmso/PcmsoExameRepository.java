package com.ocupacional.soc.Repositories.Medicina.Pcmso;

import com.ocupacional.soc.Entities.Medicina.Pcmso.PcmsoExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcmsoExameRepository extends JpaRepository<PcmsoExameEntity, Long> {}