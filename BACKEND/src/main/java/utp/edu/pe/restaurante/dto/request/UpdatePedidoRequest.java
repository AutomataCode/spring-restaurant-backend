package utp.edu.pe.restaurante.dto.request;

import java.math.BigDecimal;


public class UpdatePedidoRequest {

    private String estado;
    private String direccionEntrega;
    private String instruccionesEntrega;
    private String telefonoContacto;
    private String metodoPago;
    private BigDecimal cambioPara;

  

    public UpdatePedidoRequest() {
    }

    public UpdatePedidoRequest(String estado) {
        this.estado = estado;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @Override
    public String toString() {
        return "UpdatePedidoRequest{" +
                "estado='" + estado + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                '}';
    }
}
