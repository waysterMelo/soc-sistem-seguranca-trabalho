package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoRequestDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.NotFoundException;
import com.ocupacional.soc.Mapper.PrestadorServiços.PrestadorServicoMapper;
import com.ocupacional.soc.Repositories.Cadastros.CboRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Cadastros.PrestadorServicoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PrestadorServicoServiceImpl implements PrestadorServicoService {

    private final PrestadorServicoRepository repo;
    private final CboRepository cboRepo;
    private final PrestadorServicoMapper prestadorServicoMapper;


    @Override
    @Transactional
    public PrestadorServicoResponseDTO create(PrestadorServicoRequestDTO dto) {
        validarCpfUnico(dto.getCpf(), null);
        PrestadorServicoEntity entity = prestadorServicoMapper.toEntity(dto);
        entity.setCbo(cboRepo.getReferenceById(dto.getCboId()));
        return prestadorServicoMapper.toDto(repo.save(entity));
    }

    @Override
    public PrestadorServicoResponseDTO update(Long id, PrestadorServicoRequestDTO dto) {
       PrestadorServicoEntity entity = repo.findById(id).orElseThrow(() -> new NotFoundException("Prestador não encontrado"));
        validarCpfUnico(dto.getCpf(), null);
        prestadorServicoMapper.updateEntityFromDto(dto, entity);
        entity.setCbo(cboRepo.getReferenceById(dto.getCboId()));
        return prestadorServicoMapper.toDto(entity);
    }

    @Override
    @Transactional
    public Page<PrestadorServicoResponseDTO> list(String filtro, Pageable pageable) {
       return repo.findByNomeContainingIgnoreCaseOrCpfContaining(filtro, filtro, pageable)
               .map(prestadorServicoMapper::toDto);
    }

    @Override
    @Transactional
    public PrestadorServicoResponseDTO get(Long id) {
        return prestadorServicoMapper.toDto(
                repo.findById(id).orElseThrow(() -> new NotFoundException("Prestador não encontrado"))
        );
    }


    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void importar(MultipartFile file) {
        // mesma lógica que enviamos antes…
    }


    private void validarCpfUnico(String cpf, Long idAtual){
        repo.findByCpf(cpf).filter(p ->
                !p.getId().equals(idAtual)).ifPresent(p -> { throw new BusinessException("CPF já cadastrado"); });
    }
}
