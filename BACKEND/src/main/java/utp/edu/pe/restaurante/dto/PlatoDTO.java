package utp.edu.pe.restaurante.dto;

import java.math.BigDecimal;

public class PlatoDTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private Long categoriaId;
	private String categoriaNombre;
	private String imagenUrl;
	private Integer tiempoPreparacion;
	private Boolean disponibleDomicilio;
	private Boolean activo;

	public PlatoDTO() {
	}

	public PlatoDTO(Long id, String nombre, String descripcion, BigDecimal precio, Long categoriaId,
			String categoriaNombre, String imagenUrl, Integer tiempoPreparacion, Boolean disponibleDomicilio,
			Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoriaId = categoriaId;
		this.categoriaNombre = categoriaNombre;
		this.imagenUrl = imagenUrl;
		this.tiempoPreparacion = tiempoPreparacion;
		this.disponibleDomicilio = disponibleDomicilio;
		this.activo = activo;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Integer getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Integer tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public Boolean getDisponibleDomicilio() {
		return disponibleDomicilio;
	}

	public void setDisponibleDomicilio(Boolean disponibleDomicilio) {
		this.disponibleDomicilio = disponibleDomicilio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
