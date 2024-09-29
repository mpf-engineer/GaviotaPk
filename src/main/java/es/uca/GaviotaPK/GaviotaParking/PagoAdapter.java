package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoAdapter extends JpaRepository<PagoRelacional, UUID> {
    public Optional<PagoRelacional> findById(UUID id);
}
