package com.manutenciona.api.service;

import com.manutenciona.api.dto.EquipamentoCreateDTO;
import com.manutenciona.api.dto.EquipamentoDTO;
import com.manutenciona.api.model.Equipamento;
import com.manutenciona.api.model.Sala;
import com.manutenciona.api.model.Solicitacao;
import com.manutenciona.api.repository.EquipamentoRepository;
import com.manutenciona.api.repository.SalaRepository;
import com.manutenciona.api.repository.SolicitacaoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SalaRepository salaRepository;

    //Para remoção de equipamentos
    private final SolicitacaoRepository solicitacaoRepository;

    @Transactional(readOnly = true)
    public List<EquipamentoDTO> findAll(Long salaId) {
        List<Equipamento> list = (salaId != null) 
                ? equipamentoRepository.findBySalaId(salaId)
                : equipamentoRepository.findAll();
        return list.stream().map(EquipamentoDTO::from).toList();
    }

    @Transactional(readOnly = true)
    public EquipamentoDTO findById(Long id) {
        return equipamentoRepository.findById(id)
                .map(EquipamentoDTO::from)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com id: " + id));
    }

    @Transactional
    public EquipamentoDTO create(EquipamentoCreateDTO dto) {
        Sala sala = null;
        if (dto.salaId() != null) {
            sala = salaRepository.findById(dto.salaId())
                    .orElseThrow(() -> new RuntimeException("Sala não encontrada com id: " + dto.salaId()));
        }

        Equipamento equipamento = Equipamento.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .numeroSerie(dto.numeroSerie())
                .sala(sala)
                .build();

        return EquipamentoDTO.from(equipamentoRepository.save(equipamento));
    }

    @Transactional
    public EquipamentoDTO update(Long id, EquipamentoCreateDTO dto) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com id: " + id));

        Sala sala = null;
        if (dto.salaId() != null) {
            sala = salaRepository.findById(dto.salaId())
                    .orElseThrow(() -> new RuntimeException("Sala não encontrada com id: " + dto.salaId()));
        }

        equipamento.setNome(dto.nome());
        equipamento.setDescricao(dto.descricao());
        equipamento.setNumeroSerie(dto.numeroSerie());
        equipamento.setSala(sala);

        return EquipamentoDTO.from(equipamentoRepository.save(equipamento));
    }

    @Transactional
    public void delete(Long id) {
        if (!equipamentoRepository.existsById(id)) {
            throw new RuntimeException("Equipamento não encontrado com id: " + id);
        }

        // Desvincula o equipamento de eventuais solicitações existentes
        List<Solicitacao> solicitacoes = solicitacaoRepository.
        findByEquipamentoId(id);
        for (Solicitacao s : solicitacoes) {
            s.setEquipamento(null);
            solicitacaoRepository.save(s);
        }

        equipamentoRepository.deleteById(id);
    }
}
