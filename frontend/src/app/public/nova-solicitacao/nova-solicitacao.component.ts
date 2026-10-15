import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SolicitacaoService } from '../../core/services/solicitacao.service';
import { SalaService } from '../../core/services/sala.service';
import { EquipamentoService } from '../../core/services/equipamento.service';
import { SolicitacaoCreate } from '../../core/models/solicitacao.model';
import { Sala } from '../../core/models/sala.model';
import { Equipamento } from '../../core/models/equipamento.model';

@Component({
  selector: 'app-nova-solicitacao',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nova-solicitacao.component.html',
})
export class NovaSolicitacaoComponent implements OnInit {
  private solicitacaoService = inject(SolicitacaoService);
  private salaService = inject(SalaService);
  private equipamentoService = inject(EquipamentoService);
  private router = inject(Router);

  salas = signal<Sala[]>([]);
  equipamentos = signal<Equipamento[]>([]);
  loading = signal(false);
  success = signal(false);
  errorMessage = signal<string>('');

  form: SolicitacaoCreate = {
    titulo: '',
    descricao: '',
    solicitanteNome: '',
    solicitanteContato: '',
    prioridade: 'MEDIA',
    salaId: undefined,
    equipamentoId: undefined,
  };

  ngOnInit() {
    this.salaService.getAll().subscribe({ next: (s) => this.salas.set(s), error: () => {} });
  }

  onSalaChange() {
    this.form.equipamentoId = undefined;
    if (this.form.salaId) {
      this.equipamentoService.getAll(this.form.salaId).subscribe({
        next: (e) => this.equipamentos.set(e),
        error: () => this.equipamentos.set([]),
      });
    } else {
      this.equipamentos.set([]);
    }
  }

  submit() {
    if (
      !this.form.solicitanteNome?.trim() ||
      !this.form.titulo?.trim() ||
      !this.form.descricao?.trim() ||
      !this.form.salaId ||
      !this.form.equipamentoId ||
      !this.form.prioridade
    ) {
      this.errorMessage.set('Por favor, preencha todos os campos obrigatórios (*).');
      return;
    }

    if (this.form.descricao.trim().length < 10) {
      this.errorMessage.set('A descrição do defeito deve conter pelo menos 10 caracteres.');
      return;
    }

    this.errorMessage.set('');
    this.loading.set(true);
    this.solicitacaoService.create(this.form).subscribe({
      next: () => {
        this.loading.set(false);
        this.success.set(true);
      },
      error: (err) => {
        this.loading.set(false);
        this.errorMessage.set(err?.error?.message ?? 'Erro ao enviar a solicitação. Verifique os dados.');
      },
    });
  }

  newRequest() {
    this.success.set(false);
    this.errorMessage.set('');
    this.form = {
      titulo: '',
      descricao: '',
      solicitanteNome: '',
      solicitanteContato: '',
      prioridade: 'MEDIA',
      salaId: undefined,
      equipamentoId: undefined,
    };
  }
}
