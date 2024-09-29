package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente> {

    public Cliente getClientesById(UUID id);
    public Cliente getClienteByNombre(String nombre);
}
