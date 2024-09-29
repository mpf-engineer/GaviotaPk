package es.uca.GaviotaPK.GaviotaParking;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pago {
    private UUID idPago;
    private BigDecimal valor;
    private LocalDateTime fecha;
    private Cliente cliente;

    // Constructor de la clase Pago dado el valor del pago y el cliente que lo realiza
    public Pago(BigDecimal valor, Cliente c) {
        this.idPago = UUID.randomUUID();
        this.valor = valor;
        this.cliente = c;
        this.fecha = LocalDateTime.now();
    }

    // Constructor de la clase Pago dado el valor del pago, el cliente que lo realiza y el vehículo asociado
    public Pago(Abono a, Cliente c, Vehiculo v) {
        try {
            if (v.getAbono() == null || v.getAbono().getFechaCaducidad().isBefore(LocalDateTime.now()) /*|| c.getVehiculo() != v*/) {
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
    public Pago(Ticket ti, Cliente c, Tarifa ta) {
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

    public void setId(UUID id){
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
    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }

    public PagoRelacional toPagoRelacional(){
        PagoRelacional p = new PagoRelacional(valor, null);
        p.setId(idPago);
        return p;
    }
}   
