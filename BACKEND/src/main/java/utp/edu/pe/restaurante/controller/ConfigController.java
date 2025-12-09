package utp.edu.pe.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.restaurante.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para obtener configuraciones del sistema
 */
@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*")
public class ConfigController {

    @Value("${app.yape.numero:999888777}")
    private String yapeNumero;

    @Value("${app.yape.codigo:999888777}")
    private String yapeCodigo;

    @Value("${app.yape.whatsapp:999888777}")
    private String yapeWhatsapp;

    @Value("${app.yape.qr.url:}")
    private String yapeQrUrl;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint de health check para Railway
     * 
     * @return Estado de la aplicación
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        try {
            // Verificar conexión a la base de datos
            usuarioRepository.count();
            status.put("status", "UP");
            status.put("database", "CONNECTED");
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            status.put("status", "DOWN");
            status.put("database", "DISCONNECTED");
            status.put("error", e.getMessage());
            return ResponseEntity.status(503).body(status);
        }
    }

    /**
     * Endpoint para obtener la configuración de Yape
     * 
     * @return Configuración de Yape (número, código, WhatsApp, QR)
     */
    @GetMapping("/yape")
    public ResponseEntity<Map<String, String>> getYapeConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("numero", yapeNumero);
        config.put("codigo", yapeCodigo);
        config.put("whatsapp", yapeWhatsapp);
        config.put("qrUrl", yapeQrUrl != null && !yapeQrUrl.isEmpty() ? yapeQrUrl : "");
        return ResponseEntity.ok(config);
    }
}

