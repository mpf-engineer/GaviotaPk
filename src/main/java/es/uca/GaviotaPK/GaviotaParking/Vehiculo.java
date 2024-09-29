package es.uca.GaviotaPK.GaviotaParking;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Vehiculo {

    private String matricula;

    @JsonManagedReference
    private Abono abonoActual;

    @JsonManagedReference
    private Ticket ticketActual;

    @JsonManagedReference
    private Cliente conductor;

    boolean enParking;
    
    // Constructor
    public Vehiculo(String matricula){
        this.matricula = matricula;
        this.abonoActual = null;
        this.ticketActual = null;
        this.enParking = false;
    }

    // Método observador para obtener la matrícula de un vehículo
    public String getMatricula(){
        return matricula;
    }

    // Método observador para obtener el abono actual asociado a un vehículo
    public Abono getAbono(){
        return abonoActual;
    }
    
    // Método observador para obtener el ticket actual asociado a un vehículo
    public Ticket getTicket(){
        return ticketActual;
    }

    // Método asignador para establecer el ticket actual asociado a un vehículo
    public void setTicket(Ticket t){
        this.ticketActual = t;
    }

    // Método asignador para establecer el abono actual asociado a un vehículo
    public void setAbonoActual(Abono a){
        this.abonoActual = a;
    }

    public Cliente getConductor(){
        return conductor;
    }

    public void setConductor(Cliente c){
        this.conductor = c;
    }

    // Método para comprobar si un vehículo tiene un abono activo
    public boolean estaAbonado(){
        return this.abonoActual != null && !(this.abonoActual.estaCaducado());
       
    }

    public boolean estaEnParking(){
        return enParking;
    }

    public void setEnParking(boolean b){
        enParking = b;
    }

    public VehiculoRelacional toVehiculoRelacional(){
        VehiculoRelacional v = new VehiculoRelacional(matricula);
        return v;
    }

}