package com.manutenciona.api.dto;

import com.manutenciona.api.model.Sala;
import java.time.LocalDateTime;

public record SalaDTO(
        Long id,
        String nome,
        String descricao,
        String localizacao,
        LocalDateTime createdAt
) {
    public static SalaDTO from(Sala s) {
        return new SalaDTO(
                s.getId(),
                s.getNome(),
                s.getDescricao(),
                s.getLocalizacao(),
                s.getCreatedAt()
        );
    }
}
