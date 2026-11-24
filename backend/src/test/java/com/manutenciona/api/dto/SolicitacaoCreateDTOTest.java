package com.manutenciona.api.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.manutenciona.api.enums.PrioridadeSolicitacao;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** US02: campos obrigatórios e tamanho mínimo na abertura de solicitação. */
class SolicitacaoCreateDTOTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void solicitacaoCompletaEValida() {
        SolicitacaoCreateDTO dto = new SolicitacaoCreateDTO(
                "Lâmpada queimada",
                "Lâmpada do fundo da sala está queimada.",
                "Ana",
                "ana@exemplo.com",
                PrioridadeSolicitacao.BAIXA,
                1L,
                2L);

        assertThat(validator.validate(dto)).isEmpty();
    }

    @Test
    void descricaoCurtaEhRejeitada() {
        SolicitacaoCreateDTO dto = new SolicitacaoCreateDTO(
                "Lâmpada", "curta", "Ana", null, PrioridadeSolicitacao.BAIXA, 1L, 2L);

        assertThat(camposInvalidos(dto)).containsExactly("descricao");
    }

    @Test
    void camposObrigatoriosAusentesSaoRejeitados() {
        SolicitacaoCreateDTO dto = new SolicitacaoCreateDTO(
                "", "Descrição suficientemente longa.", " ", null, null, null, null);

        assertThat(camposInvalidos(dto))
                .containsExactlyInAnyOrder(
                        "titulo", "solicitanteNome", "prioridade", "salaId", "equipamentoId");
    }

    private Set<String> camposInvalidos(SolicitacaoCreateDTO dto) {
        Set<ConstraintViolation<SolicitacaoCreateDTO>> violations = validator.validate(dto);
        return violations.stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }
}
