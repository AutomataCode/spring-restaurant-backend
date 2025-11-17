package utp.edu.pe.restaurante.dto;

import java.math.BigDecimal;


public class DetallePedidoDTO {

    private Long id;
    private Long pedidoId;
    private Long platoId;
    private String platoNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String notas;

   

    public DetallePedidoDTO() {
    }

    public DetallePedidoDTO(Long id, Long pedidoId, Long platoId, String platoNombre,
            Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.platoId = platoId;
        this.platoNombre = platoNombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getPlatoId() {
        return platoId;
    }

    public void setPlatoId(Long platoId) {
        this.platoId = platoId;
    }

    public String getPlatoNombre() {
        return platoNombre;
    }

    public void setPlatoNombre(String platoNombre) {
        this.platoNombre = platoNombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "DetallePedidoDTO{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", platoId=" + platoId +
                ", platoNombre='" + platoNombre + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
