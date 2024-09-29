package es.uca.GaviotaPK.GaviotaParking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class BBDDClienteRepository implements ClienteRepository {
    private ClienteAdapter clienteAdapter;

    public BBDDClienteRepository(ClienteAdapter ca){
        this.clienteAdapter = ca;
    }

    public List<Cliente> getAll(){
        List<ClienteRelacional> Clientes = clienteAdapter.findAll();
        List<Cliente> ClientesNormales = new ArrayList<Cliente>();
        for(ClienteRelacional clienteR : Clientes){
            Cliente cli = clienteR.toCliente();
            cli.setVehiculo(clienteR.getVehiculo().toVehiculo());
            ClientesNormales.add(clienteR.toCliente());
        }
        return ClientesNormales;
    }

    public void add(Cliente e){
        ClienteRelacional clienteR = e.toClienteRelacional();
        if(e.getVehiculo() != null) clienteR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        if(e.getVehiculo() != null) clienteR.getVehiculo().setConductor(clienteR);
        clienteAdapter.save(clienteR);   
    }

    public void update(Cliente e){
        ClienteRelacional clienteR = e.toClienteRelacional();
        clienteR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        clienteAdapter.save(clienteR); 
    }

    public void delete(Cliente e){
        ClienteRelacional clienteR = e.toClienteRelacional();
        clienteR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        clienteAdapter.delete(clienteR); 
    }

    public Cliente getClientesById(UUID id){
        Optional<ClienteRelacional> cli = clienteAdapter.findById(id);
        if(cli.isPresent()){
            Cliente c = cli.get().toCliente();
            c.setVehiculo(cli.get().getVehiculo().toVehiculo());
            return c;
        }
        else return null;
    }
    
    public Cliente getClienteByNombre(String nom){
        Optional<ClienteRelacional> cli = clienteAdapter.findByNombre(nom);
        if(cli.isPresent()){
            Cliente c = cli.get().toCliente();
            if(cli.get().getVehiculo() != null) c.setVehiculo(cli.get().getVehiculo().toVehiculo());
            return c;
        }
        else return null;
    }  
}
