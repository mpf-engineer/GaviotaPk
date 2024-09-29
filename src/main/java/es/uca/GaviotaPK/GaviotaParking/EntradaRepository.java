package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends BaseRepository<Entrada> {

    public List<Entrada> getEntradasByVehiculo(String mat);
    public List<Entrada> getEntradasByfecha(LocalDate f);
    public List<Entrada> getEntradasByVehiculoAndFecha(String mat, LocalDate f);
}
