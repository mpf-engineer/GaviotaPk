package es.uca.GaviotaPK.GaviotaParking;

public class BarreraSalida implements Barrera{
    public void abrirBarrera(){
        System.out.println("Simulando señal a barrera física...");
        
        System.out.println("Barrera abierta. ¡Gracias por su estancia!");
    }
    public void cerrarBarrera(){
        System.out.println("Barrera cerrada");
    }
}