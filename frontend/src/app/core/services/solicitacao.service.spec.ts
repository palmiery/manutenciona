import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { SolicitacaoService } from './solicitacao.service';

describe('SolicitacaoService', () => {
  const apiUrl = 'http://localhost:8080/api/solicitacoes';
  let service: SolicitacaoService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(SolicitacaoService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('lista sem filtros quando nenhum é informado', () => {
    service.getAll().subscribe();
    const req = http.expectOne(apiUrl);
    expect(req.request.method).toBe('GET');
    expect(req.request.params.keys()).toEqual([]);
    req.flush([]);
  });

  it('envia status e sala juntos ao filtrar (US05)', () => {
    service.getAll('PENDENTE', 3).subscribe();
    const req = http.expectOne((r) => r.url === apiUrl);
    expect(req.request.params.get('status')).toBe('PENDENTE');
    expect(req.request.params.get('salaId')).toBe('3');
    req.flush([]);
  });

  it('atualiza só os campos informados no PATCH de status (US03)', () => {
    service.updateStatus(7, undefined, 'ALTA').subscribe();
    const req = http.expectOne(`${apiUrl}/7/status`);
    expect(req.request.method).toBe('PATCH');
    expect(req.request.body).toEqual({ prioridade: 'ALTA' });
    req.flush({});
  });

  it('busca as estatísticas do dashboard (US01)', () => {
    service.getDashboardStats().subscribe();
    const req = http.expectOne(`${apiUrl}/dashboard`);
    expect(req.request.method).toBe('GET');
    req.flush({});
  });
});
