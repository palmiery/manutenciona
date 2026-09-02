import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitacao, SolicitacaoCreate, DashboardStats, StatusSolicitacao } from '../models/solicitacao.model';

@Injectable({ providedIn: 'root' })
export class SolicitacaoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/solicitacoes';

  getAll(status?: StatusSolicitacao): Observable<Solicitacao[]> {
    let params = new HttpParams();
    if (status) params = params.set('status', status);
    return this.http.get<Solicitacao[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<Solicitacao> {
    return this.http.get<Solicitacao>(`${this.apiUrl}/${id}`);
  }

  getDashboardStats(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${this.apiUrl}/dashboard`);
  }

  create(data: SolicitacaoCreate): Observable<Solicitacao> {
    return this.http.post<Solicitacao>(this.apiUrl, data);
  }

  updateStatus(id: number, status: StatusSolicitacao): Observable<Solicitacao> {
    return this.http.patch<Solicitacao>(`${this.apiUrl}/${id}/status`, { status });
  }

  update(id: number, data: Partial<SolicitacaoCreate>): Observable<Solicitacao> {
    return this.http.put<Solicitacao>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
