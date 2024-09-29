package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends BaseRepository<Ticket> {
    public Ticket getTicketById(UUID id);
    public Ticket getTicketByCodigo(String codigo);
}
