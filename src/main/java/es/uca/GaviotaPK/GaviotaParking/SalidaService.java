package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class SalidaService {
    private final SalidaRepository salidaRepository;

    @Autowired
    public SalidaService(SalidaRepository salidaRepository) {
        this.salidaRepository = salidaRepository;
    }

    public List<Salida> getAllSalidas(){
        return salidaRepository.getAll();
    }

    public List<Salida> getSalidasByVehiculo(String matricula){
        return salidaRepository.getSalidasByVehiculo(matricula);
    }
    public List<Salida> getSalidasByFecha(LocalDate fecha){
        return salidaRepository.getSalidasByfecha(fecha);
    }
    public List<Salida> getSalidasByVehiculoAndFecha(String matricula, LocalDate fecha){
        return salidaRepository.getSalidasByVehiculoAndFecha(matricula, fecha);
    }

    public void addSalida(Salida salida){
        salidaRepository.add(salida);
    }
    public void update(Salida salida){
        salidaRepository.update(salida);
    }

}