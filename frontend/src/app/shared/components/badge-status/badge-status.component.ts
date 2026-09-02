import { Component, input } from '@angular/core';
import { StatusSolicitacao } from '../../../core/models/solicitacao.model';

@Component({
  selector: 'app-badge-status',
  standalone: true,
  template: `
    <span [class]="badgeClass()" class="px-2 py-1 rounded-full text-xs font-semibold">
      {{ label() }}
    </span>
  `,
})
export class BadgeStatusComponent {
  status = input.required<StatusSolicitacao>();

  label() {
    const map: Record<StatusSolicitacao, string> = {
      PENDENTE: 'Pendente',
      EM_ESPERA: 'Em Espera',
      CONCLUIDO: 'Concluído',
    };
    return map[this.status()];
  }

  badgeClass() {
    const map: Record<StatusSolicitacao, string> = {
      PENDENTE: 'bg-red-100 text-red-700',
      EM_ESPERA: 'bg-yellow-100 text-yellow-700',
      CONCLUIDO: 'bg-green-100 text-green-700',
    };
    return map[this.status()];
  }
}
