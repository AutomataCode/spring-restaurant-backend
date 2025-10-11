package utp.edu.pe.restaurante.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.PlatoDTO;
import utp.edu.pe.restaurante.dto.request.CreatePlatoRequest;
import utp.edu.pe.restaurante.dto.request.UpdatePlatoRequest;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.exception.ValidationException;
import utp.edu.pe.restaurante.service.PlatoService;


@RestController
@RequestMapping("/api/admin/platos")
@CrossOrigin(origins = "*")
public class AdminPlatoController {
	

    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> getAllPlatos() {
        List<Plato> platos = platoService.findAll();
        List<PlatoDTO> platosDTO = platos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(platosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> getPlato(@PathVariable Long id) {
        Plato plato = platoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        return ResponseEntity.ok(convertToDTO(plato));
    }

    @PostMapping
    public ResponseEntity<PlatoDTO> createPlato(@RequestBody CreatePlatoRequest request) {
    	// Validar que se envíe categoriaId
        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }
        
        // Crear la categoría con solo el ID
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        
        Plato plato = new Plato();
        plato.setNombre(request.getNombre());
        plato.setDescripcion(request.getDescripcion());
        plato.setPrecio(request.getPrecio());
        plato.setCategoria(categoria); // Asignar la categoría con ID
        plato.setImagenUrl(request.getImagenUrl());
        plato.setTiempoPreparacion(request.getTiempoPreparacion());
        plato.setDisponibleDomicilio(request.getDisponibleDomicilio() != null ? request.getDisponibleDomicilio() : true);
        
        Plato savedPlato = platoService.save(plato);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedPlato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoDTO> updatePlato(@PathVariable Long id, @RequestBody UpdatePlatoRequest request) {
    	// Validar que se envíe categoriaId si se está actualizando
        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }
        
        // Crear la categoría con solo el ID
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        
        Plato platoDetails = new Plato();
        platoDetails.setNombre(request.getNombre());
        platoDetails.setDescripcion(request.getDescripcion());
        platoDetails.setPrecio(request.getPrecio());
        platoDetails.setCategoria(categoria); // Asignar la categoría
        platoDetails.setImagenUrl(request.getImagenUrl());
        platoDetails.setTiempoPreparacion(request.getTiempoPreparacion());
        platoDetails.setDisponibleDomicilio(request.getDisponibleDomicilio());
        platoDetails.setActivo(request.getActivo());
        
        Plato updatedPlato = platoService.update(id, platoDetails);
        return ResponseEntity.ok(convertToDTO(updatedPlato));
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarPlato(@PathVariable Long id) {
        platoService.desactivarPlato(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<PlatoDTO> reactivarPlato(@PathVariable Long id) {
        Plato plato = platoService.changeStatus(id, true);
        return ResponseEntity.ok(convertToDTO(plato));
    }
    

    

    private PlatoDTO convertToDTO(Plato plato) {
        return new PlatoDTO(
                plato.getId(),
                plato.getNombre(),
                plato.getDescripcion(),
                plato.getPrecio(),
                plato.getCategoria().getId(),
                plato.getCategoria().getNombre(),
                plato.getImagenUrl(),
                plato.getTiempoPreparacion(),
                plato.getDisponibleDomicilio(),
                plato.getActivo()
        );
    }


}
