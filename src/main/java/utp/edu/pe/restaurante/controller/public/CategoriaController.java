package utp.edu.pe.restaurante.controller.Public;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.CategoriaDTO;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.service.CategoriaService;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	
		@Autowired
	    private CategoriaService categoriaService;
		



	    @GetMapping
	    public ResponseEntity<List<CategoriaDTO>> getAllCategoriasActivas() {
	        List<Categoria> categorias = categoriaService.findAllActive();
	        List<CategoriaDTO> categoriasDTO = categorias.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(categoriasDTO);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
	        Categoria categoria = categoriaService.findById(id)
	                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	        return ResponseEntity.ok(convertToDTO(categoria));
	    }

	    @GetMapping("/tipo/{tipo}")
	    public ResponseEntity<List<CategoriaDTO>> getCategoriasByTipo(@PathVariable String tipo) {
	        Categoria.TipoCategoria tipoEnum = Categoria.TipoCategoria.valueOf(tipo.toUpperCase());
	        List<Categoria> categorias = categoriaService.findByTipo(tipoEnum);
	        List<CategoriaDTO> categoriasDTO = categorias.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(categoriasDTO);
	    }

	    @GetMapping("/con-platos")
	    public ResponseEntity<List<CategoriaDTO>> getCategoriasConPlatos() {
	        List<Categoria> categorias = categoriaService.findCategoriesWithActivePlatos();
	        List<CategoriaDTO> categoriasDTO = categorias.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(categoriasDTO);
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
