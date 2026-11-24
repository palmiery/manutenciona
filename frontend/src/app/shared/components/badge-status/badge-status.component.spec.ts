import { TestBed } from '@angular/core/testing';
import { BadgeStatusComponent } from './badge-status.component';
import { StatusSolicitacao } from '../../../core/models/solicitacao.model';

describe('BadgeStatusComponent', () => {
  function render(status: StatusSolicitacao): HTMLElement {
    const fixture = TestBed.createComponent(BadgeStatusComponent);
    fixture.componentRef.setInput('status', status);
    fixture.detectChanges();
    return (fixture.nativeElement as HTMLElement).querySelector('span')!;
  }

  it('mostra "Pendente" em vermelho para PENDENTE', () => {
    const span = render('PENDENTE');
    expect(span.textContent?.trim()).toBe('Pendente');
    expect(span.className).toContain('bg-red-100');
  });

  it('mostra "Em Espera" em amarelo para EM_ESPERA', () => {
    const span = render('EM_ESPERA');
    expect(span.textContent?.trim()).toBe('Em Espera');
    expect(span.className).toContain('bg-yellow-100');
  });

  it('mostra "Concluído" em verde para CONCLUIDO', () => {
    const span = render('CONCLUIDO');
    expect(span.textContent?.trim()).toBe('Concluído');
    expect(span.className).toContain('bg-green-100');
  });
});
