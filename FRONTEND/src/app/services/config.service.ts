import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface YapeConfig {
  numero: string;
  codigo: string;
  whatsapp: string;
  qrUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getYapeConfig(): Observable<YapeConfig> {
    return this.http.get<YapeConfig>(`${this.apiUrl}/config/yape`);
  }
}

