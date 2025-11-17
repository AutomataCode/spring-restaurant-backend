package utp.edu.pe.restaurante.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import utp.edu.pe.restaurante.dto.PlatoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePlatoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePlatoRequest;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.exception.ValidationException;
import utp.edu.pe.restaurante.mapper.PlatoMapper;
import utp.edu.pe.restaurante.service.FileStorageService;
import utp.edu.pe.restaurante.service.PlatoService;

@RestController
@RequestMapping("/api/admin/platos")
@CrossOrigin(origins = "*")
public class AdminPlatoController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private PlatoMapper platoMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> getAllPlatos() {
        List<Plato> platos = platoService.findAll();
        List<PlatoDTO> platosDTO = platoMapper.toDTOList(platos);
        return ResponseEntity.ok(platosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> getPlato(@PathVariable Long id) {
        Plato plato = platoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        return ResponseEntity.ok(platoMapper.toDTO(plato));
    }

    @PostMapping
    public ResponseEntity<PlatoDTO> createPlato(@RequestBody CreatePlatoRequest request) {
        // Validar que se envíe categoriaId
        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }

        // Usar el mapper para convertir el request a entidad
        Plato plato = platoMapper.toEntity(request);
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        plato.setCategoria(categoria);

        Plato savedPlato = platoService.save(plato);
        return ResponseEntity.status(HttpStatus.CREATED).body(platoMapper.toDTO(savedPlato));
    }

    /**
     * Endpoint alternativo para crear plato con subida de imagen
     * Permite subir una imagen junto con los datos del plato
     */
    @PostMapping(value = "/con-imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlatoDTO> createPlatoWithImage(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") java.math.BigDecimal precio,
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            @RequestParam(value = "imagenUrl", required = false) String imagenUrl,
            @RequestParam(value = "tiempoPreparacion", required = false) Integer tiempoPreparacion,
            @RequestParam(value = "disponibleDomicilio", required = false, defaultValue = "true") Boolean disponibleDomicilio) {
        
        try {
            // Si se subió una imagen, guardarla
            String finalImagenUrl = imagenUrl;
            if (imagen != null && !imagen.isEmpty()) {
                finalImagenUrl = fileStorageService.saveFile(imagen, "platos");
            }

            // Crear el request
            CreatePlatoRequest request = new CreatePlatoRequest();
            request.setNombre(nombre);
            request.setDescripcion(descripcion);
            request.setPrecio(precio);
            request.setCategoriaId(categoriaId);
            request.setImagenUrl(finalImagenUrl);
            request.setTiempoPreparacion(tiempoPreparacion);
            request.setDisponibleDomicilio(disponibleDomicilio);

            // Usar el método existente
            return createPlato(request);
        } catch (Exception e) {
            throw new ValidationException("Error al procesar la imagen: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoDTO> updatePlato(@PathVariable Long id, @RequestBody UpdatePlatoRequest request) {

        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }

        Plato platoDetails = platoMapper.toEntity(request);

        // Asignar la categoría con el ID
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        platoDetails.setCategoria(categoria);

        Plato updatedPlato = platoService.update(id, platoDetails);
        return ResponseEntity.ok(platoMapper.toDTO(updatedPlato));
    }

    /**
     * Endpoint para actualizar plato con subida de imagen
     * Permite actualizar un plato y subir una nueva imagen
     */
    @PutMapping(value = "/{id}/con-imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlatoDTO> updatePlatoWithImage(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") java.math.BigDecimal precio,
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            @RequestParam(value = "imagenUrl", required = false) String imagenUrl,
            @RequestParam(value = "tiempoPreparacion", required = false) Integer tiempoPreparacion,
            @RequestParam(value = "disponibleDomicilio", required = false) Boolean disponibleDomicilio,
            @RequestParam(value = "activo", required = false) Boolean activo) {
        
        try {
            // Obtener el plato existente para preservar la imagen si no se sube una nueva
            Plato platoExistente = platoService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
            
            // Si se subió una nueva imagen, guardarla
            String finalImagenUrl = imagenUrl;
            if (imagen != null && !imagen.isEmpty()) {
                finalImagenUrl = fileStorageService.saveFile(imagen, "platos");
            } else if (finalImagenUrl == null || finalImagenUrl.trim().isEmpty()) {
                // Si no se subió nueva imagen y no se proporcionó imagenUrl, preservar la existente
                finalImagenUrl = platoExistente.getImagenUrl();
            }

            // Crear el request
            UpdatePlatoRequest updateRequest = new UpdatePlatoRequest();
            updateRequest.setNombre(nombre);
            updateRequest.setDescripcion(descripcion);
            updateRequest.setPrecio(precio);
            updateRequest.setCategoriaId(categoriaId);
            updateRequest.setImagenUrl(finalImagenUrl);
            updateRequest.setTiempoPreparacion(tiempoPreparacion);
            updateRequest.setDisponibleDomicilio(disponibleDomicilio);
            updateRequest.setActivo(activo);

            // Usar el método existente
            return updatePlato(id, updateRequest);
        } catch (Exception e) {
            throw new ValidationException("Error al procesar la imagen: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarPlato(@PathVariable Long id) {
        platoService.desactivarPlato(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/permanente")
    public ResponseEntity<Void> deletePlato(@PathVariable Long id) {
        platoService.deletePlato(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<PlatoDTO> reactivarPlato(@PathVariable Long id) {
        Plato plato = platoService.changeStatus(id, true);
        return ResponseEntity.ok(platoMapper.toDTO(plato));
    }

}
