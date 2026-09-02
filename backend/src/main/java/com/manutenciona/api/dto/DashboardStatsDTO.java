package com.manutenciona.api.dto;

public record DashboardStatsDTO(
        long pendentes,
        long emEspera,
        long concluidos,
        long total
) {}
