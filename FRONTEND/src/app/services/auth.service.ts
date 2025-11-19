import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginRequest, LoginResponse, Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private currentUserSubject = new BehaviorSubject<Usuario | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    // Cargar usuario del localStorage si existe
    const savedUser = localStorage.getItem('currentUser');
    if (savedUser) {
      this.currentUserSubject.next(JSON.parse(savedUser));
    }
  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest, { headers })
      .pipe(
        tap(response => {
          if (response.id && response.message === 'Login exitoso') {
            const user: Usuario = {
              id: response.id!,
              nombre: response.nombre!,
              email: response.email!,
              telefono: response.telefono,
              direccion: response.direccion,
              rol: response.rol as 'CLIENTE' | 'ADMIN',
              activo: true
            };
            this.setCurrentUser(user);
          }
        })
      );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('returnUrl');
    this.currentUserSubject.next(null);
  }

  setReturnUrl(url: string): void {
    localStorage.setItem('returnUrl', url);
  }

  getReturnUrl(): string | null {
    return localStorage.getItem('returnUrl');
  }

  clearReturnUrl(): void {
    localStorage.removeItem('returnUrl');
  }

  getCurrentUser(): Usuario | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return this.currentUserSubject.value !== null;
  }

  isAdmin(): boolean {
    return this.currentUserSubject.value?.rol === 'ADMIN';
  }

  isCliente(): boolean {
    return this.currentUserSubject.value?.rol === 'CLIENTE';
  }

  private setCurrentUser(user: Usuario): void {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }
}

