package utp.edu.pe.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utp.edu.pe.restaurante.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	 // Encontrar categorías activas
    List<Categoria> findByActivaTrue();
    
    // Encontrar categorías por tipo
    List<Categoria> findByTipoAndActivaTrue(Categoria.TipoCategoria tipo);
    
    // Verificar si existe categoría por nombre
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
    
    // Encontrar categorías con platos activos
    @Query("SELECT DISTINCT c FROM Categoria c JOIN c.platos p WHERE c.activa = true AND p.activo = true")
    List<Categoria> findCategoriasConPlatosActivos();
}
