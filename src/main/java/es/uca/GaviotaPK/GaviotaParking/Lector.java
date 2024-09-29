package es.uca.GaviotaPK.GaviotaParking;
/*
Lector tiene la función de leer el ticket cuando el coche llega a la barrera para irse. Comprueba que no hayan
pasado 15 minutos o más desde que se realizó el pago del ticket. El pago del ticket se haría en ventanilla,
clase que aún hay que crear
*/

import java.time.LocalDateTime;
import java.time.Duration;

public class Lector{
    public boolean leerTicket(Vehiculo v){
        Ticket t = v.getTicket();
        boolean vehiculoCorrecto/*,fechaSinRetardo*/;

        vehiculoCorrecto = v.getMatricula() == t.getVehiculo().getMatricula();
        
        boolean fechaSinRetardo;
        Duration duracion = Duration.between(t.getFechaPago(), LocalDateTime.now());
        long minutosDif = duracion.toMinutes();
        fechaSinRetardo = (long) 15 > minutosDif;
        
        return vehiculoCorrecto && t.estaPagado() && fechaSinRetardo;
    }


}