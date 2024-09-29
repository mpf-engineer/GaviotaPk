package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.temporal.ChronoUnit;


public class Ticket {
    
    private UUID idTicket;

    private String codigo;

    private LocalDateTime fechaEntrada;

    private LocalDateTime fechaPago;

    @JsonBackReference
    private Vehiculo vehiculo;

    private boolean pagado;

    private boolean activo;

    // Constructor de la clase Ticket dado un vehículo asociado
    public Ticket(Vehiculo v) {
        this.idTicket = UUID.randomUUID();
        this.codigo = generarCodigo();
        this.fechaEntrada = LocalDateTime.now();
        this.vehiculo = v;
        this.pagado = false;
        this.activo = true;
    }

    // Método observador para obtener el identificador de un ticket
    public UUID getId() {
        return idTicket;
    }

    // Método observador para obtener el código QR de un ticket
    public String getCodigo() {
        return codigo;
    }

    // Método observador para obtener la fecha de entrada de un ticket
    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    // Método observador para obtener la fecha de pago de un ticket
    public LocalDateTime getFechaPago(){
        return fechaPago;
    }

    // Método observador para obtener el número de días que lleva un coche en el parking
    public int getDias(LocalDateTime fechaActual) {
        return (int) ChronoUnit.DAYS.between(fechaEntrada.toLocalDate(), fechaActual.toLocalDate());
    }

    // Método observador para obtener la matrícula del vehículo asociado al ticket
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo v) {
        this.vehiculo = v;
    }

    // Método observador para comprobar si un ticket está pagado (true : pagado, false : no pagado)
    public boolean estaPagado(){
        return pagado;
    }

    // Método observador para comprobar si un ticket está activo (true : activo, false : no activo)
    public boolean estaActivo() {
        return activo;
    }
    

    // Método asignador para establecer que un ticket ha sido pagado
    public void setPagado(){
        this.pagado = true;
    }

    // Método asignador para establecer la fecha de pago de un ticket
    public void setFechaPago(LocalDateTime fecha){
        fechaPago = fecha;
    }

    // Método para desactivar un ticket
    public void desactivar() {
        this.activo = false;
    }

    // Método para generar un código QR asociado a un ticket
    private String generarCodigo() {
        return UUID.randomUUID().toString();
    }

    public void setId(UUID idTicket2) {
        idTicket = idTicket2;
    }

    public void setCodigo(String codigo2) {
        codigo = codigo2;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada2) {
        fechaEntrada = fechaEntrada2;
    }

    public void setPagado(boolean pagado2) {
        pagado = pagado2;
    }

    public void setActivo(boolean activo2) {
        activo = activo2;
    }
    

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", codigo='" + codigo + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaPago=" + fechaPago +
                ", matricula='" + vehiculo.getMatricula() + '\'' +
                ", pagado=" + pagado +
                ", activo=" + activo +
                '}';
    }

    public TicketRelacional toTicketRelacional() {
        TicketRelacional ticket = new TicketRelacional(vehiculo.toVehiculoRelacional());
        ticket.setId(idTicket);
        ticket.setCodigo(codigo);
        ticket.setFechaEntrada(fechaEntrada);
        ticket.setFechaPago(fechaPago);
        ticket.setPagado(pagado);
        ticket.setActivo(activo);
        return ticket;
    }

    
}
