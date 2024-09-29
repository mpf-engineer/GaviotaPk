package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;


public abstract class Abono {
    protected UUID idAbono;
    
    protected BigDecimal precio; 
    
    protected LocalDateTime fechaCaducidad;

    protected LocalDateTime fechaCreacion;

    @JsonBackReference
    protected Vehiculo vehiculo;

    // Método observador para obtener el identificador de un abono
    public UUID getId(){
        return this.idAbono;
    }

    // Método observador para obtener el precio de un abono
    public BigDecimal getPrecio(){
        return this.precio;
    }

    // Método observador para comprobar si un abono está caducado (true : caducado, false : no caducado)
    public boolean estaCaducado(){
        return LocalDateTime.now().isAfter(this.fechaCaducidad);
    }

    // Método observador para obtener el vehículo asociado a un abono
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }

    // Método observador para obtener la fecha de caducidad de un abono
    public LocalDateTime getFechaCaducidad(){
        return this.fechaCaducidad;
    }

    // Método observador para obtener la fecha de creación de un abono
    public LocalDateTime getFechaCreacion(){
        return this.fechaCreacion;
    }

    // Método asignador para establecer la fecha de caducidad de un abono
    public void setFechaCaducidad(LocalDateTime f){
        this.fechaCaducidad = f;
    }

    public void setVehiculo(Vehiculo v){
        this.vehiculo = v;
    }

    // Método abstracto para calcular la fecha de caducidad
    protected abstract LocalDateTime calcularFechaCaducidad();


    //Conversor de abono a abono relacional. El vehiculo de setearse a la salida de la función para no entrar en bucle
    public AbonoRelacional toAbonoRelacional(){
        if(this instanceof AbonoMensual){
            return new AbonoMensualRelacional(this.precio, null);

        }else if (this instanceof AbonoTrimestral){
            return new AbonoTrimestralRelacional(this.precio, null);

        }else if (this instanceof AbonoAnual){
            return new AbonoAnualRelacional(this.precio, null);
        }

        return null;
    }
}
