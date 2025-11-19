package utp.edu.pe.restaurante.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

