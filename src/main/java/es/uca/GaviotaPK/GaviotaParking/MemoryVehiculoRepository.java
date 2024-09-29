package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

//@Repository
public class MemoryVehiculoRepository implements VehiculoRepository {
    private List<Vehiculo> vehiculos;

    public MemoryVehiculoRepository(){
        vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> getAll(){
        return vehiculos;
    }

    public void add(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    public void update(Vehiculo vehiculo){
        // Para trabajar en memoria no es necesario implementar este método
    }

    public void updateTicket(Vehiculo vehiculo, Ticket ticket){
        vehiculo.setTicket(ticket);
    }
    public void updateAbono(Vehiculo vehiculo, Abono Abono){
        vehiculo.setAbonoActual(Abono);
    }

    public void updateConductor(Vehiculo vehiculo, Cliente cliente){
        vehiculo.setConductor(cliente);
    }

    public void updateEnParking(Vehiculo vehiculo){
        vehiculo.setEnParking(vehiculo.estaEnParking());
    }


    public void delete(Vehiculo vehiculo){
        vehiculos.remove(vehiculo);
    }

    // Método para obtener un abono según su id. Si no encuentra el abono lanza excepción
    public Vehiculo getVehiculoByMatricula(String matricula){
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.getMatricula().equals(matricula)) return vehiculo;
        }
        return null;
    }

}
