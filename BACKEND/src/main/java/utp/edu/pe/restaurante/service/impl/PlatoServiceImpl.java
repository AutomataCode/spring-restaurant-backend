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
import utp.edu.pe.restaurante.service.PlatoService;


@Service
@Transactional
public class PlatoServiceImpl implements PlatoService {
	
	 	@Autowired
	    private PlatoRepository platoRepository;

	    @Autowired
	    private CategoriaRepository categoriaRepository;

	    private static final String PLATO_NOT_FOUND = "Plato no encontrado con id: ";

	    @Override
	    @Transactional(readOnly = true)
	    public List<Plato> findAll() {
	        return platoRepository.findAll();
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public Optional<Plato> findById(Long id) {
	        return platoRepository.findById(id);
	    }

	    @Override
	    public Plato save(Plato plato) {
	        validatePlato(plato);
	        if (plato.getCategoria() == null || plato.getCategoria().getId() == null) {
	            throw new BusinessException("La categoría es obligatoria");
	        }
	        // Verificar que la categoría existe
	        Categoria categoria = categoriaRepository.findById(plato.getCategoria().getId())
	                .orElseThrow(() -> new BusinessException("La categoría especificada no existe"));
	        
	        if (!categoria.getActiva()) {
	            throw new BusinessException("No se puede asignar una categoría inactiva");
	        }
	        
	        plato.setCategoria(categoria);
	        
	        return platoRepository.save(plato);
	    }

	    @Override
	    public Plato update(Long id, Plato platoDetails) {
	        Plato plato = platoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(PLATO_NOT_FOUND + id));

	        // IMPORTANTE: Asignar el ID al platoDetails antes de validar
	        // para que la validación de nombre único excluya este mismo plato
	        platoDetails.setId(id);
	        validatePlato(platoDetails);

	        // Verificar categoría si se está actualizando
	        if (platoDetails.getCategoria() != null && 
	            platoDetails.getCategoria().getId() != null) {
	            Categoria categoria = categoriaRepository.findById(platoDetails.getCategoria().getId())
	                    .orElseThrow(() -> new BusinessException("La categoría especificada no existe"));
	            plato.setCategoria(categoria);
	        }

        // Actualizar campos
        plato.setNombre(platoDetails.getNombre());
        plato.setDescripcion(platoDetails.getDescripcion());
        plato.setPrecio(platoDetails.getPrecio());
        
        // Solo actualizar imagenUrl si se proporciona un valor no nulo y no vacío
        // Esto preserva la imagen existente cuando no se envía una nueva imagen
        if (platoDetails.getImagenUrl() != null && !platoDetails.getImagenUrl().trim().isEmpty()) {
            plato.setImagenUrl(platoDetails.getImagenUrl());
        }
        
        plato.setTiempoPreparacion(platoDetails.getTiempoPreparacion());
        plato.setDisponibleDomicilio(platoDetails.getDisponibleDomicilio());
        
        // Actualizar estado activo si se proporciona
        if (platoDetails.getActivo() != null) {
            plato.setActivo(platoDetails.getActivo());
        }

        return platoRepository.save(plato);
	    }
	    @Override
	    @Transactional
	    public void desactivarPlato(Long id) {
	        Plato plato = platoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(PLATO_NOT_FOUND + id));
	        
	        plato.setActivo(false);
	        platoRepository.save(plato);
	    }

	    @Override
	    @Transactional
	    public void deletePlato(Long id) {
	        Plato plato = platoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(PLATO_NOT_FOUND + id));
	        
	        try {
	            platoRepository.delete(plato);
	        } catch (Exception e) {
	            // Si falla la eliminación (probablemente por restricciones de clave foránea)
	            throw new BusinessException(
	                "No se puede eliminar el plato porque está asociado a pedidos existentes. " +
	                "Puedes desactivarlo en su lugar."
	            );
	        }
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public List<Plato> findAllActive() {
	        return platoRepository.findByActivoTrue();
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public List<Plato> findByCategoriaId(Long categoriaId) {
	        return platoRepository.findByCategoriaIdAndActivoTrue(categoriaId);
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public List<Plato> findAvailableForDomicilio() {
	        return platoRepository.findByDisponibleDomicilioTrueAndActivoTrue();
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public List<Plato> searchByNombre(String nombre) {
	        if (nombre == null || nombre.trim().isEmpty()) {
	            return findAllActive();
	        }
	        return platoRepository.buscarPorNombre(nombre.trim());
	    }

	    @Override
	    public Plato changeStatus(Long id, boolean activo) {
	        Plato plato = platoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(PLATO_NOT_FOUND + id));
	        
	        plato.setActivo(activo);
	        return platoRepository.save(plato);
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public boolean existsByNombre(String nombre) {
	        return platoRepository.findByNombreIgnoreCase(nombre).isPresent();
	    }

	    @Override
	    public void validatePlato(Plato plato) {
	        if (plato.getNombre() == null || plato.getNombre().trim().isEmpty()) {
	            throw new BusinessException("El nombre del plato es obligatorio");
	        }
	        
	        if (plato.getPrecio() == null || plato.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0) {
	            throw new BusinessException("El precio debe ser mayor o igual a cero");
	        }
	 
	        
	        // Validar nombre único (excepto para el mismo plato en actualización)
	        Optional<Plato> platoExistente = platoRepository.findByNombreIgnoreCase(plato.getNombre());
	        if (platoExistente.isPresent() && 
	            (plato.getId() == null || !platoExistente.get().getId().equals(plato.getId()))) {
	            throw new BusinessException("Ya existe un plato con el nombre: " + plato.getNombre());
	        }
	    }

}
