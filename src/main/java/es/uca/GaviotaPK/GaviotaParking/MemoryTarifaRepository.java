package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

//@Repository
public class MemoryTarifaRepository implements TarifaRepository {
    private List<Tarifa> tarifas;

    public MemoryTarifaRepository(){
        tarifas = new ArrayList<Tarifa>();
    }

    public List<Tarifa> getAll(){
        return tarifas;
    }

    public void add(Tarifa tarifa){
        tarifas.add(tarifa);    
    }

    public void update(Tarifa tarifa){
        //No hace falta en memoria
    }

    public void delete(Tarifa tarifa){
        tarifas.remove(tarifa);
    }


    // Método para obtener una tarifa según su id. Si no encuentra la tarifa lanza excepción
    public Tarifa getTarifaById(UUID id){
        for(Tarifa tarifa: tarifas){
            if(tarifa.getId() == id) return tarifa;
        }
        throw new IllegalArgumentException();
    }

    public Tarifa getTarifaByNombre(String nombre){
        for(Tarifa tarifa: tarifas){
            if(tarifa.getNombre().equals(nombre)) return tarifa;
        }
        throw new IllegalArgumentException();
    }
}
