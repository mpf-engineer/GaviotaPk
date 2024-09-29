package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Repository
public class MemoryPagoRepository implements PagoRepository {
    private List<Pago> pagos;

    public MemoryPagoRepository(){
        pagos = new ArrayList<Pago>();
    }

    public List<Pago> getAll(){
        return pagos;
    }

    public void add(Pago pago){
        pagos.add(pago);    
    }

    public void update(Pago pago){
        //no se necesita en memory
    }

    public void delete(Pago pago){
        pagos.remove(pago);
    }

    // Método para obtener un abono según su id. Si no encuentra el abono lanza excepción
    public Pago getPagoById(UUID id){
        for(Pago pago: pagos){
            if(pago.getId() == id) return pago;
        }
        throw new IllegalArgumentException();
    }
}
