package utp.edu.pe.restaurante.controller.Public;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;



@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*") // Para Angular
public class PlatoController {
	

    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<Plato>> getAllPlatosActivos() {
        List<Plato> platos = platoService.findAllActive();
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Long id) {
        Plato plato = platoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        return ResponseEntity.ok(plato);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Plato>> getPlatosByCategoria(@PathVariable Long categoriaId) {
        List<Plato> platos = platoService.findByCategoriaId(categoriaId);
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/domicilio")
    public ResponseEntity<List<Plato>> getPlatosDomicilio() {
        List<Plato> platos = platoService.findAvailableForDomicilio();
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Plato>> searchPlatos(@RequestParam String nombre) {
        List<Plato> platos = platoService.searchByNombre(nombre);
        return ResponseEntity.ok(platos);
    }

}
