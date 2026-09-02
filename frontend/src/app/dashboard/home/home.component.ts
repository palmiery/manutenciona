import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { SolicitacaoService } from '../../core/services/solicitacao.service';
import { Solicitacao, DashboardStats } from '../../core/models/solicitacao.model';
import { BadgeStatusComponent } from '../../shared/components/badge-status/badge-status.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, BadgeStatusComponent, DatePipe],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  private solicitacaoService = inject(SolicitacaoService);

  stats = signal<DashboardStats>({ pendentes: 0, emEspera: 0, concluidos: 0, total: 0 });
  recentes = signal<Solicitacao[]>([]);
  loading = signal(true);

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
        // Dados simulados para desenvolvimento
        this.recentes.set([]);
        this.loading.set(false);
      },
    });
  }
}
