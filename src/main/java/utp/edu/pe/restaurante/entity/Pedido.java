package utp.edu.pe.restaurante.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tipo_pedido", nullable = false)
    private String tipoPedido; // DOMICILIO, LOCAL

    @Column(name = "estado", nullable = false)
    private String estado; // PENDIENTE, EN_PREPARACION, EN_CAMINO, ENTREGADO, CANCELADO

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "direccion_entrega", columnDefinition = "TEXT")
    private String direccionEntrega;

    @Column(name = "instrucciones_entrega", columnDefinition = "TEXT")
    private String instruccionesEntrega;

    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago; // EFECTIVO, TARJETA, TRANSFERENCIA

    @Column(name = "cambio_para", precision = 10, scale = 2)
    private BigDecimal cambioPara;

    @Column(name = "fecha_confirmacion")
    private LocalDateTime fechaConfirmacion;

    @Column(name = "fecha_preparacion")
    private LocalDateTime fechaPreparacion;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {
        this.fechaPedido = LocalDateTime.now();
        this.estado = "PENDIENTE";
        this.total = BigDecimal.ZERO;
        this.activo = Boolean.TRUE;
    }

    public Pedido(Usuario usuario, String tipoPedido, String metodoPago) {
        this();
        this.usuario = usuario;
        this.tipoPedido = tipoPedido;
        this.metodoPago = metodoPago;
        this.activo = Boolean.TRUE;
    }

    public void calcularTotal() {
        this.total = BigDecimal.ZERO;
        for (DetallePedido detalle : this.detalles) {
            this.total = this.total.add(detalle.getSubtotal());
        }
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.detalles.add(detalle);
        detalle.setPedido(this);
        calcularTotal();
    }

    public void eliminarDetalle(DetallePedido detalle) {
        this.detalles.remove(detalle);
        detalle.setPedido(null);
        calcularTotal();
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;

        switch (nuevoEstado) {
            case "EN_PREPARACION":
                this.fechaPreparacion = LocalDateTime.now();
                break;
            case "EN_CAMINO":
                this.fechaEnvio = LocalDateTime.now();
                break;
            case "ENTREGADO":
                this.fechaEntrega = LocalDateTime.now();
                break;
            case "CANCELADO":
                break;
        }
    }

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

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getInstruccionesEntrega() {
        return instruccionesEntrega;
    }

    public void setInstruccionesEntrega(String instruccionesEntrega) {
        this.instruccionesEntrega = instruccionesEntrega;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getCambioPara() {
        return cambioPara;
    }

    public void setCambioPara(BigDecimal cambioPara) {
        this.cambioPara = cambioPara;
    }

    public LocalDateTime getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(LocalDateTime fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(LocalDateTime fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", total=" + total +
                '}';
    }
}
