package es.uca.GaviotaPK.GaviotaParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntradaService {
    private final EntradaRepository entradaRepository;

    @Autowired
    public EntradaService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public List<Entrada> getAllEntradas(){
        return entradaRepository.getAll();
    }

    public List<Entrada> getEntradasByVehiculo(String matricula){
        return entradaRepository.getEntradasByVehiculo(matricula);
    }
    public List<Entrada> getEntradasByFecha(LocalDate fecha){
        return entradaRepository.getEntradasByfecha(fecha);
    }
    public List<Entrada> getEntradasByVehiculoAndFecha(String matricula, LocalDate fecha){
        return entradaRepository.getEntradasByVehiculoAndFecha(matricula, fecha);
    }

    public void addEntrada(String matricula, LocalDateTime fecha){
        
        Vehiculo vehiculo = new Vehiculo(matricula);
        Entrada entrada = new Entrada(vehiculo, fecha);
        entradaRepository.add(entrada);
    }
    public void update(Entrada entrada){
        entradaRepository.update(entrada);
    }

}