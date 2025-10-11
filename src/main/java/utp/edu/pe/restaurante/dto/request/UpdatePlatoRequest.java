package utp.edu.pe.restaurante.dto.request;

import java.math.BigDecimal;

public class UpdatePlatoRequest {
	
	  private String nombre;
	    private String descripcion;
	    private BigDecimal precio;
	    private Long categoriaId;
	    private String imagenUrl;
	    private Integer tiempoPreparacion;
	    private Boolean disponibleDomicilio;
	    private Boolean activo;

	    // Getters y Setters
	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }

	    public String getDescripcion() { return descripcion; }
	    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	    public BigDecimal getPrecio() { return precio; }
	    public void setPrecio(BigDecimal precio) { this.precio = precio; }

	    public Long getCategoriaId() { return categoriaId; }
	    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

	    public String getImagenUrl() { return imagenUrl; }
	    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

	    public Integer getTiempoPreparacion() { return tiempoPreparacion; }
	    public void setTiempoPreparacion(Integer tiempoPreparacion) { this.tiempoPreparacion = tiempoPreparacion; }

	    public Boolean getDisponibleDomicilio() { return disponibleDomicilio; }
	    public void setDisponibleDomicilio(Boolean disponibleDomicilio) { this.disponibleDomicilio = disponibleDomicilio; }

	    public Boolean getActivo() { return activo; }
	    public void setActivo(Boolean activo) { this.activo = activo; }

}
