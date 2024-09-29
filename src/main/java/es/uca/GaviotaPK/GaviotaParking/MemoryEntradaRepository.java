package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

//@Repository
public class MemoryEntradaRepository implements EntradaRepository {
    private List<Entrada> entradas;

    public MemoryEntradaRepository(){
        entradas = new ArrayList<>();
    }

    public List<Entrada> getAll(){
        return entradas;
    }

    public void add(Entrada entrada){
        entradas.add(entrada);    
    }

    public void update(Entrada entrada){
        // No hace falta en memory
    }

    public void delete(Entrada entrada){
        entradas.remove(entrada);
    }

    public List<Entrada> getEntradasByVehiculo(String matricula){
        List<Entrada> res = new ArrayList<>();
        for(Entrada entrada: entradas){
            if(entrada.getMatricula().equals(matricula)){
                res.add(entrada);
            }
        }
        return res;
    }

    public List<Entrada> getEntradasByfecha(LocalDate fecha){
        List<Entrada> res = new ArrayList<>();
        for(Entrada entrada: entradas){
            if(entrada.getFecha().isBefore(fecha.atTime(LocalTime.MAX))){
                res.add(entrada);
            }
        }
        return res;
    }

    public List<Entrada> getEntradasByVehiculoAndFecha(String matricula, LocalDate fecha){
        List<Entrada> res = new ArrayList<>();
        for(Entrada entrada: entradas){
            if(entrada.getMatricula().equals(matricula) && entrada.getFecha().isBefore(fecha.atTime(LocalTime.MAX))){
                res.add(entrada);
            }
        }
        return res;
    }
    

   
}
