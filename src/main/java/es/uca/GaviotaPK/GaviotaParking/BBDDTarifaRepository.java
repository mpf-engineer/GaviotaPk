package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDTarifaRepository implements TarifaRepository{
    private TarifaAdapter tarifaAdapter;

    public BBDDTarifaRepository(TarifaAdapter ca){
        this.tarifaAdapter = ca;
    }

    public List<Tarifa> getAll(){
        List<TarifaRelacional> Tarifas = tarifaAdapter.findAll();
        List<Tarifa> TarifasNormales = new ArrayList<Tarifa>();
        for(TarifaRelacional TarifaR : Tarifas){
            TarifasNormales.add(TarifaR.toTarifa());
        }
        return TarifasNormales;
    }

    public void add(Tarifa e){
        tarifaAdapter.save(e.toTarifaRelacional());    
    }

    public void update(Tarifa e){
        tarifaAdapter.save(e.toTarifaRelacional());
    }

    public void delete(Tarifa e){
        tarifaAdapter.delete(e.toTarifaRelacional());
    }

    public Tarifa getTarifaById(UUID id){
        Optional<TarifaRelacional> cli = tarifaAdapter.findById(id);
        if(cli.isPresent()) return cli.get().toTarifa();
        else return null;
    } 

    public Tarifa getTarifaByNombre(String nom){
        Optional<TarifaRelacional> cli = tarifaAdapter.findByNombre(nom);
        if(cli.isPresent()) return cli.get().toTarifa();
        else return null;
    } 
}
