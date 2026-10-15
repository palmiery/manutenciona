package com.manutenciona.api.dto;

import com.manutenciona.api.enums.PrioridadeSolicitacao;
import com.manutenciona.api.enums.StatusSolicitacao;

public record SolicitacaoStatusDTO(
        StatusSolicitacao status,
        PrioridadeSolicitacao prioridade
) {}
