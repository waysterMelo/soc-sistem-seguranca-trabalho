package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrMapaRiscoFuncaoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PgrMapper;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.RiscoCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.PgrRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.PgrService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PgrServiceImpl implements PgrService {

    private final PgrRepository pgrRepository;
    private final PgrMapper pgrMapper;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final FuncaoRepository funcaoRepository;
    private final RiscoCatalogoRepository riscoCatalogoRepository;

    @Override
    @Transactional
    public PgrResponseDTO createPgr(PgrRequestDTO requestDTO) {
        PgrEntity pgrEntity = pgrMapper.toEntity(requestDTO);
        updatePgrFields(pgrEntity, requestDTO); // Popula todos os campos, incluindo os novos

        UnidadeOperacionalEntity unidade = findUnidadeById(requestDTO.getUnidadeOperacionalId());
        pgrEntity.setUnidadeOperacional(unidade);

        updateMapaDeRiscos(pgrEntity, requestDTO);

        return pgrMapper.toDto(pgrRepository.save(pgrEntity));
    }

    @Override
    public PgrResponseDTO getPgrById(Long id) {
        return pgrRepository.findById(id)
                .map(pgrMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + id));
    }

    @Override
    public Page<PgrResponseDTO> getAllPgrsByEmpresa(Long empresaId, Pageable pageable) {
        return pgrRepository.findByUnidadeOperacional_Empresa_Id(empresaId, pageable).map(pgrMapper::toDto);
    }

    @Override
    @Transactional
    public PgrResponseDTO updatePgr(Long id, PgrRequestDTO requestDTO) {
        PgrEntity pgrEntity = pgrRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + id));

        updatePgrFields(pgrEntity, requestDTO);

        if (!pgrEntity.getUnidadeOperacional().getId().equals(requestDTO.getUnidadeOperacionalId())) {
            pgrEntity.setUnidadeOperacional(findUnidadeById(requestDTO.getUnidadeOperacionalId()));
        }
        updateMapaDeRiscos(pgrEntity, requestDTO);

        return pgrMapper.toDto(pgrRepository.save(pgrEntity));
    }

    @Override
    @Transactional
    public void deletePgr(Long id) {
        if (!pgrRepository.existsById(id)) {
            throw new ResourceNotFoundException("PGR não encontrado com ID: " + id);
        }
        pgrRepository.deleteById(id);
    }

    private void updateMapaDeRiscos(PgrEntity pgrEntity, PgrRequestDTO requestDTO) {
        pgrEntity.getMapaRiscos().clear();
        if (!CollectionUtils.isEmpty(requestDTO.getMapaRiscos())) {
            requestDTO.getMapaRiscos().forEach(mapaDto -> {
                FuncaoEntity funcao = findFuncaoById(mapaDto.getFuncaoId());
                Set<RiscoCatalogoEntity> riscos = new HashSet<>();
                if (!CollectionUtils.isEmpty(mapaDto.getRiscoCatalogoIds())) {
                    riscos = new HashSet<>(riscoCatalogoRepository.findAllById(mapaDto.getRiscoCatalogoIds()));
                }

                PgrMapaRiscoFuncaoEntity mapaRisco = PgrMapaRiscoFuncaoEntity.builder()
                        .funcao(funcao)
                        .riscosDoCatalogo(riscos)
                        .riscosPersonalizados(mapaDto.getRiscosPersonalizados())
                        .build();
                pgrEntity.addMapaRisco(mapaRisco);
            });
        }
    }

    private void updatePgrFields(PgrEntity entity, PgrRequestDTO dto) {
        entity.setTipo(dto.getTipo());
        entity.setConteudoCapa(dto.getConteudoCapa());
        entity.setTermoValidacao(dto.getTermoValidacao());
        entity.setDocumentoBase(dto.getDocumentoBase());
        entity.setResponsavelEmpresa(dto.getResponsavelEmpresa());
        entity.setDataDocumento(dto.getDataDocumento());
        entity.setDataRevisao(dto.getDataRevisao());
        entity.setTermoCiencia(dto.getTermoCiencia());
        entity.setInventarioRiscos(dto.getInventarioRiscos());
        entity.setPlanoAcaoMetodologia(dto.getPlanoAcaoMetodologia());
        entity.setPlanoAcaoOrientacoes(dto.getPlanoAcaoOrientacoes());
        entity.setConsideracoesFinais(dto.getConsideracoesFinais());

        // Atualiza campos NR 31
        entity.setNr31TrabalhoComAnimais(dto.getNr31TrabalhoComAnimais());
        entity.setNr31OcorrenciasClimaticas(dto.getNr31OcorrenciasClimaticas());
        entity.setNr31EsforcosExcessivos(dto.getNr31EsforcosExcessivos());
        entity.setNr31RegrasTransito(dto.getNr31RegrasTransito());
        entity.setNr31DescarteResiduos(dto.getNr31DescarteResiduos());
        entity.setNr31ProximidadeRedesEletricas(dto.getNr31ProximidadeRedesEletricas());
    }

    private UnidadeOperacionalEntity findUnidadeById(Long id) {
        return unidadeOperacionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + id));
    }

    private FuncaoEntity findFuncaoById(Long id) {
        return funcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + id));
    }
}