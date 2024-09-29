package es.uca.GaviotaPK.GaviotaParking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("anual")
public class AbonoAnualRelacional extends AbonoRelacional {

    public AbonoAnualRelacional() {}

    // Constructor de la clase AbonoAnual dado un precio y un vehículo
    public AbonoAnualRelacional(BigDecimal precio, VehiculoRelacional coche) {
        this.idAbono = UUID.randomUUID();
        this.fechaCreacion = LocalDateTime.now();
        this.precio = precio;
        this.vehiculo = coche;
        this.fechaCaducidad = calcularFechaCaducidad();
    }

    // Método para calcular la fecha de caducidad de un abono anual
    @Override
    protected LocalDateTime calcularFechaCaducidad() {
        return LocalDateTime.now().plusYears(1);
    }
}
