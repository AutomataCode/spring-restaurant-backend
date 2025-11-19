import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/usuario.model';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    password: ''
  };

  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.loginRequest.email || !this.loginRequest.password) {
      this.errorMessage = 'Por favor, complete todos los campos';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login(this.loginRequest).subscribe({
      next: (response) => {
        this.isLoading = false;
        if (response.message === 'Login exitoso') {
          // Verificar si el usuario es admin
          const isAdmin = response.rol === 'ADMIN' || response.rol === 'admin';
          
          // Obtener la URL de retorno guardada
          const returnUrl = this.authService.getReturnUrl();
          
          if (returnUrl && returnUrl !== '/login' && !isAdmin) {
            // Redirigir a la URL donde estaba antes (solo si no es admin)
            this.authService.clearReturnUrl();
            this.router.navigateByUrl(returnUrl);
          } else {
            // Si es admin, siempre redirigir al panel admin
            // Si no hay URL de retorno, redirigir según el rol
            if (isAdmin) {
              this.authService.clearReturnUrl(); // Limpiar returnUrl si existe
              this.router.navigate(['/admin']);
            } else {
              this.router.navigate(['/menu']);
            }
          }
        } else {
          this.errorMessage = response.message || 'Error al iniciar sesión';
        }
      },
      error: (error) => {
        this.isLoading = false;
        if (error.error && error.error.message) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = 'Error al conectar con el servidor. Verifique que el backend esté corriendo.';
        }
        console.error('Error en login:', error);
      }
    });
  }
}

