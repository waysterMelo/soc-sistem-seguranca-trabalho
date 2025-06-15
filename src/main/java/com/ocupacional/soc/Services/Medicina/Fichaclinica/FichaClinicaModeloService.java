package com.ocupacional.soc.Services.Medicina.Fichaclinica;


import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloListDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloRequestDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;

import java.util.List;

public interface FichaClinicaModeloService {
    List<FichaClinicaModeloListDTO> findAll();
    FichaClinicaResponseDTO findById(Long id);
    FichaClinicaResponseDTO create(FichaClinicaModeloRequestDTO dto);
    FichaClinicaResponseDTO update(Long id, FichaClinicaModeloRequestDTO dto);
    void delete(Long id);
}