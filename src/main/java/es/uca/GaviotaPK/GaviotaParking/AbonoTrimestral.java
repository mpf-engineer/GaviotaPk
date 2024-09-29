package es.uca.GaviotaPK.GaviotaParking;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AbonoTrimestral extends Abono {

    // Constructor de la clase AbonoTrimestral dado un precio y un vehículo
    public AbonoTrimestral(BigDecimal precio, Vehiculo coche) {
        this.idAbono = UUID.randomUUID();
        this.fechaCreacion = LocalDateTime.now();
        this.precio = precio;
        this.vehiculo = coche;
        this.fechaCaducidad = calcularFechaCaducidad();
        coche.setAbonoActual(this);
    }

    // Método para calcular la fecha de caducidad de un abono trimestral
    @Override
    protected LocalDateTime calcularFechaCaducidad() {
        return LocalDateTime.now().plusMonths(3);
    }


}
