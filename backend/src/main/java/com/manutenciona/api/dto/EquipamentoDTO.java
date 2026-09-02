package com.manutenciona.api.dto;

import com.manutenciona.api.model.Equipamento;
import java.time.LocalDateTime;

public record EquipamentoDTO(
        Long id,
        String nome,
        String descricao,
        String numeroSerie,
        Long salaId,
        String salaNome,
        LocalDateTime createdAt
) {
    public static EquipamentoDTO from(Equipamento e) {
        return new EquipamentoDTO(
                e.getId(),
                e.getNome(),
                e.getDescricao(),
                e.getNumeroSerie(),
                e.getSala() != null ? e.getSala().getId() : null,
                e.getSala() != null ? e.getSala().getNome() : null,
                e.getCreatedAt()
        );
    }
}
