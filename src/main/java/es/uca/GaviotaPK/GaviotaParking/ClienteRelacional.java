package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class ClienteRelacional {

    @Id
    @Column(name = "id_persona") 
    private UUID id;

    @Column(name = "nif", unique = true, nullable = false) 
    private String nif;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToOne(mappedBy = "conductor")
    private VehiculoRelacional vehiculo;

    public ClienteRelacional(){}

    public ClienteRelacional(String nif, String nombre, VehiculoRelacional v){
        this.id = UUID.randomUUID();
        this.nif = nif;
        this.nombre = nombre;
        this.vehiculo = v;
    }

    // Getters y setters
    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getId() {
        return id;
    }

    public VehiculoRelacional getVehiculo(){
        return vehiculo;
    }
    public void setVehiculo(VehiculoRelacional v){
        this.vehiculo = v;
    }

    public void setId(UUID id){
        this.id = id;
    }   

    public Cliente toCliente(){
        Cliente cli = new Cliente(nif, nombre, null);
        cli.setId(id);
        return cli;
    }
}
