package utp.edu.pe.restaurante.controller.Public;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.CategoriaDTO;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.mapper.CategoriaMapper;
import utp.edu.pe.restaurante.service.CategoriaService;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaMapper categoriaMapper;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> getAllCategoriasActivas() {
		List<Categoria> categorias = categoriaService.findAllActive();
		List<CategoriaDTO> categoriasDTO = categoriaMapper.toDTOList(categorias);
		return ResponseEntity.ok(categoriasDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
		return ResponseEntity.ok(categoriaMapper.toDTO(categoria));
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CategoriaDTO>> getCategoriasByTipo(@PathVariable String tipo) {
		Categoria.TipoCategoria tipoEnum = Categoria.TipoCategoria.valueOf(tipo.toUpperCase());
		List<Categoria> categorias = categoriaService.findByTipo(tipoEnum);
		List<CategoriaDTO> categoriasDTO = categoriaMapper.toDTOList(categorias);
		return ResponseEntity.ok(categoriasDTO);
	}

	@GetMapping("/con-platos")
	public ResponseEntity<List<CategoriaDTO>> getCategoriasConPlatos() {
		List<Categoria> categorias = categoriaService.findCategoriesWithActivePlatos();
		List<CategoriaDTO> categoriasDTO = categoriaMapper.toDTOList(categorias);
		return ResponseEntity.ok(categoriasDTO);
	}

}
