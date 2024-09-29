package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;  


public interface TarifaAdapter extends JpaRepository<TarifaRelacional, UUID> {
    public Optional<TarifaRelacional> findById(UUID id);
    public Optional<TarifaRelacional> findByNombre(String nom);
}
