package utp.edu.pe.restaurante.service;

import java.util.List;
import java.util.Optional;

import utp.edu.pe.restaurante.entity.Categoria;

public interface CategoriaService {
	
	 	List<Categoria> findAll();
	    Optional<Categoria> findById(Long id);
	    Categoria save(Categoria categoria);
	    Categoria update(Long id, Categoria categoria);
	  
	    
	    
	    
	    void desactivarCategoriaYPlatos(Long id);
	    void deleteCategoria(Long id);
	   
	    Categoria reactivarCategoria(Long id);
	    
	  
	    List<Categoria> findAllActive();
	    List<Categoria> findByTipo(Categoria.TipoCategoria tipo);
	    List<Categoria> findCategoriesWithActivePlatos();
	    boolean existsByNombre(String nombre);
	    void validateCategoriaHasNoPlatos(Long categoriaId);

}