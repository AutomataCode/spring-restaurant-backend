package utp.edu.pe.restaurante.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utp.edu.pe.restaurante.service.FileStorageService;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para manejar la subida y descarga de archivos
 */
@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Endpoint para subir una imagen de plato
     */
    @PostMapping("/upload/plato")
    public ResponseEntity<Map<String, String>> uploadPlatoImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String fileUrl = fileStorageService.saveFile(file, "platos");
            response.put("url", fileUrl);
            response.put("message", "Imagen subida exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "Error al subir la imagen: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Endpoint para subir una imagen de categoría
     */
    @PostMapping("/upload/categoria")
    public ResponseEntity<Map<String, String>> uploadCategoriaImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String fileUrl = fileStorageService.saveFile(file, "categorias");
            response.put("url", fileUrl);
            response.put("message", "Imagen subida exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "Error al subir la imagen: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}

