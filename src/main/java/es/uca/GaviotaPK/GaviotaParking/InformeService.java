package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;


@Service
public class InformeService {
    private final GeneradorInf generadorInf;

    @Autowired
    public InformeService(GeneradorInf generadorInf){
        this.generadorInf = generadorInf;
    }
    public List<Movimiento> generarInformeES(LocalDate fecha, String matricula){
        return generadorInf.generarInformeES(fecha, matricula);
    }
    public List<Pago> generarInformeIngresos(LocalDate fechaIni, LocalDate fechaFin){
        return generadorInf.generarInformeIngresos(fechaIni, fechaFin);
    }
}
