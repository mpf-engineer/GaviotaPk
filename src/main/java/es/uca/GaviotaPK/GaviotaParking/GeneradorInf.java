package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.util.List;

public interface GeneradorInf {
    List<Movimiento> generarInformeES(LocalDate fecha, String matricula);
    List<Pago> generarInformeIngresos(LocalDate fechaIni, LocalDate fechaFin);
}
