package utp.edu.pe.restaurante.dto.request;

import utp.edu.pe.restaurante.entity.Categoria;

public class UpdateCategoriaRequest {

    private String nombre;
    private String descripcion;
    private Categoria.TipoCategoria tipo;
    private Boolean activa;

    // Constructores
    public UpdateCategoriaRequest() {
    }

    public UpdateCategoriaRequest(String nombre, String descripcion,
            Categoria.TipoCategoria tipo, Boolean activa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.activa = activa;
    }

    // Getters y Setters
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

    public Categoria.TipoCategoria getTipo() {
        return tipo;
    }

    public void setTipo(Categoria.TipoCategoria tipo) {
        this.tipo = tipo;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "UpdateCategoriaRequest{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", activa=" + activa +
                '}';
    }
}
