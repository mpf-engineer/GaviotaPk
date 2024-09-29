package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonoAdapter extends JpaRepository<AbonoRelacional, UUID> {
    public Optional<AbonoRelacional> findById(UUID id);
}
