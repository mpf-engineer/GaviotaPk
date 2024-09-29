package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDVehiculoRepository implements VehiculoRepository {
    private VehiculoAdapter vehiculoAdapter;

    public BBDDVehiculoRepository(VehiculoAdapter ca){
        this.vehiculoAdapter = ca;
    }

    public List<Vehiculo> getAll(){
        List<VehiculoRelacional> lista = vehiculoAdapter.findAll();
        List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        for (VehiculoRelacional v : lista){
            Vehiculo vehiculo = v.toVehiculo();
            vehiculos.add(vehiculo);
        }
        return vehiculos;
    }

    public void add(Vehiculo e){
        VehiculoRelacional v = e.toVehiculoRelacional();
        if(e.getAbono() != null) v.setAbonoActual(e.getAbono().toAbonoRelacional());
        if(e.getTicket() != null) v.setTicket(e.getTicket().toTicketRelacional());
        vehiculoAdapter.save(v);  
          
    }

    
    public void update(Vehiculo e){
        VehiculoRelacional v = e.toVehiculoRelacional();
        if(e.getAbono() != null)v.setAbonoActual(e.getAbono().toAbonoRelacional());
        if(e.getTicket() != null)v.setTicket(e.getTicket().toTicketRelacional());
        if(e.getConductor()!= null)v.setConductor(e.getConductor().toClienteRelacional());
        v.setEnParking(e.estaEnParking());
        System.out.println("TICKET DEL VR:" + v.getTicket().getCodigo());
        vehiculoAdapter.save(v);  
    }

    public void updateAbono(Vehiculo e, Abono a){ //Implementar el caso en que no se encuentre
        Optional<VehiculoRelacional> v = vehiculoAdapter.findBymatricula(e.getMatricula());
        AbonoRelacional abono = a.toAbonoRelacional();
        v.get().setAbonoActual(abono); 
        abono.setIdAbono(a.getId());       
        vehiculoAdapter.save(v.get());
    }
    public void updateTicket(Vehiculo e, Ticket t){ //Implementar el caso en que no se encuentre
        Optional<VehiculoRelacional> v = vehiculoAdapter.findBymatricula(e.getMatricula());
        v.get().setTicket(t.toTicketRelacional());
        vehiculoAdapter.save(v.get());
    }

    public void updateConductor(Vehiculo e, Cliente c){ //Implementar el caso en que no se encuentre
        Optional<VehiculoRelacional> v = vehiculoAdapter.findBymatricula(e.getMatricula());
        v.get().setConductor(c.toClienteRelacional());
        vehiculoAdapter.save(v.get());
    }

    public void updateEnParking(Vehiculo e){ //Implementar el caso en que no se encuentre
        Optional<VehiculoRelacional> v = vehiculoAdapter.findBymatricula(e.getMatricula());
        v.get().setEnParking(e.estaEnParking());
        vehiculoAdapter.save(v.get());
    }

    public void delete(Vehiculo e){ //Implementar el caso en que no se encuentre
        Optional<VehiculoRelacional> v = vehiculoAdapter.findBymatricula(e.getMatricula());
        vehiculoAdapter.delete(v.get());
    }

    public Vehiculo getVehiculoByMatricula(String matricula){
        Optional<VehiculoRelacional> cli = vehiculoAdapter.findBymatricula(matricula);
        if(cli.isPresent()){
            Vehiculo v = new Vehiculo(cli.get().getMatricula());
            if(cli.get().getAbono() != null) v.setAbonoActual(cli.get().getAbono().toAbono());
            if(cli.get().getTicket() != null) v.setTicket(cli.get().getTicket().toTicket());
            if(cli.get().getConductor() != null) v.setConductor(cli.get().getConductor().toCliente());
            v.setEnParking(cli.get().getEnParking());
            return v;
        } return null;
    } 
}
