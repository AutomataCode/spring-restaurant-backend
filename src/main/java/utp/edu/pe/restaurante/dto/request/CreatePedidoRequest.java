package utp.edu.pe.restaurante.dto.request;

import java.math.BigDecimal;
import java.util.List;

/**
 * Request DTO para crear un nuevo pedido
 * 
 * Contiene los datos necesarios para crear un pedido desde el cliente.
 */
public class CreatePedidoRequest {

    private Long usuarioId;
    private String tipoPedido;
    private String direccionEntrega;
    private String instruccionesEntrega;
    private String telefonoContacto;
    private String metodoPago;
    private BigDecimal cambioPara;
    private List<CreateDetallePedidoRequest> detalles;

    

    public CreatePedidoRequest() {
    }

    public CreatePedidoRequest(Long usuarioId, String tipoPedido, String metodoPago) {
        this.usuarioId = usuarioId;
        this.tipoPedido = tipoPedido;
        this.metodoPago = metodoPago;
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

    public List<CreateDetallePedidoRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CreateDetallePedidoRequest> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "CreatePedidoRequest{" +
                "usuarioId=" + usuarioId +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", detalles=" + (detalles != null ? detalles.size() : 0) + " items" +
                '}';
    }
}
