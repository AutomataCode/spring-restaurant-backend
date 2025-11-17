package utp.edu.pe.restaurante.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) para Pedido
 * 
 * Se usa para transferir datos del pedido entre el cliente y el servidor.
 * No contiene lógica de negocio, solo datos.
 */
public class PedidoDTO {

    private Long id;
    private Long usuarioId;
    private String tipoPedido;
    private String estado;
    private LocalDateTime fechaPedido;
    private BigDecimal total;
    private String direccionEntrega;
    private String instruccionesEntrega;
    private String telefonoContacto;
    private String metodoPago;
    private BigDecimal cambioPara;
    private LocalDateTime fechaConfirmacion;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntrega;
    private List<DetallePedidoDTO> detalles;

    // ========== CONSTRUCTORES ==========

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, Long usuarioId, String tipoPedido, String estado,
            LocalDateTime fechaPedido, BigDecimal total) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipoPedido = tipoPedido;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
        this.total = total;
    }

    // ========== GETTERS Y SETTERS ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", total=" + total +
                '}';
    }
}
