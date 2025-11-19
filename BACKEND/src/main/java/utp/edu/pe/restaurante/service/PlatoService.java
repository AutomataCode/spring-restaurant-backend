package utp.edu.pe.restaurante.service;

import java.util.List;
import java.util.Optional;

import utp.edu.pe.restaurante.entity.Plato;

public interface PlatoService {
	
	
    List<Plato> findAll();
    Optional<Plato> findById(Long id);
    Plato save(Plato plato);
    Plato update(Long id, Plato plato);
   
    void desactivarPlato(Long id);
    void deletePlato(Long id);
  
    List<Plato> findAllActive();
    List<Plato> findByCategoriaId(Long categoriaId);
    List<Plato> findAvailableForDomicilio();
    List<Plato> searchByNombre(String nombre);
    Plato changeStatus(Long id, boolean activo);
    boolean existsByNombre(String nombre);
    
    
    void validatePlato(Plato plato);

}
