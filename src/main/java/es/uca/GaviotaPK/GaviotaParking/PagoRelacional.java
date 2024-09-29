package es.uca.GaviotaPK.GaviotaParking;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Pagos")
public class PagoRelacional {

    @Id
    @Column(name = "id_Pago")
    private UUID idPago;

    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @ManyToOne
    private ClienteRelacional cliente;

    public PagoRelacional(){}
    // Constructor de la clase Pago dado el valor del pago y el cliente que lo realiza
    public PagoRelacional(BigDecimal valor, ClienteRelacional c) {
        this.idPago = UUID.randomUUID();
        this.valor = valor;
        this.cliente = c;
        this.fecha = LocalDateTime.now();
    }

    // Constructor de la clase Pago dado el valor del pago, el cliente que lo realiza y el vehículo asociado
    public PagoRelacional(AbonoRelacional a, ClienteRelacional c, VehiculoRelacional v) {
        try {
            if (v.getAbono() == null || v.getAbono().getFechaCaducidad().isBefore(LocalDateTime.now()) || c.getVehiculo() != v) {
                //throw new Exception();
            } else {
                this.idPago = UUID.randomUUID();
                this.valor = a.getPrecio();
                this.cliente = c;
                this.fecha = LocalDateTime.now();
            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }
        
    }
    // Constructor de la clase Pago dado la valor del pago, el cliente que lo realiza y el ticket asociado
    public PagoRelacional(Ticket ti, ClienteRelacional c, Tarifa ta) {
        try {
            if (ti.estaPagado() == false && ti.estaActivo() == true) {
                
                this.cliente = c;
                if(ti.getDias(LocalDateTime.now()) > 0){
                    this.valor = ta.calcularPrecioDia(ti.getDias(LocalDateTime.now()));
                }
                else{
                    this.valor = ta.calcularPrecioHora(ti.getFechaEntrada(), LocalDateTime.now());
                }

                this.idPago = UUID.randomUUID();
                this.fecha = LocalDateTime.now();
                ti.setFechaPago(fecha);
                ti.setPagado();
        
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }
    }

    // Método observador para obtener el identificador del pago
    public UUID getId() {
        return idPago;
    }

    public void setId(UUID id) {
        this.idPago = id;
    }

    // Método observador para obtener el valor del pago
    public BigDecimal getValor() {
        return valor;
    }

    // Método observador para obtener la fecha del pago
    public LocalDateTime getFecha() {
        return fecha;
    }
    // Método observador para obtener el cliente que realiza el pago
    public ClienteRelacional getCliente(){
        return cliente;
    }

    public void setCliente(ClienteRelacional cli) {
        this.cliente = cli;
    }

    public Pago toPago(){
        Pago p = new Pago(valor, null);
        p.setId(idPago);
        return p;
    }

}   
