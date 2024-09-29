package es.uca.GaviotaPK.GaviotaParking;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


@Entity
@Table(name = "entradas") 
public class EntradaRelacional {
    
    @Id
    @Column(name = "id_entrada")
    private UUID idEntrada;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne
    private VehiculoRelacional vehiculo;
    
    
    public EntradaRelacional(VehiculoRelacional coche, LocalDateTime fecha) {
        this.idEntrada = UUID.randomUUID();
        this.vehiculo = coche;
        this.fecha = fecha;
    }

    public EntradaRelacional() {}

    public VehiculoRelacional getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoRelacional v) {
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
    
    public Entrada toEntrada(){
        Entrada e = new Entrada(null, fecha);
        e.setId(idEntrada);
        return e;
    }
}