package utp.edu.pe.restaurante.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interfaz para el servicio de almacenamiento de archivos
 */
public interface FileStorageService {
    
    /**
     * Guarda un archivo de imagen y retorna la URL relativa
     * 
     * @param file Archivo a guardar
     * @param subfolder Subcarpeta donde guardar (ej: "platos", "categorias")
     * @return URL relativa del archivo guardado (ej: "/uploads/platos/imagen.jpg")
     * @throws IOException Si hay error al guardar el archivo
     */
    String saveFile(MultipartFile file, String subfolder) throws IOException;
    
    /**
     * Elimina un archivo del sistema
     * 
     * @param fileUrl URL del archivo a eliminar
     * @return true si se eliminó correctamente, false si no existía
     */
    boolean deleteFile(String fileUrl);
    
    /**
     * Obtiene la ruta completa del directorio de almacenamiento
     * 
     * @return Path del directorio de almacenamiento
     */
    Path getStoragePath();
    
    /**
     * Verifica si un archivo existe
     * 
     * @param fileUrl URL del archivo
     * @return true si existe, false si no
     */
    boolean fileExists(String fileUrl);
}

