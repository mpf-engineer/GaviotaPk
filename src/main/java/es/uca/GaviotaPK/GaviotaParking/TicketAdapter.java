package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketAdapter extends JpaRepository<TicketRelacional, UUID> {
    public Optional<TicketRelacional> findById(UUID id);
    public Optional<TicketRelacional> findByCodigo(String id);
}
