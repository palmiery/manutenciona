import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SolicitacaoService } from '../../core/services/solicitacao.service';
import { SalaService } from '../../core/services/sala.service';
import { Solicitacao, DashboardStats, StatusSolicitacao } from '../../core/models/solicitacao.model';
import { Sala } from '../../core/models/sala.model';
import { BadgeStatusComponent } from '../../shared/components/badge-status/badge-status.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, BadgeStatusComponent, DatePipe],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  private solicitacaoService = inject(SolicitacaoService);
  private salaService = inject(SalaService);

  stats = signal<DashboardStats>({ pendentes: 0, emEspera: 0, concluidos: 0, total: 0 });
  recentes = signal<Solicitacao[]>([]);
  salas = signal<Sala[]>([]);
  loading = signal(true);

  // Filtros (US05)
  filtroStatus = signal<string>('');
  filtroSalaId = signal<number | undefined>(undefined);

  ngOnInit() {
    this.loadDashboard();
    this.loadSalas();
    this.aplicarFiltros();
  }

  loadDashboard() {
    this.solicitacaoService.getDashboardStats().subscribe({
      next: (data) => this.stats.set(data),
      error: () => this.stats.set({ pendentes: 0, emEspera: 0, concluidos: 0, total: 0 }),
    });
  }

  loadSalas() {
    this.salaService.getAll().subscribe({
      next: (data) => this.salas.set(data),
      error: () => this.salas.set([]),
    });
  }

  aplicarFiltros() {
    this.loading.set(true);
    const status = this.filtroStatus() ? (this.filtroStatus() as StatusSolicitacao) : undefined;
    const salaId = this.filtroSalaId();

    this.solicitacaoService.getAll(status, salaId).subscribe({
      next: (data) => {
        this.recentes.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.recentes.set([]);
        this.loading.set(false);
      },
    });
  }

  limparFiltros() {
    this.filtroStatus.set('');
    this.filtroSalaId.set(undefined);
    this.aplicarFiltros();
  }
}
