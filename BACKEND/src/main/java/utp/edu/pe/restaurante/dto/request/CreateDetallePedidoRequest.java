package utp.edu.pe.restaurante.dto.request;

import java.math.BigDecimal;


public class CreateDetallePedidoRequest {

    private Long platoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String notas;


    public CreateDetallePedidoRequest() {
    }

    public CreateDetallePedidoRequest(Long platoId, Integer cantidad, BigDecimal precioUnitario) {
        this.platoId = platoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    

    public Long getPlatoId() {
        return platoId;
    }

    public void setPlatoId(Long platoId) {
        this.platoId = platoId;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "CreateDetallePedidoRequest{" +
                "platoId=" + platoId +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
