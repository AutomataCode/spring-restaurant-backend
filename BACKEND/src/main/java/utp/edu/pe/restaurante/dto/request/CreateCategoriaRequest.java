package utp.edu.pe.restaurante.dto.request;

import utp.edu.pe.restaurante.entity.Categoria;

public class CreateCategoriaRequest {
	
    private String nombre;
    private String descripcion;
	private Categoria.TipoCategoria tipo;
    private Boolean activa;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Categoria.TipoCategoria getTipo() { return tipo; }
    public void setTipo(Categoria.TipoCategoria tipo) { this.tipo = tipo; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }

}
