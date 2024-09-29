package es.uca.GaviotaPK.GaviotaParking;


public class BarreraEntrada implements Barrera{
    public void abrirBarrera(){
        System.out.println("Simulando señal a barrera física...");
        
        System.out.println("Barrera abierta. ¡Bienvenido!");
    }
    public void cerrarBarrera(){
        System.out.println("Barrera cerrada");
    }
} 