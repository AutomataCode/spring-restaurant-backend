package utp.edu.pe.restaurante.controller.Public;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.PlatoDTO;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.mapper.PlatoMapper;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*") // Para Angular
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private PlatoMapper platoMapper;

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> getAllPlatosActivos() {
        List<Plato> platos = platoService.findAllActive();
        List<PlatoDTO> platosDTO = platoMapper.toDTOList(platos);
        return ResponseEntity.ok(platosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> getPlatoById(@PathVariable Long id) {
        Plato plato = platoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        return ResponseEntity.ok(platoMapper.toDTO(plato));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<PlatoDTO>> getPlatosByCategoria(@PathVariable Long categoriaId) {
        List<Plato> platos = platoService.findByCategoriaId(categoriaId);
        List<PlatoDTO> platosDTO = platoMapper.toDTOList(platos);
        return ResponseEntity.ok(platosDTO);
    }

    @GetMapping("/domicilio")
    public ResponseEntity<List<PlatoDTO>> getPlatosDomicilio() {
        List<Plato> platos = platoService.findAvailableForDomicilio();
        List<PlatoDTO> platosDTO = platoMapper.toDTOList(platos);
        return ResponseEntity.ok(platosDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PlatoDTO>> searchPlatos(@RequestParam String nombre) {
        List<Plato> platos = platoService.searchByNombre(nombre);
        List<PlatoDTO> platosDTO = platoMapper.toDTOList(platos);
        return ResponseEntity.ok(platosDTO);
    }

}

