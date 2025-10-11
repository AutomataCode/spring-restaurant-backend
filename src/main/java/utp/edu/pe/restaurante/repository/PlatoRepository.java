package utp.edu.pe.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utp.edu.pe.restaurante.entity.Plato;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {
	
	 
    List<Plato> findByActivoTrue();
    
    
    List<Plato> findByCategoriaIdAndActivoTrue(Long categoriaId);
    
    
    List<Plato> findByDisponibleDomicilioTrueAndActivoTrue();
    

    @Query("SELECT p FROM Plato p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND p.activo = true")
    List<Plato> buscarPorNombre(@Param("nombre") String nombre);
    
   
    Optional<Plato> findByNombreIgnoreCase(String nombre);
    
  
    @Query("SELECT COUNT(p) FROM Plato p WHERE p.categoria.id = :categoriaId AND p.activo = true")
    Long countPlatosActivosByCategoriaId(@Param("categoriaId") Long categoriaId);
	

}
