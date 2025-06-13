package com.ocupacional.soc.Repositories.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Pcmso.PcmsoExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcmsoExameRepository extends JpaRepository<PcmsoExameEntity, Long> {}