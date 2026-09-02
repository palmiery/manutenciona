package com.manutenciona.api.dto;

import com.manutenciona.api.enums.StatusSolicitacao;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoStatusDTO(
        @NotNull(message = "Status é obrigatório")
        StatusSolicitacao status
) {}
