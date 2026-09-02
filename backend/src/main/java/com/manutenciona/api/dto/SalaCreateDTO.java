package com.manutenciona.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SalaCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150)
        String nome,

        @Size(max = 500)
        String descricao,

        @Size(max = 200)
        String localizacao
) {}
