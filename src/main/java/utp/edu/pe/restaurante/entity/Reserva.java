package utp.edu.pe.restaurante.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad Reserva - Representa la reserva de un usuario para comer en el restaurante.
 * 
 * Reglas:
 * - Un usuario no puede tener reservas superpuestas.
 * - Duración máxima: 1 hora 30 minutos.
 * - Estado de la reserva: PENDIENTE, CONFIRMADA, CANCELADA, FINALIZADA.
 */
@Entity
@Table(name = "reservas",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"usuario_id", "fecha_inicio"})
       })
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario que hace la reserva
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "personas", nullable = false)
    private int personas;

    @Column(name = "comentario", length = 255)
    private String comentario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public enum Estado {
        PENDIENTE, CONFIRMADA, CANCELADA, FINALIZADA
    }

    // ==== CONSTRUCTORES ====

    public Reserva() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Reserva(Usuario usuario, LocalDateTime fechaInicio, int personas) {
        this();
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusMinutes(90); // Máximo 1h30
        this.personas = personas;
    }

    // ==== GETTERS & SETTERS ====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusMinutes(90);
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // ==== MÉTODOS AUXILIARES ====

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (fechaFin == null && fechaInicio != null) {
            fechaFin = fechaInicio.plusMinutes(90);
        }
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", personas=" + personas +
                '}';
    }
}
