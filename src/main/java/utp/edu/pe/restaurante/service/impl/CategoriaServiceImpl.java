package utp.edu.pe.restaurante.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.exception.BusinessException;
import utp.edu.pe.restaurante.exception.ResourceNotFoundException;
import utp.edu.pe.restaurante.repository.CategoriaRepository;
import utp.edu.pe.restaurante.repository.PlatoRepository;
import utp.edu.pe.restaurante.service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
    private CategoriaRepository categoriaRepository;
	

    @Autowired
    private PlatoRepository platoRepository;

    private static final String CATEGORIA_NOT_FOUND = "Categoría no encontrada con id: ";

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        validateCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORIA_NOT_FOUND + id));

        validateCategoria(categoriaDetails);

        categoria.setNombre(categoriaDetails.getNombre());
        categoria.setDescripcion(categoriaDetails.getDescripcion());
        categoria.setTipo(categoriaDetails.getTipo());

        return categoriaRepository.save(categoria);
    }


    
    
    @Override
    @Transactional
    public void desactivarCategoriaYPlatos(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORIA_NOT_FOUND + id));
        
        // Desactivar todos los platos de esta categoría
        List<Plato> platos = platoRepository.findByCategoriaIdAndActivoTrue(id);
        for (Plato plato : platos) {
            plato.setActivo(false);
            platoRepository.save(plato);
        }
        
        // Desactivar la categoría
        categoria.setActiva(false);
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria reactivarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORIA_NOT_FOUND + id));
        
        // Solo reactivar la categoría, los platos quedan inactivos
        categoria.setActiva(true);
        return categoriaRepository.save(categoria);
        
       
    }
    
    

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAllActive() {
        return categoriaRepository.findByActivaTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findByTipo(Categoria.TipoCategoria tipo) {
        return categoriaRepository.findByTipoAndActivaTrue(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findCategoriesWithActivePlatos() {
        return categoriaRepository.findCategoriasConPlatosActivos();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.findByNombreIgnoreCase(nombre).isPresent();
    }

    @Override
    public void validateCategoriaHasNoPlatos(Long categoriaId) {
        Long count = platoRepository.countPlatosActivosByCategoriaId(categoriaId);
        if (count > 0) {
            throw new BusinessException("No se puede eliminar la categoría porque tiene platos asociados");
        }
    }

    private void validateCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre de la categoría es obligatorio");
        }
        
        Optional<Categoria> categoriaExistente = categoriaRepository.findByNombreIgnoreCase(categoria.getNombre());
        if (categoriaExistente.isPresent() && 
            (categoria.getId() == null || !categoriaExistente.get().getId().equals(categoria.getId()))) {
            throw new BusinessException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
    }

}

