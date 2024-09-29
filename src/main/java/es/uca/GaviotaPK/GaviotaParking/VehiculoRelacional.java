package es.uca.GaviotaPK.GaviotaParking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos")
public class VehiculoRelacional {

    @Id
    @Column(name = "matricula")
    private String matricula;
    
    @OneToOne
    private AbonoRelacional abonoActual;

    @OneToOne
    private TicketRelacional ticketActual;

    @OneToOne
    private ClienteRelacional conductor;

    @Column(name = "enParking")
    private boolean enParking;
    
    // Constructor
    public VehiculoRelacional(String matricula){
        this.matricula = matricula;
        this.abonoActual = null;
        this.ticketActual = null;
        this.enParking = false;
    }

    public VehiculoRelacional(){};

    // Método observador para obtener la matrícula de un vehículo
    public String getMatricula(){
        return matricula;
    }

    // Método observador para obtener el abono actual asociado a un vehículo
    public AbonoRelacional getAbono(){
        return abonoActual;
    }
    
    // Método observador para obtener el ticket actual asociado a un vehículo
    public TicketRelacional getTicket(){
        return ticketActual;
    }

    // Método asignador para establecer el ticket actual asociado a un vehículo
    public void setTicket(TicketRelacional t){
        this.ticketActual = t;
    }

    // Método asignador para establecer el abono actual asociado a un vehículo
    public void setAbonoActual(AbonoRelacional a){
        this.abonoActual = a;
    }

    public ClienteRelacional getConductor(){
        return conductor;
    }   

    public void setConductor(ClienteRelacional c){
        this.conductor = c;
    }
    // Método para comprobar si un vehículo tiene un abono activo
    public boolean estaAbonado(){
        return !(this.abonoActual != null && this.abonoActual.estaCaducado());
       
    }

    public boolean getEnParking(){
        return enParking;
    }

    public void setEnParking(boolean enParking){
        this.enParking = enParking;
    }

    public Vehiculo toVehiculo(){
        Vehiculo v = new Vehiculo(this.matricula);
        return v;
    }   

}