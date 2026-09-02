import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sala, SalaCreate } from '../models/sala.model';

@Injectable({ providedIn: 'root' })
export class SalaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/salas';

  getAll(): Observable<Sala[]> {
    return this.http.get<Sala[]>(this.apiUrl);
  }

  getById(id: number): Observable<Sala> {
    return this.http.get<Sala>(`${this.apiUrl}/${id}`);
  }

  create(data: SalaCreate): Observable<Sala> {
    return this.http.post<Sala>(this.apiUrl, data);
  }

  update(id: number, data: SalaCreate): Observable<Sala> {
    return this.http.put<Sala>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
