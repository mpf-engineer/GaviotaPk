package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.getAll();
    }

    public void addVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.add(vehiculo);
    }

    public void updateVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.update(vehiculo);
    }

    public void updateVehiculoTicket(Vehiculo vehiculo, Ticket t) {
        vehiculoRepository.updateTicket(vehiculo, t);
    }
    public void updateVehiculoAbono(Vehiculo vehiculo, Abono a) {
        vehiculoRepository.updateAbono(vehiculo, a);
    }

    public void updateVehiculoConductor(Vehiculo vehiculo, Cliente c) {
        vehiculoRepository.updateConductor(vehiculo, c);
    }

    public void updateVehiculoEnParking(Vehiculo vehiculo) {
        vehiculoRepository.updateEnParking(vehiculo);
    }

    public void deleteVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.delete(vehiculo);
    }

    public Vehiculo getVehiculoByMatricula(String matricula){
        return vehiculoRepository.getVehiculoByMatricula(matricula);
    }

}
