package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

//@Repository
public class MemoryTicketRepository implements TicketRepository {
    private List<Ticket> tickets;

    public MemoryTicketRepository(){
        tickets = new ArrayList<Ticket>();
    }

    public List<Ticket> getAll(){
        return tickets;
    }

    public void add(Ticket ticket){
        tickets.add(ticket);   
    }

    public void delete(Ticket ticket){
        tickets.remove(ticket);
    }

    public void update(Ticket ticket){
        //No hace falta
    }

    public Ticket getTicketById(UUID id){
        for(Ticket ticket: tickets){
            if(ticket.getId() == id) return ticket;
        }
        throw new IllegalArgumentException();
    }

    public Ticket getTicketByCodigo(String codigo){
        for(Ticket ticket: tickets){
            if(ticket.getCodigo().equals(codigo)) return ticket;
        }
        throw new IllegalArgumentException();
    }
}
