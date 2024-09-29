package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final TicketService ticketService;
    private final AbonoService abonoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService, TicketService ticketService,
                                AbonoService abonoService) {
        this.vehiculoService = vehiculoService;
        this.ticketService = ticketService;
        this.abonoService = abonoService;
    }

    @GetMapping("/Vehiculos-all")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/VehiculoPorMatricula")
    public ResponseEntity<Vehiculo> getVehiculoByMatricula(@RequestParam String matricula) {
    try {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(matricula);
        
        return ResponseEntity.ok(vehiculo);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}


    @PostMapping
    public ResponseEntity<Void> addVehiculo(@RequestBody String matricula) {
        Vehiculo nuevoVehiculo = new Vehiculo(matricula);
        vehiculoService.addVehiculo(nuevoVehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{matricula}-cambiarTicket")
    public ResponseEntity<Void> updateVehiculo(@RequestParam String matricula, @RequestParam UUID idTicket) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(matricula);
        Ticket ticket = ticketService.getTicketById(idTicket);
        vehiculoService.updateVehiculoTicket(vehiculo, ticket);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{matricula}-cambiarAbono")
    public ResponseEntity<Void> updateVehiculoAbono(@RequestParam String matricula, @RequestParam UUID idAbono) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(matricula);
        Abono abono = abonoService.getAbonoById(idAbono);
        vehiculoService.updateVehiculoAbono(vehiculo, abono);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable String matricula) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(matricula);
        if (vehiculo != null) {
            vehiculoService.deleteVehiculo(vehiculo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
