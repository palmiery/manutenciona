package com.manutenciona.api.dto;

import com.manutenciona.api.enums.PrioridadeSolicitacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitacaoCreateDTO(
        @NotBlank(message = "Título é obrigatório")
        @Size(max = 200)
        String titulo,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 10, message = "A descrição deve conter no mínimo 10 caracteres")
        String descricao,

        @NotBlank(message = "Nome do solicitante é obrigatório")
        @Size(max = 150)
        String solicitanteNome,

        @Size(max = 150)
        String solicitanteContato,

        @NotNull(message = "Prioridade inicial é obrigatória")
        PrioridadeSolicitacao prioridade,

        @NotNull(message = "Sala é obrigatória")
        Long salaId,

        @NotNull(message = "Equipamento é obrigatório")
        Long equipamentoId
) {}
