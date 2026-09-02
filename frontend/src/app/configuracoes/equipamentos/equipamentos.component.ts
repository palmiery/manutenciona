import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EquipamentoService } from '../../core/services/equipamento.service';
import { SalaService } from '../../core/services/sala.service';
import { Equipamento, EquipamentoCreate } from '../../core/models/equipamento.model';
import { Sala } from '../../core/models/sala.model';

@Component({
  selector: 'app-equipamentos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './equipamentos.component.html',
})
export class EquipamentosComponent implements OnInit {
  private equipamentoService = inject(EquipamentoService);
  private salaService = inject(SalaService);

  equipamentos = signal<Equipamento[]>([]);
  salas = signal<Sala[]>([]);
  loading = signal(true);
  showForm = signal(false);
  editingId = signal<number | null>(null);

  form: EquipamentoCreate = { nome: '', descricao: '', numeroSerie: '', salaId: undefined };

  ngOnInit() {
    this.load();
    this.salaService.getAll().subscribe({ next: (s) => this.salas.set(s), error: () => {} });
  }

  load() {
    this.loading.set(true);
    this.equipamentoService.getAll().subscribe({
      next: (data) => { this.equipamentos.set(data); this.loading.set(false); },
      error: () => { this.equipamentos.set([]); this.loading.set(false); },
    });
  }

  openNew() {
    this.form = { nome: '', descricao: '', numeroSerie: '', salaId: undefined };
    this.editingId.set(null);
    this.showForm.set(true);
  }

  openEdit(eq: Equipamento) {
    this.form = { nome: eq.nome, descricao: eq.descricao, numeroSerie: eq.numeroSerie, salaId: eq.salaId };
    this.editingId.set(eq.id);
    this.showForm.set(true);
  }

  save() {
    const id = this.editingId();
    const obs = id
      ? this.equipamentoService.update(id, this.form)
      : this.equipamentoService.create(this.form);
    obs.subscribe({ next: () => { this.showForm.set(false); this.load(); }, error: (e) => console.error(e) });
  }

  delete(id: number) {
      if (confirm('Deseja remover este equipamento?')) {
        this.equipamentoService.delete(id).subscribe({
          next: () => {
            this.load();
          },
          error: (err) => {
            console.error('Erro ao excluir equipamento:', err);
            alert('Não foi possível remover o equipamento. Verifique se ele não possui solicitações vinculadas.');
          }
        });
      }
    }

  cancel() { this.showForm.set(false); }
}
