package es.uca.GaviotaPK.GaviotaParking;
import java.time.LocalDateTime;
import java.util.UUID;



public class Entrada {

    private UUID idEntrada;

    private LocalDateTime fecha;
    
    private Vehiculo vehiculo;
    

    public Entrada(Vehiculo coche, LocalDateTime fecha) {
        this.idEntrada = UUID.randomUUID();
        this.vehiculo = coche;
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo v) {
        this.vehiculo = v;
    }

    public String getMatricula() {
        return vehiculo.getMatricula();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public UUID getId() {
        return idEntrada;
    }

    public void setId(UUID id) {
        this.idEntrada = id;
    }

    public EntradaRelacional toEntradaRelacional(){
        EntradaRelacional er = new EntradaRelacional(null, fecha);
        er.setId(idEntrada);
        return er;
    }
}