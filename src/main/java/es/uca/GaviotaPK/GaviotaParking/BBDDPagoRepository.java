package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDPagoRepository implements PagoRepository {
    private PagoAdapter pagoAdapter;

    public BBDDPagoRepository(PagoAdapter pa){
        this.pagoAdapter = pa;
    }
    public List<Pago> getAll(){
        List<PagoRelacional> lista = pagoAdapter.findAll();
        List<Pago> Pagos = new ArrayList<Pago>();
        for (PagoRelacional p : lista){
            Pago Pago = p.toPago();
            Pago.setCliente(p.getCliente().toCliente());
            Pagos.add(Pago);
        }
        return Pagos;
    }

    public void add(Pago e){
        PagoRelacional p = e.toPagoRelacional();
        p.setCliente(e.getCliente().toClienteRelacional());
        p.setId(e.getId());
        pagoAdapter.save(p);     
    }

    public void update(Pago e){
        PagoRelacional p = e.toPagoRelacional();
        p.setCliente(e.getCliente().toClienteRelacional());
        pagoAdapter.save(p); 
    }

    public void delete(Pago e){
        PagoRelacional p = e.toPagoRelacional();
        pagoAdapter.delete(p);
    }

    public Pago getpagosById(UUID id){
        Optional<PagoRelacional> pago = pagoAdapter.findById(id);
        if(pago.isPresent()){
            Pago p = pago.get().toPago();
            p.setCliente(pago.get().getCliente().toCliente());
        return p;
        }
        else return null;
    } 
}
