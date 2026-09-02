package com.manutenciona.api.repository;

import com.manutenciona.api.enums.StatusSolicitacao;
import com.manutenciona.api.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findByStatusOrderByCreatedAtDesc(StatusSolicitacao status);

    List<Solicitacao> findAllByOrderByCreatedAtDesc();

    long countByStatus(StatusSolicitacao status);

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.status = 'PENDENTE'")
    long countPendentes();

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.status = 'EM_ESPERA'")
    long countEmEspera();

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.status = 'CONCLUIDO'")
    long countConcluidos();

    // Para apagar equipamento cadastrado
    List<Solicitacao> findByEquipamentoId(Long equipamentoId);
}
