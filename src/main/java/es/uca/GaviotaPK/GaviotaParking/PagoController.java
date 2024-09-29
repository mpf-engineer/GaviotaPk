package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final TicketService ticketService;
    private final TarifaService tarifaService;
    private final ClienteService clienteService;
    private final VehiculoService vehiculoService;
    private final AbonoService abonoService; 

    @Autowired
    public PagoController(PagoService pagoService, TicketService ticketService, TarifaService tarifaService,
                            ClienteService clienteService, VehiculoService vehiculoService, AbonoService abonoService) {
        this.pagoService = pagoService;
        this.ticketService = ticketService;
        this.tarifaService = tarifaService;
        this.clienteService = clienteService;
        this.vehiculoService = vehiculoService;
        this.abonoService = abonoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagoService.getAllPagos();
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Void> addPago(@RequestBody Pago pago) {
        pagoService.addPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/PagoTicket")
    public ResponseEntity<Void> addPagoTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.getTicketByCodigo(ticketDTO.getCodigo());
        Tarifa tarifa = tarifaService.getTarifaByNombre(ticketDTO.getNombreTarifa());
        Cliente cliente = clienteService.getClienteByNombre("anonimo");

        if(cliente == null){
            Cliente cliAnonimo = new Cliente("0000000", "anonimo", null);
            clienteService.addCliente(cliAnonimo);
            cliente = cliAnonimo;
        }
        Pago pago = new Pago(ticket, cliente, tarifa);
        pagoService.addPago(pago);

        ticket.setPagado();
        ticket.setFechaPago(LocalDateTime.now());
        ticketService.updateTicket(ticket);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //PARA PAGAR UN ABONO HAY QUE ESTAR REGISTRADO EN EL SISTEMA Y TENER UN COCHE ASOCIADO
    @PostMapping("/PagoAbonoAnual")
    public ResponseEntity<Void> addAbonoAnual(@RequestBody AbonoDTO abonoDTO) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) vehiculoService.addVehiculo(new Vehiculo(abonoDTO.getMatricula()));

        Abono abono = new AbonoAnual(tarifa.getCosteAbonoAnual(), vehiculo);
        abonoService.addAbono(abono);

        vehiculoService.updateVehiculoAbono(vehiculo, abono);

        Pago pago = new Pago(abono, vehiculo.getConductor(), vehiculo);
        pagoService.addPago(pago);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

     //PARA PAGAR UN ABONO HAY QUE ESTAR REGISTRADO EN EL SISTEMA Y TENER UN COCHE ASOCIADO
     @PostMapping("/PagoAbonoTrimestral")
     public ResponseEntity<Void> addAbonoTrimestral(@RequestBody AbonoDTO abonoDTO) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) vehiculoService.addVehiculo(new Vehiculo(abonoDTO.getMatricula()));
 
        Abono abono = new AbonoTrimestral(tarifa.getCosteAbonoTrimestral(), vehiculo);
        abonoService.addAbono(abono);

        vehiculoService.updateVehiculoAbono(vehiculo, abono);

 
        Pago pago = new Pago(abono, vehiculo.getConductor(), vehiculo);
        pagoService.addPago(pago);

         
        return ResponseEntity.status(HttpStatus.CREATED).build();
     }

      //PARA PAGAR UN ABONO HAY QUE ESTAR REGISTRADO EN EL SISTEMA Y TENER UN COCHE ASOCIADO
    @PostMapping("/PagoAbonoMensual")
    public ResponseEntity<Void> addAbonoMensual(@RequestBody AbonoDTO abonoDTO) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) vehiculoService.addVehiculo(new Vehiculo(abonoDTO.getMatricula()));

        Abono abono = new AbonoMensual(tarifa.getCosteAbonoMensual(), vehiculo);
        abonoService.addAbono(abono);

        vehiculoService.updateVehiculoAbono(vehiculo, abono);
        
        Pago pago = new Pago(abono, vehiculo.getConductor(), vehiculo);
        pagoService.addPago(pago);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

     

}
