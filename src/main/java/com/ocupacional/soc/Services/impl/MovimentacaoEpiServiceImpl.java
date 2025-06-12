package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiResponseDTO;
import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiRequestDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Epi.EpiEpcEntity;
import com.ocupacional.soc.Entities.Epi.MovimentacaoEpiEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.EpiMappers.MovimentacaoEpiMapper;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.Epi.EpiEpcRepository;
import com.ocupacional.soc.Repositories.Epi.MovimentacaoEpiRepository;
import com.ocupacional.soc.Services.EpiService.MovimentacaoEpiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoEpiServiceImpl implements MovimentacaoEpiService {

    private final MovimentacaoEpiRepository movimentacaoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EpiEpcRepository epiRepository;
    private final MovimentacaoEpiMapper movimentacaoEpiMapper;

    @Override
    @Transactional
    public List<MovimentacaoEpiResponseDTO> registrarRetirada(MovimentacaoEpiRequestDTO dto) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        // Lista para armazenar as movimentações criadas
        List<MovimentacaoEpiEntity> movimentacoesSalvas = new java.util.ArrayList<>();

        for (MovimentacaoEpiRequestDTO.ItemMovimentacaoDTO itemDto : dto.getItens()) {
            EpiEpcEntity epi = epiRepository.findById(itemDto.getEpiId())
                    .orElseThrow(() -> new ResourceNotFoundException("EPI não encontrado"));

            if (epi.getQuantidadeEmEstoque() < itemDto.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para o EPI: " + epi.getNome());
            }

            epi.setQuantidadeEmEstoque(epi.getQuantidadeEmEstoque() - itemDto.getQuantidade());
            epiRepository.save(epi);

            MovimentacaoEpiEntity movimentacao = new MovimentacaoEpiEntity();
            movimentacao.setFuncionario(funcionario);
            movimentacao.setEpi(epi);
            movimentacao.setQuantidade(itemDto.getQuantidade());
            movimentacao.setDataMovimentacao(java.time.LocalDateTime.now());
            movimentacao.setTermoCiencia(dto.getTermoCiencia());

            movimentacoesSalvas.add(movimentacaoRepository.save(movimentacao));
        }

        // Mapeia a lista de entidades salvas para DTOs e retorna
        return movimentacoesSalvas.stream()
                .map(movimentacaoEpiMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

}