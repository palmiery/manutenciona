package com.manutenciona.api.dto;

import com.manutenciona.api.enums.PrioridadeSolicitacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SolicitacaoCreateDTO(
        @NotBlank(message = "Título é obrigatório")
        @Size(max = 200)
        String titulo,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotBlank(message = "Nome do solicitante é obrigatório")
        @Size(max = 150)
        String solicitanteNome,

        @Size(max = 150)
        String solicitanteContato,

        PrioridadeSolicitacao prioridade,

        Long salaId,

        Long equipamentoId
) {}
