import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipamento, EquipamentoCreate } from '../models/equipamento.model';

@Injectable({ providedIn: 'root' })
export class EquipamentoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/equipamentos';

  getAll(salaId?: number): Observable<Equipamento[]> {
    let params = new HttpParams();
    if (salaId) params = params.set('salaId', salaId.toString());
    return this.http.get<Equipamento[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<Equipamento> {
    return this.http.get<Equipamento>(`${this.apiUrl}/${id}`);
  }

  create(data: EquipamentoCreate): Observable<Equipamento> {
    return this.http.post<Equipamento>(this.apiUrl, data);
  }

  update(id: number, data: EquipamentoCreate): Observable<Equipamento> {
    return this.http.put<Equipamento>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
