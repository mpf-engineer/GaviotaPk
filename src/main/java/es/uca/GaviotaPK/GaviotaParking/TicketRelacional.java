package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "tickets")
public class TicketRelacional {
    
    @Id
    @Column(name = "id_ticket")
    private UUID idTicket;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_entrada")
    private LocalDateTime fechaEntrada;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @OneToOne(mappedBy = "ticketActual")
    private VehiculoRelacional vehiculo;

    @Column(name = "pagado")
    private boolean pagado;

    @Column(name = "activo")
    private boolean activo;

    // Constructor de la clase Ticket dado un vehículo asociado
    public TicketRelacional(VehiculoRelacional v) {
        this.idTicket = UUID.randomUUID();
        this.codigo = generarCodigo();
        this.fechaEntrada = LocalDateTime.now();
        this.vehiculo = v;
        this.pagado = false;
        this.activo = true;
    }

    public TicketRelacional() {}
    
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
    public VehiculoRelacional getVehiculo() {
        return vehiculo;
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
    public void setPagado(boolean pagado2){
        this.pagado = pagado2;
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

    public void setActivo(boolean activo2) {
        activo = activo2;
    }

    public void setVehiculo(VehiculoRelacional vehiculo2) {
        vehiculo = vehiculo2;
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

    public Ticket toTicket() {
        Ticket t = new Ticket(vehiculo.toVehiculo());
        t.setId(idTicket);
        t.setCodigo(codigo);
        t.setFechaEntrada(fechaEntrada);
        t.setFechaPago(fechaPago);
        t.setVehiculo(vehiculo.toVehiculo());
        t.setPagado(pagado);
        t.setActivo(activo);
        return t;
    }
}
