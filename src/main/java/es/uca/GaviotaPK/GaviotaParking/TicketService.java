package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAll();
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.add(ticket);
    }

    public void updateTicket(Ticket ticket) {
        ticketRepository.update(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    public Ticket getTicketById(UUID id){
        return ticketRepository.getTicketById(id);
    }

    public Ticket getTicketByCodigo(String codigo){
        return ticketRepository.getTicketByCodigo(codigo);
    }

}
