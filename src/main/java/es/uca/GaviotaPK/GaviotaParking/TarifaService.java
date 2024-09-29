package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TarifaService {
    private TarifaRepository tarifaRepository;

    @Autowired
    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public List<Tarifa> getAllTarifas() {
        return tarifaRepository.getAll();
    }

    public void addTarifa(Tarifa tarifa) {
        tarifaRepository.add(tarifa);
    }

    public void updateTarifa(Tarifa tarifa) {
        tarifaRepository.update(tarifa);
    }

    public void deleteTarifa(Tarifa tarifa) {
        tarifaRepository.delete(tarifa);
    }

    public Tarifa getTarifaById(UUID id){
        return tarifaRepository.getTarifaById(id);
    }

    public Tarifa getTarifaByNombre(String nom){
        return tarifaRepository.getTarifaByNombre(nom);
    }

}
