package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

//Como se gestiona la herencia en las bases de datos?
public class Cliente {

    protected UUID id;

    protected String nif;

    protected String nombre;

    @JsonBackReference
    private Vehiculo vehiculo;

    public Cliente(String nif, String nombre, Vehiculo v){
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

    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    public void setVehiculo(Vehiculo v){
        this.vehiculo = v;
    }

    public void setId(UUID id){
        this.id = id;
    }   

    public ClienteRelacional toClienteRelacional(){
        ClienteRelacional cr = new ClienteRelacional(nif, nombre, null);
        cr.setId(id);
        return cr;
    }
}
