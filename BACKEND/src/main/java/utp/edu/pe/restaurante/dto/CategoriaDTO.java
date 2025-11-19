package utp.edu.pe.restaurante.dto;

import utp.edu.pe.restaurante.entity.Categoria;

public class CategoriaDTO {
	
	 private Long id;
	    private String nombre;
	    private String descripcion;
		private Categoria.TipoCategoria tipo;
	    private Boolean activa;

	    // Constructores
	    public CategoriaDTO() {}

	    public CategoriaDTO(Long id, String nombre, String descripcion, 
	                       Categoria.TipoCategoria tipo, Boolean activa) {
	        this.id = id;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.tipo = tipo;
	        this.activa = activa;
	    }

	    // Getters y Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }

	    public String getDescripcion() { return descripcion; }
	    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	    public Categoria.TipoCategoria getTipo() { return tipo; }
	    public void setTipo(Categoria.TipoCategoria tipo) { this.tipo = tipo; }

	    public Boolean getActiva() { return activa; }
	    public void setActiva(Boolean activa) { this.activa = activa; }

}

