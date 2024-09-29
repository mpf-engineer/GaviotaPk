package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;

public class TarifaDTO {
    private String nombre;

    private BigDecimal costePorHora; 

    private BigDecimal costePorDia; 

    private BigDecimal costeAbonoMensual;

    private BigDecimal costeAbonoTrimestral;

    private BigDecimal costeAbonoAnual;
    
    public String getNombre() {
        return nombre;
    }   

    public BigDecimal getCostePorHora() {
        return costePorHora;
    }

    public BigDecimal getCostePorDia() {
        return costePorDia;
    }

    public BigDecimal getCosteAbonoMensual() {
        return costeAbonoMensual;
    }

    public BigDecimal getCosteAbonoTrimestral() {
        return costeAbonoTrimestral;
    }

    public BigDecimal getCosteAbonoAnual() {
        return costeAbonoAnual;
    }
}
