package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;




public interface SalidaAdapter extends JpaRepository<SalidaRelacional, UUID>{
    
    List<SalidaRelacional> findAll();
    List<SalidaRelacional> findByfechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<SalidaRelacional> findByvehiculo(VehiculoRelacional vehiculo);
    List<SalidaRelacional> findByVehiculoAndFechaBetween(VehiculoRelacional vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
