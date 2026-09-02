import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SalaService } from '../../core/services/sala.service';
import { Sala, SalaCreate } from '../../core/models/sala.model';

@Component({
  selector: 'app-salas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './salas.component.html',
})
export class SalasComponent implements OnInit {
  private salaService = inject(SalaService);

  salas = signal<Sala[]>([]);
  loading = signal(true);
  showForm = signal(false);
  editingId = signal<number | null>(null);

  form: SalaCreate = { nome: '', descricao: '', localizacao: '' };

  ngOnInit() {
    this.load();
  }

  load() {
    this.loading.set(true);
    this.salaService.getAll().subscribe({
      next: (data) => { this.salas.set(data); this.loading.set(false); },
      error: () => { this.salas.set([]); this.loading.set(false); },
    });
  }

  openNew() {
    this.form = { nome: '', descricao: '', localizacao: '' };
    this.editingId.set(null);
    this.showForm.set(true);
  }

  openEdit(sala: Sala) {
    this.form = { nome: sala.nome, descricao: sala.descricao, localizacao: sala.localizacao };
    this.editingId.set(sala.id);
    this.showForm.set(true);
  }

  save() {
    const id = this.editingId();
    const obs = id
      ? this.salaService.update(id, this.form)
      : this.salaService.create(this.form);

    obs.subscribe({
      next: () => { this.showForm.set(false); this.load(); },
      error: (err) => console.error(err),
    });
  }

  delete(id: number) {
    if (confirm('Deseja remover esta sala?')) {
      this.salaService.delete(id).subscribe({ next: () => this.load() });
    }
  }

  cancel() {
    this.showForm.set(false);
  }
}
