package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


//@Repository
public class MemorySalidaRepository implements SalidaRepository {
    private List<Salida> salidas;

    public MemorySalidaRepository(){
        salidas = new ArrayList<>();
    }

    public List<Salida> getAll(){
        return salidas;
    }

    public void add(Salida salida){
        salidas.add(salida);    
    }

    public void update(Salida salida){
        // Para trabajar en memoria no es necesario implementar este método
    }

    public void delete(Salida salida){
        salidas.remove(salida);
    }

    public List<Salida> getSalidasByVehiculo(String matricula){
        List<Salida> res = new ArrayList<>();
        for(Salida salida: salidas){
            if(salida.getMatricula().equals(matricula)){
                res.add(salida);
            }
        }
        return res;
    }

    public List<Salida> getSalidasByfecha(LocalDate fecha){
        List<Salida> res = new ArrayList<>();
        for(Salida salida: salidas){
            if(salida.getFecha().isBefore(fecha.atTime(23,59,59))){
                res.add(salida);
            }
        }
        return res;
    }

    public List<Salida> getSalidasByVehiculoAndFecha(String matricula, LocalDate fecha){
        List<Salida> res = new ArrayList<>();
        for(Salida salida: salidas){
            if(salida.getMatricula().equals(matricula) && salida.getFecha().isBefore(fecha.atTime(23,59,59))){
                res.add(salida);
            }
        }
        return res;
    }
    
    

    
}
