package com.manutenciona.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.manutenciona.api.dto.DashboardStatsDTO;
import com.manutenciona.api.dto.SolicitacaoCreateDTO;
import com.manutenciona.api.dto.SolicitacaoDTO;
import com.manutenciona.api.dto.SolicitacaoStatusDTO;
import com.manutenciona.api.enums.PrioridadeSolicitacao;
import com.manutenciona.api.enums.StatusSolicitacao;
import com.manutenciona.api.model.Solicitacao;
import com.manutenciona.api.repository.EquipamentoRepository;
import com.manutenciona.api.repository.SalaRepository;
import com.manutenciona.api.repository.SolicitacaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SolicitacaoServiceTest {

    @Mock
    private SolicitacaoRepository solicitacaoRepository;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private EquipamentoRepository equipamentoRepository;

    @InjectMocks
    private SolicitacaoService service;

    @Test
    void dashboardSomaOsContadoresNoTotal() {
        when(solicitacaoRepository.countPendentes()).thenReturn(3L);
        when(solicitacaoRepository.countEmEspera()).thenReturn(2L);
        when(solicitacaoRepository.countConcluidos()).thenReturn(5L);

        DashboardStatsDTO stats = service.getDashboardStats();

        assertThat(stats.total()).isEqualTo(10L);
    }

    @Test
    void filtroPorStatusESalaUsaConsultaCombinada() {
        when(solicitacaoRepository.findByStatusAndSalaIdOrderByCreatedAtDesc(
                        StatusSolicitacao.PENDENTE, 4L))
                .thenReturn(List.of());

        service.findAll(StatusSolicitacao.PENDENTE, 4L);

        verify(solicitacaoRepository)
                .findByStatusAndSalaIdOrderByCreatedAtDesc(StatusSolicitacao.PENDENTE, 4L);
        verify(solicitacaoRepository, never()).findAllByOrderByCreatedAtDesc();
    }

    @Test
    void novaSolicitacaoComecaPendente() {
        when(solicitacaoRepository.save(any(Solicitacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        SolicitacaoCreateDTO dto = new SolicitacaoCreateDTO(
                "Projetor sem imagem",
                "O projetor da sala liga, mas não exibe imagem.",
                "Maria",
                null,
                PrioridadeSolicitacao.ALTA,
                null,
                null);

        SolicitacaoDTO criada = service.create(dto);

        assertThat(criada.status()).isEqualTo(StatusSolicitacao.PENDENTE);
        assertThat(criada.prioridade()).isEqualTo(PrioridadeSolicitacao.ALTA);
    }

    @Test
    void concluirRegistraDataDeResolucao() {
        Solicitacao solicitacao = solicitacao(StatusSolicitacao.PENDENTE, null);
        when(solicitacaoRepository.findById(1L)).thenReturn(Optional.of(solicitacao));
        when(solicitacaoRepository.save(any(Solicitacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.updateStatus(1L, new SolicitacaoStatusDTO(StatusSolicitacao.CONCLUIDO, null));

        assertThat(solicitacao.getStatus()).isEqualTo(StatusSolicitacao.CONCLUIDO);
        assertThat(solicitacao.getResolvedAt()).isNotNull();
    }

    @Test
    void reabrirLimpaDataDeResolucaoEMantemPrioridade() {
        Solicitacao solicitacao =
                solicitacao(StatusSolicitacao.CONCLUIDO, LocalDateTime.now().minusDays(1));
        when(solicitacaoRepository.findById(1L)).thenReturn(Optional.of(solicitacao));
        when(solicitacaoRepository.save(any(Solicitacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.updateStatus(1L, new SolicitacaoStatusDTO(StatusSolicitacao.EM_ESPERA, null));

        assertThat(solicitacao.getResolvedAt()).isNull();
        assertThat(solicitacao.getPrioridade()).isEqualTo(PrioridadeSolicitacao.MEDIA);
    }

    @Test
    void atualizarStatusDeSolicitacaoInexistenteFalha() {
        when(solicitacaoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateStatus(
                        99L, new SolicitacaoStatusDTO(StatusSolicitacao.CONCLUIDO, null)))
                .hasMessageContaining("99");
    }

    private Solicitacao solicitacao(StatusSolicitacao status, LocalDateTime resolvedAt) {
        Solicitacao s = new Solicitacao();
        s.setId(1L);
        s.setTitulo("Ar-condicionado pingando");
        s.setDescricao("Ar-condicionado da sala 3 pingando no piso.");
        s.setSolicitanteNome("João");
        s.setStatus(status);
        s.setPrioridade(PrioridadeSolicitacao.MEDIA);
        s.setResolvedAt(resolvedAt);
        return s;
    }
}
