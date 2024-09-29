package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes(){
        return clienteRepository.getAll();
    }

    public Cliente getClientesById(UUID id){
        return clienteRepository.getClientesById(id);
    }

    public Cliente getClienteByNombre(String nombre){
        return clienteRepository.getClienteByNombre(nombre);
    }

    // Este método hay que mejorarlo
    public void addCliente(Cliente cliente){ 
        clienteRepository.add(cliente);
    }
    public void updateCliente(Cliente cliente){
        clienteRepository.update(cliente);
    }

    public void deleteCliente(Cliente cliente){
        clienteRepository.delete(cliente);
    }

}