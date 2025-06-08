package com.ocupacional.soc.Services;

import com.ocupacional.soc.Entities.Cadastros.MedicoEntity;
import com.ocupacional.soc.Repositories.Cadastros.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<MedicoEntity> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<MedicoEntity> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public MedicoEntity salvar(MedicoEntity medico) {
        return medicoRepository.save(medico);
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }

    public MedicoEntity atualizar(Long id, MedicoEntity medicoAtualizado) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setNome(medicoAtualizado.getNome());
                    medico.setCrm(medicoAtualizado.getCrm());
                    return medicoRepository.save(medico);
                })
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }
} 