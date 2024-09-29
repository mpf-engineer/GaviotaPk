package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends BaseRepository<Vehiculo> {
    public List<Vehiculo> getAll();
    public void add(Vehiculo v);
    public Vehiculo getVehiculoByMatricula(String matricula);
    public void updateTicket(Vehiculo v, Ticket t);
    public void updateAbono(Vehiculo v, Abono a);
    public void updateConductor(Vehiculo v, Cliente c);
    public void updateEnParking(Vehiculo v);


}