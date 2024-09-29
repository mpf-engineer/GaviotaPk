package es.uca.GaviotaPK.GaviotaParking;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDGeneradorInf implements GeneradorInf {

    private EntradaService entradaService;
    private SalidaService salidaService;
    private PagoService pagoService;

    public BBDDGeneradorInf(EntradaService entradaService, SalidaService salidaService,PagoService pagoService) {
        this.entradaService = entradaService;
        this.salidaService = salidaService;
        this.pagoService = pagoService;
    }

    public List<Movimiento> generarInformeES(LocalDate fecha, String matricula) {

    List<Movimiento> movimientos = new ArrayList<Movimiento>();
    
    if(fecha == null && matricula == null) {
        return movimientos;
    }

    else if(fecha == null && matricula != null){
        List<Entrada> ent = entradaService.getEntradasByVehiculo(matricula);
        for(Entrada entrada: ent){
            movimientos.add(new Movimiento(entrada.getMatricula(), entrada.getFecha(), "ENTRADA"));
        }
        List<Salida> sal = salidaService.getSalidasByVehiculo(matricula);
        for(Salida salida: sal){
            movimientos.add(new Movimiento(salida.getMatricula(), salida.getFecha(), "SALIDA"));
        }   
    }

    else if(fecha != null && matricula == null){
        List<Entrada> ent = entradaService.getEntradasByFecha(fecha);
        for(Entrada entrada: ent){
            movimientos.add(new Movimiento(entrada.getMatricula(), entrada.getFecha(), "ENTRADA"));
        }
        List<Salida> sal = salidaService.getSalidasByFecha(fecha);

        for(Salida salida: sal){
            movimientos.add(new Movimiento(salida.getMatricula(), salida.getFecha(), "SALIDA"));
        }   
    }

    else{
        List<Entrada> ent = entradaService.getEntradasByVehiculoAndFecha(matricula, fecha);
        for(Entrada entrada: ent){
            movimientos.add(new Movimiento(entrada.getMatricula(), entrada.getFecha(), "ENTRADA"));
        }
        List<Salida> sal = salidaService.getSalidasByVehiculoAndFecha(matricula, fecha);
        for(Salida salida: sal){
            movimientos.add(new Movimiento(salida.getMatricula(), salida.getFecha(), "SALIDA"));
        }   
    }

    return movimientos;
}

    public List<Pago> generarInformeIngresos(LocalDate fechaIni, LocalDate fechaFin) {
    
        List<Pago> pagos = pagoService.getAllPagos();
        List<Pago> pagosRango = new ArrayList<Pago>();
        for(Pago pago: pagos){
            if (fechaEnRango(pago.getFecha().toLocalDate(), fechaIni, fechaFin)) {
                pagosRango.add(pago);
            }
        }
        return pagosRango;   

    }
    private boolean fechaEnRango(LocalDate f, LocalDate fechaIni, LocalDate fechaFin){
        if(fechaIni == null){
            return f.isEqual(fechaFin) || f.isBefore(fechaFin);
        }
        else if(fechaFin == null){
            return f.isAfter(fechaIni) || f.isEqual(fechaIni);
        }
        else return f.isEqual(fechaIni) || f.isEqual(fechaFin) || (f.isAfter(fechaIni) && f.isBefore(fechaFin));
    }
}
