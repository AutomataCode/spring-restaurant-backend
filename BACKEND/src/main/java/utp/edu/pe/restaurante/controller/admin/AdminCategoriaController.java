package utp.edu.pe.restaurante.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.dto.CategoriaDTO;
import utp.edu.pe.restaurante.dto.request.CreateCategoriaRequest;
import utp.edu.pe.restaurante.dto.request.UpdateCategoriaRequest;
import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.mapper.CategoriaMapper;
import utp.edu.pe.restaurante.service.CategoriaService;
import utp.edu.pe.restaurante.service.PlatoService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categorias")
@CrossOrigin(origins = "*")
public class AdminCategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaMapper categoriaMapper;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> categoriasDTO = categoriaMapper.toDTOList(categorias);
		return ResponseEntity.ok(categoriasDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
		return ResponseEntity.ok(categoriaMapper.toDTO(categoria));
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CreateCategoriaRequest request) {
		Categoria categoria = categoriaMapper.toEntity(request);
		if (categoria.getActiva() == null) {
			categoria.setActiva(true);
		}
		Categoria savedCategoria = categoriaService.save(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaMapper.toDTO(savedCategoria));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
			@RequestBody UpdateCategoriaRequest request) {
		Categoria categoriaDetails = categoriaMapper.toEntity(request);
		Categoria updatedCategoria = categoriaService.update(id, categoriaDetails);
		return ResponseEntity.ok(categoriaMapper.toDTO(updatedCategoria));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> desactivarCategoria(@PathVariable Long id) {
		categoriaService.desactivarCategoriaYPlatos(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}/permanente")
	public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/reactivar")
	public ResponseEntity<CategoriaDTO> reactivarCategoria(@PathVariable Long id) {
		Categoria categoria = categoriaService.reactivarCategoria(id);
		return ResponseEntity.ok(categoriaMapper.toDTO(categoria));
	}

}