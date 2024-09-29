package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;
    private VehiculoService vehiculoService;

    public ClienteController(ClienteService cs, VehiculoService vs){
        this.clienteService = cs;
        this.vehiculoService = vs;
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/clientes-id")
    public Cliente getClientes(@RequestParam UUID id){
        return clienteService.getClientesById(id);
      
    }

    @PostMapping("/nuevo-cliente")
    public void addCliente(@RequestBody ClienteDTO clienteDTO){
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(clienteDTO.getMatricula());
        if(vehiculo == null){
            vehiculo = new Vehiculo(clienteDTO.getMatricula());
            vehiculoService.addVehiculo(vehiculo);
        }
        Cliente cliente = new Cliente(clienteDTO.getNif(), clienteDTO.getNombre(), vehiculo);
        clienteService.addCliente(cliente);

        vehiculo.setConductor(cliente);
        vehiculoService.updateVehiculoConductor(vehiculo, cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public void deleteCliente(@RequestParam String id){
        deleteCliente(id);
    }

    //Hace falta modificar datos de clientes?
}

