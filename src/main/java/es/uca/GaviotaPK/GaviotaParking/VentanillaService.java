package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.stereotype.Service;

@Service
public class VentanillaService {
    private AbonoService abonoService;
    private PagoService pagoService;

    public VentanillaService(AbonoService a, PagoService p){
        this.abonoService = a;
        this.pagoService = p;
    }

    public void pagarAbonoMensual(Cliente c, Tarifa ta){
        try {
            if (!c.getVehiculo().getAbono().estaCaducado()) {
                throw new Exception();
            } else {
                AbonoMensual nuevoAbono = new AbonoMensual(ta.getCosteAbonoMensual(), c.getVehiculo());
                Pago nuevoPago = new Pago(nuevoAbono, c, c.getVehiculo());
                abonoService.addAbono(nuevoAbono);
                pagoService.addPago(nuevoPago);
            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }
    }

    public void pagarAbonoTrimestral(Cliente c, Tarifa ta){
        try {
            if (!c.getVehiculo().getAbono().estaCaducado()) {
                throw new Exception();
            } else {
                AbonoTrimestral nuevoAbono = new AbonoTrimestral(ta.getCosteAbonoTrimestral(), c.getVehiculo());
                Pago nuevoPago = new Pago(nuevoAbono, c, c.getVehiculo());
                abonoService.addAbono(nuevoAbono);
                pagoService.addPago(nuevoPago);
            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }
    }

    public void pagarAbonoAnual(Cliente c, Tarifa ta){
        try {
            if (!c.getVehiculo().getAbono().estaCaducado()) {
                throw new Exception();
            } else {
                AbonoAnual nuevoAbono = new AbonoAnual(ta.getCosteAbonoAnual(), c.getVehiculo());
                Pago nuevoPago = new Pago(nuevoAbono, c, c.getVehiculo());
                abonoService.addAbono(nuevoAbono);
                pagoService.addPago(nuevoPago);
            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }
    }

    
    public void pagarTicket(Ticket ti, Cliente c, Tarifa ta){
        try {
            if (ti.estaPagado() == false && ti.estaActivo() == true) {
                Pago nuevoPago = new Pago(ti, c, ta);
                pagoService.addPago(nuevoPago);

            }
        } catch (Exception e) {
            // Manejo de esta excepción
        }

    }


}
