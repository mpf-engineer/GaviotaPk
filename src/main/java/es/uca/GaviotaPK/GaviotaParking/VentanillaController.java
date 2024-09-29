package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventanilla")
public class VentanillaController {

    private final VentanillaService ventanillaService;
    private final ClienteService clienteService;
    private final TarifaService tarifaService;
    private final TicketService ticketService;

    @Autowired
    public VentanillaController(VentanillaService ventanillaService, ClienteService clienteService,
                                TarifaService tarifaService, TicketService ticketService) {
        this.ventanillaService = ventanillaService;
        this.clienteService = clienteService;
        this.tarifaService = tarifaService;
        this.ticketService = ticketService;
    }

    @PostMapping("/abonoMensual")
    public ResponseEntity<String> pagarAbonoMensual(@RequestParam UUID idCliente, @RequestParam UUID idTarifa) {
        try {
            Cliente cli = clienteService.getClientesById(idCliente);
            Tarifa tarifa = tarifaService.getTarifaById(idTarifa);

            ventanillaService.pagarAbonoMensual(cli,tarifa);
            return ResponseEntity.ok("Abono mensual pagado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo pagar el abono mensual.");
        }
    }

    @PostMapping("/abonoTrimestral")
    public ResponseEntity<String> pagarAbonoTrimestral(@RequestParam UUID idCliente, @RequestParam UUID idTarifa) {
        try {
            Cliente cli = clienteService.getClientesById(idCliente);
            Tarifa tarifa = tarifaService.getTarifaById(idTarifa);

            ventanillaService.pagarAbonoTrimestral(cli,tarifa);
            return ResponseEntity.ok("Abono Trimestral pagado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo pagar el abono Trimestral.");
        }
    }

    @PostMapping("/abonoAnual")
    public ResponseEntity<String> pagarAbonoAnual(@RequestParam UUID idCliente, @RequestParam UUID idTarifa) {
        try {
            Cliente cli = clienteService.getClientesById(idCliente);
            Tarifa tarifa = tarifaService.getTarifaById(idTarifa);

            ventanillaService.pagarAbonoAnual(cli,tarifa);
            return ResponseEntity.ok("Abono Anual pagado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo pagar el abono Anual.");
        }
    }

    @PostMapping("/ticket")
    public ResponseEntity<String> pagarTicket(@RequestParam UUID idTicket, @RequestParam UUID idCliente, @RequestParam UUID idTarifa){
        try {
            Ticket ticket = ticketService.getTicketById(idTicket);
            Cliente cliente = clienteService.getClientesById(idCliente);
            Tarifa tarifa = tarifaService.getTarifaById(idTarifa);

            ventanillaService.pagarTicket(ticket, cliente, tarifa);
            return ResponseEntity.ok("Ticket pagado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo pagar el ticket.");
        }
    }
}
