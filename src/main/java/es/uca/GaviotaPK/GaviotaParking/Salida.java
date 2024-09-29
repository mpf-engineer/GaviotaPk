package es.uca.GaviotaPK.GaviotaParking;
import java.time.LocalDateTime;
import java.util.UUID;

public class Salida {
   
    private UUID idSalida;
    private LocalDateTime fechaSalida;
      
    private Vehiculo vehiculo;

    public Salida(Vehiculo coche, LocalDateTime fechaSalida) {
        this.idSalida = UUID.randomUUID();
        this.fechaSalida = fechaSalida;
        this.vehiculo = coche;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getMatricula() {
        return vehiculo.getMatricula();
    }
    
    public LocalDateTime getFecha() {
        return fechaSalida;
    }

    public UUID getId() {
        return idSalida;
    }

    public void setId(UUID id) {
        this.idSalida = id;
    }   

    public SalidaRelacional toSalidaRelacional() {
        SalidaRelacional salida = new SalidaRelacional(null, fechaSalida);
        salida.setId(idSalida);
        return salida;
    }
}
