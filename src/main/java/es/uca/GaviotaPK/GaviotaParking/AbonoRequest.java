package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;

public class AbonoRequest {
   
    private String matricula;
    private BigDecimal precio;

    // Constructor de la clase AbonoRequest
    public AbonoRequest(String matricula, BigDecimal precio) {
        this.matricula = matricula;
        this.precio = precio;
    }
    // Getters y setters
    public String getMatricula() {
        return matricula;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
}
