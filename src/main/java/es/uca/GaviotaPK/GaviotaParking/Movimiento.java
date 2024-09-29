package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;

public class Movimiento {
    private String matricula;
    private LocalDateTime fecha;
    private String tipo;

    public Movimiento(String matricula, LocalDateTime fecha, String tipo) {
        this.matricula = matricula;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
