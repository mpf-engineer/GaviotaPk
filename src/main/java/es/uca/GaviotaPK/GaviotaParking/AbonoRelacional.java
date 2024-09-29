package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_abono")
@Table(name = "abonos")
public abstract class AbonoRelacional {

    @Id
    @Column(name = "id_abono")
    protected UUID idAbono;
    
    @Column(name = "precio")
    protected BigDecimal precio; 

    @Column(name = "fecha_caducidad")
    protected LocalDateTime fechaCaducidad;

    @Column(name = "fecha_creacion")
    protected LocalDateTime fechaCreacion;

    @OneToOne(mappedBy = "abonoActual")
    protected VehiculoRelacional vehiculo;

    
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
    public VehiculoRelacional getVehiculo(){
        return this.vehiculo;
    }

    public void setVehiculo(VehiculoRelacional v){
        this.vehiculo = v;
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

    // Método asignador para establecer la fecha de creación de un abono
    public void setIdAbono(UUID id){
        this.idAbono = id;
    }

    // Método abstracto para calcular la fecha de caducidad
    protected abstract LocalDateTime calcularFechaCaducidad();


    // Método para convertir un abono relacional en un abono
    public Abono toAbono(){
        Vehiculo v = vehiculo.toVehiculo();

        if(this instanceof AbonoMensualRelacional){
            return new AbonoMensual(this.precio, v);

        }else if (this instanceof AbonoTrimestralRelacional){
            return new AbonoTrimestral(this.precio, v);
            
        }else{
            return new AbonoAnual(precio, v);
        } 
            
    }
}
