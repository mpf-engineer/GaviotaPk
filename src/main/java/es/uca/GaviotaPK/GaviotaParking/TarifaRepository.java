package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends BaseRepository<Tarifa> {
    public Tarifa getTarifaById(UUID id);
    public Tarifa getTarifaByNombre(String nom);
}
