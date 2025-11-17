package utp.edu.pe.restaurante.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utp.edu.pe.restaurante.service.FileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Implementación del servicio de almacenamiento de archivos
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Value("${app.base.url:http://localhost:8080}")
    private String baseUrl;

    @Override
    public String saveFile(MultipartFile file, String subfolder) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo no puede estar vacío");
        }

        // Validar que sea una imagen
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen");
        }

        // Crear directorio si no existe
        Path uploadPath = Paths.get(uploadDir, subfolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generar nombre único para el archivo
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // Guardar archivo
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retornar URL relativa (ej: /uploads/platos/uuid.jpg)
        return "/uploads/" + subfolder + "/" + uniqueFilename;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return false;
        }

        try {
            // Remover el prefijo /uploads/ si existe
            String relativePath = fileUrl;
            if (fileUrl.startsWith("/uploads/")) {
                relativePath = fileUrl.substring(1); // Remover el primer /
            } else if (fileUrl.startsWith("uploads/")) {
                relativePath = fileUrl;
            } else {
                // Si es una URL completa, extraer solo la ruta relativa
                if (fileUrl.contains("/uploads/")) {
                    relativePath = fileUrl.substring(fileUrl.indexOf("/uploads/") + 1);
                } else {
                    return false; // No es una ruta válida
                }
            }

            Path filePath = Paths.get(relativePath);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }
            return false;
        } catch (IOException e) {
            System.err.println("Error al eliminar archivo: " + fileUrl + " - " + e.getMessage());
            return false;
        }
    }

    @Override
    public Path getStoragePath() {
        return Paths.get(uploadDir);
    }

    @Override
    public boolean fileExists(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return false;
        }

        try {
            String relativePath = fileUrl;
            if (fileUrl.startsWith("/uploads/")) {
                relativePath = fileUrl.substring(1);
            } else if (fileUrl.startsWith("uploads/")) {
                relativePath = fileUrl;
            } else if (fileUrl.contains("/uploads/")) {
                relativePath = fileUrl.substring(fileUrl.indexOf("/uploads/") + 1);
            } else {
                return false;
            }

            Path filePath = Paths.get(relativePath);
            return Files.exists(filePath);
        } catch (Exception e) {
            return false;
        }
    }
}

