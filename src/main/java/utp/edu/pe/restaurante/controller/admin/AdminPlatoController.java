package utp.edu.pe.restaurante.controller.admin;

import java.util.List;

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
import utp.edu.pe.restaurante.mapper.PlatoMapper;
import utp.edu.pe.restaurante.service.PlatoService;

@RestController
@RequestMapping("/api/admin/platos")
@CrossOrigin(origins = "*")
public class AdminPlatoController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private PlatoMapper platoMapper;

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
        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }

        Plato plato = platoMapper.toEntity(request);
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        plato.setCategoria(categoria);

        Plato savedPlato = platoService.save(plato);
        return ResponseEntity.status(HttpStatus.CREATED).body(platoMapper.toDTO(savedPlato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoDTO> updatePlato(@PathVariable Long id, @RequestBody UpdatePlatoRequest request) {

        if (request.getCategoriaId() == null) {
            throw new ValidationException("El ID de la categoría es obligatorio");
        }

        Plato platoDetails = platoMapper.toEntity(request);

        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());
        platoDetails.setCategoria(categoria);

        Plato updatedPlato = platoService.update(id, platoDetails);
        return ResponseEntity.ok(platoMapper.toDTO(updatedPlato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarPlato(@PathVariable Long id) {
        platoService.desactivarPlato(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<PlatoDTO> reactivarPlato(@PathVariable Long id) {
        Plato plato = platoService.changeStatus(id, true);
        return ResponseEntity.ok(platoMapper.toDTO(plato));
    }

}
