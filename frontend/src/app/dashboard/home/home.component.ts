import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SolicitacaoService } from '../../core/services/solicitacao.service';
import { Solicitacao, DashboardStats, StatusSolicitacao, PrioridadeSolicitacao } from '../../core/models/solicitacao.model';
import { BadgeStatusComponent } from '../../shared/components/badge-status/badge-status.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, BadgeStatusComponent],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  private solicitacaoService = inject(SolicitacaoService);

  stats = signal<DashboardStats>({ pendentes: 0, emEspera: 0, concluidos: 0, total: 0 });
  recentes = signal<Solicitacao[]>([]);
  loading = signal(true);

  // Edição Rápida (US03)
  editingSolicitacao = signal<Solicitacao | null>(null);
  novoStatus = signal<StatusSolicitacao>('PENDENTE');
  novaPrioridade = signal<PrioridadeSolicitacao>('MEDIA');
  savingEdit = signal(false);

  ngOnInit() {
    this.loadDashboard();
  }

  loadDashboard() {
    this.loading.set(true);
    this.solicitacaoService.getDashboardStats().subscribe({
      next: (data) => this.stats.set(data),
      error: () => console.warn('Backend não disponível — usando dados simulados'),
    });

    this.solicitacaoService.getAll().subscribe({
      next: (data) => {
        this.recentes.set(data.slice(0, 10));
        this.loading.set(false);
      },
      error: () => {
        this.recentes.set([]);
        this.loading.set(false);
      },
    });
  }

  openQuickEdit(item: Solicitacao) {
    this.editingSolicitacao.set(item);
    this.novoStatus.set(item.status);
    this.novaPrioridade.set(item.prioridade);
  }

  closeQuickEdit() {
    this.editingSolicitacao.set(null);
  }

  saveQuickEdit() {
    const item = this.editingSolicitacao();
    if (!item) return;

    this.savingEdit.set(true);
    this.solicitacaoService.updateStatus(item.id, this.novoStatus(), this.novaPrioridade()).subscribe({
      next: () => {
        this.savingEdit.set(false);
        this.closeQuickEdit();
        this.loadDashboard();
      },
      error: (err) => {
        this.savingEdit.set(false);
        console.error('Erro ao atualizar solicitação:', err);
        alert('Não foi possível atualizar a solicitação.');
      },
    });
  }
}
