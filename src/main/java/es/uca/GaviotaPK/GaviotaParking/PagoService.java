package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> getAllPagos() {
        return pagoRepository.getAll();
    }

    public void addPago(Pago pago) {
        pagoRepository.add(pago);
    }

    public void updatePago(Pago pago) {
        pagoRepository.update(pago);
    }

    public void deletePago(Pago pago) {
        pagoRepository.delete(pago);
    }
}
