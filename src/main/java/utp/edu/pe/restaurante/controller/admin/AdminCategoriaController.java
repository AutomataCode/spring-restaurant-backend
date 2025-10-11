package utp.edu.pe.restaurante.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.CategoriaDTO;
import utp.edu.pe.restaurante.dto.request.CreateCategoriaRequest;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.service.CategoriaService;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminCategoriaController {
	
	  @Autowired
	    private CategoriaService categoriaService;
	  
	  @Autowired
	    private PlatoService platoService;

	    @GetMapping
	    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
	        List<Categoria> categorias = categoriaService.findAll();
	        List<CategoriaDTO> categoriasDTO = categorias.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(categoriasDTO);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long id) {
	        Categoria categoria = categoriaService.findById(id)
	                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	        return ResponseEntity.ok(convertToDTO(categoria));
	    }

	    @PostMapping
	    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CreateCategoriaRequest request) {
	        Categoria categoria = new Categoria();
	        categoria.setNombre(request.getNombre());
	        categoria.setDescripcion(request.getDescripcion());
	        categoria.setTipo(request.getTipo());
	        categoria.setActiva(request.getActiva() != null ? request.getActiva() : true);

	        Categoria savedCategoria = categoriaService.save(categoria);
	        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedCategoria));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id, 
	                                                       @RequestBody CreateCategoriaRequest request) {
	        Categoria categoriaDetails = new Categoria();
	        categoriaDetails.setNombre(request.getNombre());
	        categoriaDetails.setDescripcion(request.getDescripcion());
	        categoriaDetails.setTipo(request.getTipo());
	        categoriaDetails.setActiva(request.getActiva());

	        Categoria updatedCategoria = categoriaService.update(id, categoriaDetails);
	        return ResponseEntity.ok(convertToDTO(updatedCategoria));
	    }



	    
	    /**
	     * "Eliminar" categoría (desactivar categoría y todos sus platos)
	     */
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> desactivarCategoria(@PathVariable Long id) {
	        categoriaService.desactivarCategoriaYPlatos(id);
	        return ResponseEntity.noContent().build();
	    }

	    /**
	     * Reactivar solo la categoría (los platos quedan inactivos para revisión)
	     */
	    @PatchMapping("/{id}/reactivar")
	    public ResponseEntity<CategoriaDTO> reactivarCategoria(@PathVariable Long id) {
	        Categoria categoria = categoriaService.reactivarCategoria(id);
	        return ResponseEntity.ok(convertToDTO(categoria));
	    }



	    private CategoriaDTO convertToDTO(Categoria categoria) {
	        return new CategoriaDTO(
	                categoria.getId(),
	                categoria.getNombre(),
	                categoria.getDescripcion(),
	                categoria.getTipo(),
	                categoria.getActiva()
	        );
	    }

}
