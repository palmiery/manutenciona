package com.manutenciona.api.service;

import com.manutenciona.api.dto.DashboardStatsDTO;
import com.manutenciona.api.dto.SolicitacaoCreateDTO;
import com.manutenciona.api.dto.SolicitacaoDTO;
import com.manutenciona.api.dto.SolicitacaoStatusDTO;
import com.manutenciona.api.enums.PrioridadeSolicitacao;
import com.manutenciona.api.enums.StatusSolicitacao;
import com.manutenciona.api.model.Equipamento;
import com.manutenciona.api.model.Sala;
import com.manutenciona.api.model.Solicitacao;
import com.manutenciona.api.repository.EquipamentoRepository;
import com.manutenciona.api.repository.SalaRepository;
import com.manutenciona.api.repository.SolicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SalaRepository salaRepository;
    private final EquipamentoRepository equipamentoRepository;

    @Transactional(readOnly = true)
    public List<SolicitacaoDTO> findAll(StatusSolicitacao status) {
        List<Solicitacao> list = (status != null)
                ? solicitacaoRepository.findByStatusOrderByCreatedAtDesc(status)
                : solicitacaoRepository.findAllByOrderByCreatedAtDesc();
        return list.stream().map(SolicitacaoDTO::from).toList();
    }

    @Transactional(readOnly = true)
    public SolicitacaoDTO findById(Long id) {
        return solicitacaoRepository.findById(id)
                .map(SolicitacaoDTO::from)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public DashboardStatsDTO getDashboardStats() {
        long pendentes = solicitacaoRepository.countPendentes();
        long emEspera = solicitacaoRepository.countEmEspera();
        long concluidos = solicitacaoRepository.countConcluidos();
        long total = pendentes + emEspera + concluidos;
        return new DashboardStatsDTO(pendentes, emEspera, concluidos, total);
    }

    @Transactional
    public SolicitacaoDTO create(SolicitacaoCreateDTO dto) {
        Sala sala = null;
        if (dto.salaId() != null) {
            sala = salaRepository.findById(dto.salaId()).orElse(null);
        }

        Equipamento equipamento = null;
        if (dto.equipamentoId() != null) {
            equipamento = equipamentoRepository.findById(dto.equipamentoId()).orElse(null);
        }

        Solicitacao solicitacao = Solicitacao.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .solicitanteNome(dto.solicitanteNome())
                .solicitanteContato(dto.solicitanteContato())
                .prioridade(dto.prioridade() != null ? dto.prioridade() : PrioridadeSolicitacao.MEDIA)
                .status(StatusSolicitacao.PENDENTE)
                .sala(sala)
                .equipamento(equipamento)
                .build();

        return SolicitacaoDTO.from(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public SolicitacaoDTO updateStatus(Long id, SolicitacaoStatusDTO dto) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com id: " + id));

        solicitacao.setStatus(dto.status());
        if (dto.status() == StatusSolicitacao.CONCLUIDO) {
            solicitacao.setResolvedAt(LocalDateTime.now());
        } else {
            solicitacao.setResolvedAt(null);
        }

        return SolicitacaoDTO.from(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public SolicitacaoDTO update(Long id, SolicitacaoCreateDTO dto) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com id: " + id));

        Sala sala = null;
        if (dto.salaId() != null) {
            sala = salaRepository.findById(dto.salaId()).orElse(null);
        }

        Equipamento equipamento = null;
        if (dto.equipamentoId() != null) {
            equipamento = equipamentoRepository.findById(dto.equipamentoId()).orElse(null);
        }

        solicitacao.setTitulo(dto.titulo());
        solicitacao.setDescricao(dto.descricao());
        solicitacao.setSolicitanteNome(dto.solicitanteNome());
        solicitacao.setSolicitanteContato(dto.solicitanteContato());
        if (dto.prioridade() != null) {
            solicitacao.setPrioridade(dto.prioridade());
        }
        solicitacao.setSala(sala);
        solicitacao.setEquipamento(equipamento);

        return SolicitacaoDTO.from(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public void delete(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new RuntimeException("Solicitação não encontrada com id: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }
}
