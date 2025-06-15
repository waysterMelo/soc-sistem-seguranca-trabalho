package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Cat.CidEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.AfastamentoEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.MotivoAfastamentoEntity;
import com.ocupacional.soc.Enuns.Funcionario.StatusFuncionario;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Aso.AfastamentoMapper;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.Cadastros.ProfissionalRegistrosRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.AfastamentoRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.MotivoAfastamentoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.CidRepository;
import com.ocupacional.soc.Services.Medicina.Aso.AfastamentoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.DAYS;


@Service
@RequiredArgsConstructor
public class AfastamentoServiceImpl implements AfastamentoService {

    private final AfastamentoRepository afasRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProfissionalRegistrosRepository registroRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final MotivoAfastamentoRepository motivoRepository;
    private final CidRepository cidRepository;
    private final AfastamentoMapper mapper;

    @Override
    @Transactional
    public AfastamentoResponseDTO createAfastamento(AfastamentoRequestDTO dto) {
        AfastamentoEntity entity = new AfastamentoEntity();
        mapToEntity(entity, dto);

        handleFuncionarioStatus(entity);

        AfastamentoEntity savedEntity = afasRepository.save(entity);
        return buildResponseWithCalculatedFields(savedEntity);
    }

    @Override
    @Transactional
    public AfastamentoResponseDTO updateAfastamento(Long id, AfastamentoRequestDTO dto) {
        AfastamentoEntity entity = afasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afastamento não encontrado com ID: " + id));

        mapToEntity(entity, dto);
        handleFuncionarioStatus(entity);

        AfastamentoEntity updatedEntity = afasRepository.save(entity);
        return buildResponseWithCalculatedFields(updatedEntity);
    }

    @Override
    @Transactional
    public Page<AfastamentoListDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;
        return afasRepository.findBySearchTerm(searchTerm, pageable).map(mapper::toListDto);
    }

    @Override
    @Transactional
    public AfastamentoResponseDTO findById(Long id) {
        AfastamentoEntity entity = afasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afastamento não encontrado com ID: " + id));
        return buildResponseWithCalculatedFields(entity);
    }

    @Override
    @Transactional
    public void deleteAfastamento(Long id) {
        if (!afasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Afastamento não encontrado com ID: " + id);
        }
        afasRepository.deleteById(id);
    }

    private void handleFuncionarioStatus(AfastamentoEntity entity) {
        if (Boolean.TRUE.equals(entity.getAlterarStatusFuncionario())) {
            FuncionarioEntity funcionario = entity.getRegistroProfissional().getFuncionario();
            funcionario.setStatus(StatusFuncionario.AFASTADO);
            funcionarioRepository.save(funcionario);
        }
    }

    private AfastamentoResponseDTO buildResponseWithCalculatedFields(AfastamentoEntity entity) {
        AfastamentoResponseDTO dto = mapper.toResponseDto(entity);
        if (entity.getDataInicio() != null && entity.getDataFim() != null) {
            dto.setTotalDias(DAYS.between(entity.getDataInicio(), entity.getDataFim()) + 1);
        }
        return dto;
    }

    private void mapToEntity(AfastamentoEntity entity, AfastamentoRequestDTO dto) {
        ProfissionalRegistrosEntity registro = registroRepository.findById(dto.getRegistroProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Registro Profissional não encontrado com ID: " + dto.getRegistroProfissionalId()));

        PrestadorServicoEntity emitente = prestadorRepository.findById(dto.getEmitenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Emitente não encontrado com ID: " + dto.getEmitenteId()));

        MotivoAfastamentoEntity motivo = motivoRepository.findById(dto.getMotivoAfastamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Motivo de Afastamento não encontrado com ID: " + dto.getMotivoAfastamentoId()));

        entity.setRegistroProfissional(registro);
        entity.setEmitente(emitente);
        entity.setMotivoAfastamento(motivo);

        if (dto.getCidId() != null) {
            CidEntity cid = cidRepository.findById(dto.getCidId())
                    .orElseThrow(() -> new ResourceNotFoundException("CID não encontrado com ID: " + dto.getCidId()));
            entity.setCid(cid);
        } else {
            entity.setCid(null);
        }

        entity.setTipoAcidente(dto.getTipoAcidente());
        entity.setCnpj(dto.getCnpj());
        entity.setOnusRemuneracao(dto.getOnusRemuneracao());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        entity.setObservacao(dto.getObservacao());
        entity.setMesmaDoenca60Dias(dto.getMesmaDoenca60Dias());
        entity.setAlterarStatusFuncionario(dto.getAlterarStatusFuncionario());
        entity.setExibirRgAtestado(dto.getExibirRgAtestado());
        entity.setExibirCpfAtestado(dto.getExibirCpfAtestado());
        entity.setTipoRetificacao(dto.getTipoRetificacao());
        entity.setNumeroReciboEsocial(dto.getNumeroReciboEsocial());
    }
}