package es.uca.GaviotaPK.GaviotaParking;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "salidas")
public class SalidaRelacional {

    @Id
    @Column(name = "id_salida")
    private UUID idSalida;

    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @ManyToOne
    private VehiculoRelacional vehiculo;

    public SalidaRelacional() {}

    public SalidaRelacional(VehiculoRelacional coche, LocalDateTime fechaSalida) {
        this.idSalida = UUID.randomUUID();
        this.fecha = fechaSalida;
        this.vehiculo = coche;
    }

    public VehiculoRelacional getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoRelacional vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getMatricula() {
        return vehiculo.getMatricula();
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public UUID getId() {
        return idSalida;
    }

    public void setId(UUID id) {
        this.idSalida = id;
    }

    public Salida toSalida() {
        Salida salida = new Salida(null, fecha);
        salida.setId(idSalida);
        return salida;
    }


}
