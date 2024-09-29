package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("trimestral")
public class AbonoTrimestralRelacional extends AbonoRelacional {
    // Constructor de la clase AbonoTrimestral dado un precio y un vehículo
    public AbonoTrimestralRelacional(BigDecimal precio, VehiculoRelacional coche) {
        this.idAbono = UUID.randomUUID();
        this.fechaCreacion = LocalDateTime.now();
        this.precio = precio;
        this.vehiculo = coche;
        this.fechaCaducidad = calcularFechaCaducidad();
    }

    public AbonoTrimestralRelacional() {}

    // Método para calcular la fecha de caducidad de un abono trimestral
    @Override
    protected LocalDateTime calcularFechaCaducidad() {
        return LocalDateTime.now().plusMonths(3);
    }
}
