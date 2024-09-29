package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("mensual")
public class AbonoMensualRelacional extends AbonoRelacional {

    // Constructor de la clase AbonoMensual dado un precio y un vehículo
    public AbonoMensualRelacional(BigDecimal precio, VehiculoRelacional coche) {
        this.idAbono = UUID.randomUUID();
        this.fechaCreacion = LocalDateTime.now();
        this.precio = precio;
        this.vehiculo = coche;
        this.fechaCaducidad = calcularFechaCaducidad();
    }

    public AbonoMensualRelacional() {}

    
    // Método para calcular la fecha de caducidad de un abono mensual
    @Override
    protected LocalDateTime calcularFechaCaducidad() {
        return LocalDateTime.now().plusMonths(1);
    }

}
