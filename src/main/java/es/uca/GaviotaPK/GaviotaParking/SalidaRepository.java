package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends BaseRepository<Salida> {
    public List<Salida> getSalidasByVehiculo(String mat);
    public List<Salida> getSalidasByfecha(LocalDate f);
    public List<Salida> getSalidasByVehiculoAndFecha(String mat, LocalDate f);
}
