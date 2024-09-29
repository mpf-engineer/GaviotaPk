package es.uca.GaviotaPK.GaviotaParking;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoAdapter extends JpaRepository<VehiculoRelacional, String> {
    public Optional<VehiculoRelacional> findBymatricula(String matricula);
}
