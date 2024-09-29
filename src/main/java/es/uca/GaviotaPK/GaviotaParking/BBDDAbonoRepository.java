package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BBDDAbonoRepository implements AbonoRepository {
    private AbonoAdapter abonoAdapter;

    public BBDDAbonoRepository(AbonoAdapter aa){
        this.abonoAdapter = aa;
    }
    
    // Método para obtener todos los abonos del repositorio
    public List<Abono> getAll(){
        List<AbonoRelacional> abonos = abonoAdapter.findAll();
        List<Abono> abonosNormales = new ArrayList<Abono>();
        
        for(AbonoRelacional abonoR : abonos){
            Abono abono = abonoR.toAbono();
            abono.setVehiculo(abonoR.getVehiculo().toVehiculo());
            abonosNormales.add(abono);
        }

        return abonosNormales;
    }

    // Método para agregar un abono al repositorio
    public void add(Abono abono){

        AbonoRelacional abonoR = abono.toAbonoRelacional();
        abonoR.setIdAbono(abono.getId());

        abonoR.setVehiculo(abono.getVehiculo().toVehiculoRelacional()); //Un abono siempre tiene vehículo, no hay que comprobar null
        abonoR.getVehiculo().setAbonoActual(abonoR);
        
        abonoAdapter.save(abonoR);    
    }

    public void update(Abono abono){

        AbonoRelacional abonoR = abono.toAbonoRelacional();
        abonoR.setVehiculo(abono.getVehiculo().toVehiculoRelacional());
        abonoAdapter.save(abonoR); 
    }

    // Método para eliminar un abono del repositorio
    public void delete(Abono abono){
        AbonoRelacional abonoR = abono.toAbonoRelacional();
        abonoR.setVehiculo(abono.getVehiculo().toVehiculoRelacional());
        abonoAdapter.delete(abonoR); 
    }
    // Método para obtener un abono según su id. Si no encuentra el abono lanza excepción
    public Abono getAbonoById(UUID id){
        Optional<AbonoRelacional> abono = abonoAdapter.findById(id);
        if(abono.isPresent()) return abono.get().toAbono();
        else return null;
    }
}
