package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDTicketRepository implements TicketRepository {
    private TicketAdapter ticketAdapter;

    public BBDDTicketRepository(TicketAdapter ca){
        this.ticketAdapter = ca;
    }

    public List<Ticket> getAll(){
        List<TicketRelacional> Tickets = ticketAdapter.findAll();
        List<Ticket> TicketsNormales = new ArrayList<Ticket>();
        for(TicketRelacional TicketR : Tickets){
            TicketsNormales.add(TicketR.toTicket());
        }
        return TicketsNormales;
    }

    public void add(Ticket e){
        //Para añadir el ticket hay que pasarlo a ticket relacional
        TicketRelacional tr = e.toTicketRelacional();
        //Tenemos que hacer este guardado ya o el save dará error
        tr.getVehiculo().setTicket(tr);
        ticketAdapter.save(tr);   
    }

    public void update(Ticket e){
        TicketRelacional tr = e.toTicketRelacional();
        VehiculoRelacional vr = e.getVehiculo().toVehiculoRelacional();
        if(e.getVehiculo().getConductor()!= null)vr.setConductor(e.getVehiculo().getConductor().toClienteRelacional());
        
        tr.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        tr.getVehiculo().setTicket(tr);
        ticketAdapter.save(tr);
    }

    public void delete(Ticket e){
        ticketAdapter.save(e.toTicketRelacional());
    }

    public Ticket getTicketById(UUID id){
        Optional<TicketRelacional> cli = ticketAdapter.findById(id);
        if(cli.isPresent()) return cli.get().toTicket();
        else return null;
    }
    
    public Ticket getTicketByCodigo(String codigo){
        Optional<TicketRelacional> cli = ticketAdapter.findByCodigo(codigo);
        if(cli.isPresent()) return cli.get().toTicket();
        else return null;
    } 
}
