package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;

public class Impresora{
    
    TicketService ticketService;

    @Autowired
    public Impresora(TicketService ticketService){
        this.ticketService = ticketService;
    }
    
    Ticket imprimeTicket(Vehiculo coche){
        //Creamos un ticket asociado al vehículo
        Ticket t = new Ticket(coche);
        

        //Y lo añadimos a la base de datos
        ticketService.addTicket(t);


        return t;
    }
}