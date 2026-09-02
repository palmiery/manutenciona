package com.manutenciona.api.dto;

import com.manutenciona.api.enums.PrioridadeSolicitacao;
import com.manutenciona.api.enums.StatusSolicitacao;
import com.manutenciona.api.model.Solicitacao;

import java.time.LocalDateTime;

public record SolicitacaoDTO(
        Long id,
        String titulo,
        String descricao,
        String solicitanteNome,
        String solicitanteContato,
        StatusSolicitacao status,
        PrioridadeSolicitacao prioridade,
        Long salaId,
        String salaNome,
        Long equipamentoId,
        String equipamentoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime resolvedAt
) {
    public static SolicitacaoDTO from(Solicitacao s) {
        return new SolicitacaoDTO(
                s.getId(),
                s.getTitulo(),
                s.getDescricao(),
                s.getSolicitanteNome(),
                s.getSolicitanteContato(),
                s.getStatus(),
                s.getPrioridade(),
                s.getSala() != null ? s.getSala().getId() : null,
                s.getSala() != null ? s.getSala().getNome() : null,
                s.getEquipamento() != null ? s.getEquipamento().getId() : null,
                s.getEquipamento() != null ? s.getEquipamento().getNome() : null,
                s.getCreatedAt(),
                s.getUpdatedAt(),
                s.getResolvedAt()
        );
    }
}
