
package es.uca.GaviotaPK.GaviotaParking;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


//@Repository
public class GeneradorInfTXT implements GeneradorInf {

    private EntradaService entradaService;
    private SalidaService salidaService;
    private PagoService pagoService;

    public GeneradorInfTXT(EntradaService entradaService, SalidaService salidaService,PagoService pagoService) {
        this.entradaService = entradaService;
        this.salidaService = salidaService;
        this.pagoService = pagoService;
    }

    public List<Movimiento> generarInformeES(LocalDate fecha, String matricula) {
    List<Entrada> ent = entradaService.getAllEntradas();
    List<Salida> sal = salidaService.getAllSalidas();

    List<Movimiento> movs = new ArrayList<Movimiento>();
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("informe.txt"))) {
        for (Entrada entrada : ent) {
            if ((matricula == null || entrada.getMatricula().equals(matricula)) &&
                (fecha == null || entrada.getFecha().getDayOfYear() == fecha.getDayOfYear())) {
                writer.write("ENTRADA" + " " + entrada.getMatricula() + " " + entrada.getFecha() + "\n");
                movs.add(new Movimiento(entrada.getMatricula(), entrada.getFecha(), "ENTRADA"));
            }
        }

        for (Salida salida : sal) {
            if ((matricula == null || salida.getMatricula().equals(matricula)) &&
                (fecha == null || salida.getFecha().getDayOfYear() == salida.getFecha().getDayOfYear())) {
                writer.write("SALIDA" + " " + salida.getMatricula() + " " + salida.getFecha() + "\n");
                movs.add(new Movimiento(salida.getMatricula(), salida.getFecha(), "SALIDA"));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return movs;
}


    //Hay que hacer bien este método o borrarlo 
    public List<Pago> generarInformeIngresos(LocalDate fechaIni, LocalDate fechaFin) {
    
        List<Pago> pagos = pagoService.getAllPagos();
        BigDecimal ingresosPagos = BigDecimal.ZERO;
        int nPagos = 0;

        List<Pago> res = new ArrayList<Pago>();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Informe_Ingresos.txt"))) {
            writer.write("CLIENTE\t\t\tCANTIDAD\t\t\tFECHA\n");
            for(Pago pago: pagos){
                if (fechaEnRango(pago.getFecha().toLocalDate(), fechaIni, fechaFin)) {
                    writer.write(pago.getCliente().getNombre() + "\t\t\t" + pago.getValor() + "\t\t\t"+ pago.getFecha()+ "\n");
                    nPagos++;
                    ingresosPagos = ingresosPagos.add(pago.getValor());
                    res.add(pago);
                    
                }
            }
            writer.write("Se han realizado " + nPagos + "y se ha ingresado un total de" + ingresosPagos + "euros\n"); 

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    private boolean fechaEnRango(LocalDate f, LocalDate fechaIni, LocalDate fechaFin){
        if(fechaIni == null && fechaFin == null) return true;
        else if(fechaIni == null) return f.isBefore(fechaFin) || f.isEqual(fechaFin);
        else if(fechaFin == null) return f.isAfter(fechaIni) || f.isEqual(fechaIni);
        else return f.isEqual(fechaIni) || f.isEqual(fechaFin) || (f.isAfter(fechaIni) && f.isBefore(fechaFin));
    }
}
