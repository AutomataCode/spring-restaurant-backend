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
import java.text.Normalizer;
import java.util.regex.Pattern;

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

        // Obtener nombre original del archivo
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            originalFilename = "imagen_" + System.currentTimeMillis();
        }

        // Extraer extensión
        String extension = "";
        String nameWithoutExtension = originalFilename;
        if (originalFilename.contains(".")) {
            int lastDotIndex = originalFilename.lastIndexOf(".");
            extension = originalFilename.substring(lastDotIndex);
            nameWithoutExtension = originalFilename.substring(0, lastDotIndex);
        }

        // Sanitizar el nombre del archivo (remover acentos, caracteres especiales, etc.)
        String sanitizedName = sanitizeFilename(nameWithoutExtension);
        
        // Si el nombre está vacío después de sanitizar, usar un nombre por defecto
        if (sanitizedName.isEmpty()) {
            sanitizedName = "imagen_" + System.currentTimeMillis();
        }

        // Construir el nombre final del archivo
        String finalFilename = sanitizedName + extension;

        // Si el archivo ya existe, agregar un sufijo numérico
        Path filePath = uploadPath.resolve(finalFilename);
        int counter = 1;
        while (Files.exists(filePath)) {
            String newName = sanitizedName + "_" + counter + extension;
            filePath = uploadPath.resolve(newName);
            counter++;
        }

        // Guardar archivo
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retornar URL relativa (ej: /uploads/platos/nombre-archivo.jpg)
        return "/uploads/" + subfolder + "/" + filePath.getFileName().toString();
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return false;
        }

        try {
            // Extraer la ruta relativa del archivo desde la URL
            String relativePath = fileUrl;
            if (fileUrl.startsWith("/uploads/")) {
                // Remover /uploads/ y obtener la ruta relativa (ej: platos/imagen.jpg)
                relativePath = fileUrl.substring("/uploads/".length());
            } else if (fileUrl.startsWith("uploads/")) {
                relativePath = fileUrl.substring("uploads/".length());
            } else if (fileUrl.contains("/uploads/")) {
                // Si es una URL completa, extraer solo la ruta relativa
                relativePath = fileUrl.substring(fileUrl.indexOf("/uploads/") + "/uploads/".length());
            } else {
                // Asumir que es una ruta relativa directa
                relativePath = fileUrl;
            }

            // Construir la ruta completa usando el directorio base de uploads
            Path filePath = Paths.get(uploadDir, relativePath);
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
            // Extraer la ruta relativa del archivo desde la URL
            String relativePath = fileUrl;
            if (fileUrl.startsWith("/uploads/")) {
                // Remover /uploads/ y obtener la ruta relativa (ej: platos/imagen.jpg)
                relativePath = fileUrl.substring("/uploads/".length());
            } else if (fileUrl.startsWith("uploads/")) {
                relativePath = fileUrl.substring("uploads/".length());
            } else if (fileUrl.contains("/uploads/")) {
                // Si es una URL completa, extraer solo la ruta relativa
                relativePath = fileUrl.substring(fileUrl.indexOf("/uploads/") + "/uploads/".length());
            } else {
                // Asumir que es una ruta relativa directa
                relativePath = fileUrl;
            }

            // Construir la ruta completa usando el directorio base de uploads
            Path filePath = Paths.get(uploadDir, relativePath);
            return Files.exists(filePath);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sanitiza el nombre del archivo removiendo caracteres especiales y acentos
     * @param filename Nombre original del archivo
     * @return Nombre sanitizado
     */
    private String sanitizeFilename(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }

        // Normalizar y remover acentos
        String normalized = Normalizer.normalize(filename, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalized).replaceAll("");

        // Reemplazar espacios con guiones bajos y remover caracteres especiales
        String sanitized = withoutAccents
                .replaceAll("[^a-zA-Z0-9\\s\\-_]", "") // Remover caracteres especiales excepto espacios, guiones y guiones bajos
                .replaceAll("\\s+", "_") // Reemplazar espacios múltiples con un guión bajo
                .replaceAll("_+", "_") // Reemplazar múltiples guiones bajos con uno solo
                .replaceAll("^_|_$", "") // Remover guiones bajos al inicio y final
                .toLowerCase(); // Convertir a minúsculas

        // Limitar la longitud del nombre (máximo 200 caracteres)
        if (sanitized.length() > 200) {
            sanitized = sanitized.substring(0, 200);
        }

        return sanitized;
    }
}

