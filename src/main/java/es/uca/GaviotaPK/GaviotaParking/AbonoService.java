package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AbonoService {
    private final AbonoRepository abonoRepository;

    @Autowired
    public AbonoService(AbonoRepository abonoRepository) {
        this.abonoRepository = abonoRepository;
    }

    public List<Abono> getAllAbonos() {
        return abonoRepository.getAll();
    }
    public Abono getAbonoById(UUID id){
        return abonoRepository.getAbonoById(id);
    }

    public void addAbono(Abono Abono) {
        abonoRepository.add(Abono);
    }

    public void update(Abono Abono) {
        abonoRepository.update(Abono);
    }

    public void delete(Abono Abono) {
        abonoRepository.delete(Abono);
    }

}
