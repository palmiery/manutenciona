package com.manutenciona.api.service;

import com.manutenciona.api.dto.SalaCreateDTO;
import com.manutenciona.api.dto.SalaDTO;
import com.manutenciona.api.model.Sala;
import com.manutenciona.api.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<SalaDTO> findAll() {
        return salaRepository.findAll().stream()
                .map(SalaDTO::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public SalaDTO findById(Long id) {
        return salaRepository.findById(id)
                .map(SalaDTO::from)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com id: " + id));
    }

    @Transactional
    public SalaDTO create(SalaCreateDTO dto) {
        Sala sala = Sala.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .localizacao(dto.localizacao())
                .build();
        return SalaDTO.from(salaRepository.save(sala));
    }

    @Transactional
    public SalaDTO update(Long id, SalaCreateDTO dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com id: " + id));

        sala.setNome(dto.nome());
        sala.setDescricao(dto.descricao());
        sala.setLocalizacao(dto.localizacao());

        return SalaDTO.from(salaRepository.save(sala));
    }

    @Transactional
    public void delete(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new RuntimeException("Sala não encontrada com id: " + id);
        }
        salaRepository.deleteById(id);
    }
}
