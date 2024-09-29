import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.*;



import org.junit.After;
import org.junit.Before;

public class SimulacionTest {
        private AbonoService abonoService;
        private EntradaService entradaService;
        private SalidaService salidaService;
        private PagoService pagoService;
        private TarifaService tarifaService;
        private TicketService ticketService;
        private VehiculoService vehiculoService;
        private VentanillaService ventanillaService;
        private InformeService informeService;
        private ParkingService parkingService;
        private Vehiculo vehiculo1, vehiculo2;
        private Cliente cliente1, cliente2;
        private Tarifa tarifa1;
       

    @Before
    public void setUp(){
        abonoService = new AbonoService(new MemoryAbonoRepository());
        entradaService = new EntradaService(new MemoryEntradaRepository());
        salidaService = new SalidaService(new MemorySalidaRepository());
        pagoService = new PagoService(new MemoryPagoRepository());
        tarifaService = new TarifaService(new MemoryTarifaRepository());
        ticketService = new TicketService(new MemoryTicketRepository());
        ventanillaService = new VentanillaService(abonoService, pagoService);
        vehiculoService = new VehiculoService(new MemoryVehiculoRepository());
        informeService = new InformeService(new GeneradorInfTXT(entradaService, salidaService, pagoService));
        parkingService = new ParkingService(entradaService, salidaService, vehiculoService, pagoService, ticketService);
        vehiculo1 = new Vehiculo("1234ABC");
        vehiculo2 = new Vehiculo("5678DEF");
        cliente1 = new Cliente("2379423E", "Martín Pérez", vehiculo1);
        cliente2 = new Cliente("1238513G", "Blanca Sánchez", vehiculo2);
        tarifa1 = new Tarifa("TarifaEstandar", BigDecimal.valueOf(2),BigDecimal.valueOf(20), BigDecimal.valueOf(50),
                    BigDecimal.valueOf(90), BigDecimal.valueOf(150));

        


    }
    @After
    public void tearDown() {
        abonoService = null;
        entradaService = null;
        salidaService = null;
        pagoService = null;
        tarifaService = null;
        ticketService = null;
        vehiculoService = null;
        parkingService = null; 
    }

    @Test
    public void testSimulacion(){
        //Un admin añade una nueva tarifa:
        tarifaService.addTarifa(tarifa1);
        //Entran dos clientes no abonados en el parking 
        parkingService.entraCoche(vehiculo1.getMatricula());
        parkingService.entraCoche(vehiculo2.getMatricula());

        //El cliente1 paga el ticket de su estancia y se va
        ventanillaService.pagarTicket(vehiculo1.getTicket(), cliente1, tarifa1);
        parkingService.saleCoche(vehiculo1.getMatricula());

        //El cliente2 paga el ticket de su estancia, se abona mensualmente y se va
        ventanillaService.pagarTicket(vehiculo2.getTicket(), cliente2, tarifa1);
        ventanillaService.pagarAbonoMensual(cliente2, tarifa1);
        parkingService.saleCoche(vehiculo2.getMatricula());

        //El cliente2 entra como abonado
        parkingService.entraCoche(vehiculo2.getMatricula());

        //El cliente1 entra y saca ticket de nuevo
        parkingService.entraCoche(vehiculo1.getMatricula());

        //El cliente1 paga el ticket y se abona trimestralmente
        ventanillaService.pagarTicket(vehiculo1.getTicket(), cliente1, tarifa1);
        ventanillaService.pagarAbonoTrimestral(cliente1, tarifa1);

        //El cliente2 sale como abonado
        parkingService.saleCoche(vehiculo2.getMatricula());

        //El cliente1 sale con el ticket
        parkingService.saleCoche(vehiculo1.getMatricula());

        //Un admin pide un informe de entradas y salidas y uno de ingresos
        informeService.generarInformeES(LocalDateTime.now().toLocalDate(),null);
        informeService.generarInformeIngresos(LocalDateTime.now().toLocalDate().minusDays(1), LocalDateTime.now().toLocalDate().plusDays(1));

        //Un admin pide un informe de entradas del vehiculo1
        informeService.generarInformeES(null, vehiculo1.getMatricula());
    }
}
