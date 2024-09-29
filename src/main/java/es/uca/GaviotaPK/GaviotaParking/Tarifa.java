package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;


public class Tarifa {
  
    private UUID idTarifa;

    private String nombre;

    private BigDecimal costePorHora; 

    private BigDecimal costePorDia; 

    private BigDecimal costeAbonoMensual;

    private BigDecimal costeAbonoTrimestral;

    private BigDecimal costeAbonoAnual;


    // Constructor de la clase Tarifa
    public Tarifa(String Nom, BigDecimal costePorHora, BigDecimal costePorDia, BigDecimal costeAbonoMensual, BigDecimal costeAbonoTrimestral, BigDecimal costeAbonoAnual) {
        this.idTarifa = UUID.randomUUID();
        this.nombre = Nom;
        this.costePorHora = costePorHora;
        this.costePorDia = costePorDia;
        this.costeAbonoMensual = costeAbonoMensual;
        this.costeAbonoTrimestral = costeAbonoTrimestral;
        this.costeAbonoAnual = costeAbonoAnual;
    }

    // Método observador para obtener el identificador de una tarifa
    public UUID getId() {
        return idTarifa;
    }
    
    public String getNombre() {
        return nombre;
    }   
    // Método observador para obtener el coste por hora de una tarifa
    public BigDecimal getCostePorHora() {
        return costePorHora;
    }

    // Método observador para obtener el coste por día de una tarifa
    public BigDecimal getCostePorDia() {
        return costePorDia;
    }

    // Método observador para obtener el coste de un abono mensual
    public BigDecimal getCosteAbonoMensual() {
        return costeAbonoMensual;
    }

    // Método observador para obtener el coste de un abono trimestral
    public BigDecimal getCosteAbonoTrimestral() {
        return costeAbonoTrimestral;
    }

    // Método observador para obtener el coste de un abono anual
    public BigDecimal getCosteAbonoAnual() {
        return costeAbonoAnual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }   

    // Método asignador para establecer el coste por hora de una tarifa
    public void setCostePorHora(BigDecimal costePorHora) {
        this.costePorHora = costePorHora;
    }

    // Método asignador para establecer el coste por día de una tarifa
    public void setCostePorDia(BigDecimal costePorDia) {
        this.costePorDia = costePorDia;
    }

    // Método asignador para establecer el coste de un abono mensual
    public void setCosteAbonoMensual(BigDecimal costeAbonoMensual) {
        this.costeAbonoMensual = costeAbonoMensual;
    }

    // Método asignador para establecer el coste de un abono trimestral
    public void setCosteAbonoTrimestral(BigDecimal costeAbonoTrimestral) {
        this.costeAbonoTrimestral = costeAbonoTrimestral;
    }

    // Método asignador para establecer el coste de un abono anual
    public void setCosteAbonoAnual(BigDecimal costeAbonoAnual) {
        this.costeAbonoAnual = costeAbonoAnual;
    }

    
    // Método para calcular el precio de un estacionamiento por horas
    public BigDecimal calcularPrecioHora(LocalDateTime horaEntrada, LocalDateTime horaSalida) {
        if (horaSalida.isBefore(horaEntrada)) {
            throw new IllegalArgumentException("La hora de salida no puede ser anterior a la hora de entrada");
        }

        Duration duracion = Duration.between(horaEntrada, horaSalida);
        long horas = duracion.toHours(); // Diferencia de tiempo en horas

        BigDecimal precioTotal = BigDecimal.ZERO;
        precioTotal = precioTotal.add(costePorHora.multiply(BigDecimal.valueOf(horas)));
        return precioTotal;
    }

    // Método para calcular el precio de un estacionamiento por días
    public BigDecimal calcularPrecioDia(int dias) {
        BigDecimal precioTotal = costePorDia.multiply(BigDecimal.valueOf(dias));
        return precioTotal; 
    }

    public TarifaRelacional toTarifaRelacional() {
        return new TarifaRelacional(this.nombre, this.costePorHora, this.costePorDia, this.costeAbonoMensual, this.costeAbonoTrimestral, this.costeAbonoAnual);
    }
}
