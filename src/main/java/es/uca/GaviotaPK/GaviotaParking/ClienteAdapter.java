package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteAdapter extends JpaRepository<ClienteRelacional, UUID> {
    public Optional<ClienteRelacional> findById(UUID id);
    public Optional<ClienteRelacional> findByNombre(String nombre);
}
