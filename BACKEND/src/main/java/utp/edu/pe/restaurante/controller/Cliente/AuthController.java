package utp.edu.pe.restaurante.controller.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.dto.request.LoginRequest;
import utp.edu.pe.restaurante.dto.response.LoginResponse;
import utp.edu.pe.restaurante.service.AuthService;

/**
 * Controlador para autenticación
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint para iniciar sesión
     * 
     * @param request Datos de login (email y password)
     * @return LoginResponse con los datos del usuario
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
