package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository; 


public interface EntradaAdapter extends JpaRepository<EntradaRelacional, UUID>{
    
    public List<EntradaRelacional> findAll();
    public List<EntradaRelacional> findByfechaBetween(LocalDateTime fechaIni, LocalDateTime fechaFin);
    public List<EntradaRelacional> findByVehiculo(VehiculoRelacional v);
    public List<EntradaRelacional> findByVehiculoAndFechaBetween(VehiculoRelacional v, LocalDateTime fechaIni, LocalDateTime fechaFin);
}
