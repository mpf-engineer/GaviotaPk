package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Repository
public class MemoryAbonoRepository implements AbonoRepository {
    private List<Abono> abonos;

    public MemoryAbonoRepository(){
        abonos = new ArrayList<>();
    }

    // Método para obtener todos los abonos del repositorio
    public List<Abono> getAll(){
        return abonos;
    }

    // Método para agregar un abono al repositorio
    public void add(Abono abono){
        abonos.add(abono);    
    }

    public void update(Abono abono){
        // Para trabajar en memoria no es necesario implementar este método
    }

    // Método para eliminar un abono del repositorio
    public void delete(Abono abono){
        abonos.remove(abono);
    }
    // Método para obtener un abono según su id. Si no encuentra el abono lanza excepción
    public Abono getAbonoById(UUID id){
        for(Abono abono: abonos){
            if(abono.getId() == id) return abono;
        }
        throw new IllegalArgumentException();
    }
}

