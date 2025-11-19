package utp.edu.pe.restaurante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.restaurante.dto.request.LoginRequest;
import utp.edu.pe.restaurante.dto.response.LoginResponse;
import utp.edu.pe.restaurante.entity.Usuario;
import utp.edu.pe.restaurante.exception.UnauthorizedException;
import utp.edu.pe.restaurante.repository.UsuarioRepository;
import utp.edu.pe.restaurante.service.AuthService;

import java.util.Optional;

/**
 * Implementación del servicio de autenticación
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public LoginResponse login(LoginRequest request) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        
        if (usuarioOpt.isEmpty()) {
            throw new UnauthorizedException("Credenciales inválidas");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Verificar si el usuario está activo
        if (!usuario.getActivo()) {
            throw new UnauthorizedException("Usuario inactivo");
        }
        
        // Verificar contraseña (comparación simple - en producción debería usar BCrypt)
        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("Credenciales inválidas");
        }
        
        // Crear respuesta de login exitoso
        LoginResponse response = new LoginResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setTelefono(usuario.getTelefono());
        response.setDireccion(usuario.getDireccion());
        response.setRol(usuario.getRol().name());
        response.setMessage("Login exitoso");
        
        return response;
    }
}

