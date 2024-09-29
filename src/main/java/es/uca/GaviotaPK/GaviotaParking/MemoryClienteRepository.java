package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Repository
public class MemoryClienteRepository implements ClienteRepository {
    private List<Cliente> clientes;

    public MemoryClienteRepository(){
        clientes = new ArrayList<Cliente>();
    }

    public List<Cliente> getAll(){
        return clientes;
    }

    public void add(Cliente e){
        clientes.add(e);    
    }

    public void update(Cliente e){
        // Para trabajar en memoria no es necesario implementar este método
    }

    public void delete(Cliente e){
        clientes.remove(e);
    }

    public Cliente getClientesById(UUID id){
        for(Cliente cliente: clientes){
            if(cliente.getId() == id) return cliente;
        }
        throw new IllegalArgumentException();
    }  
    
    public Cliente getClienteByNombre(String nombre){
        for(Cliente cliente: clientes){
            if(cliente.getNombre().equals(nombre)) return cliente;
        }
        return null;
    }
}
