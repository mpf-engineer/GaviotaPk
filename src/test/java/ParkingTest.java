import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.ParkingService;
import es.uca.GaviotaPK.GaviotaParking.EntradaService;
import es.uca.GaviotaPK.GaviotaParking.MemoryEntradaRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryPagoRepository;
import es.uca.GaviotaPK.GaviotaParking.MemorySalidaRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryTicketRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryVehiculoRepository;
import es.uca.GaviotaPK.GaviotaParking.SalidaService;
import es.uca.GaviotaPK.GaviotaParking.TicketService;
import es.uca.GaviotaPK.GaviotaParking.VehiculoService;
import es.uca.GaviotaPK.GaviotaParking.PagoService;

import es.uca.GaviotaPK.GaviotaParking.Vehiculo;


import org.junit.After;
import org.junit.Before;

public class ParkingTest {
    private ParkingService parkingService;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        parkingService = new ParkingService(
                                            new EntradaService(new MemoryEntradaRepository()),
                                            new SalidaService(new MemorySalidaRepository()), 
                                            new VehiculoService(new MemoryVehiculoRepository()), 
                                            new PagoService(new MemoryPagoRepository()),
                                            new TicketService(new MemoryTicketRepository()));
        parkingService.setNombre("Parking test");
        parkingService.setDir("Dirección de prueba");
        parkingService.setPlazasTotales(100);
        parkingService.setPlazasDisp(100);
       
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        parkingService = null;
    }

    @Test
    public void testGetNombre() {
        // Comprobación del nombre del parking
        assertEquals("Parking Test", parkingService.getNombre());
    }

    @Test
    public void testGetDir() {
        // Comprobación de la dirección del parking
        assertEquals("Dirección de prueba", parkingService.getDir());
    }

    @Test
    public void testGetPlazasDispInicial() {
        // Comprobación del número inicial de plazas disponibles
        assertEquals(100, parkingService.getPlazasDisp());
    }

    @Test
    public void testEntraCoche() {
        // Comprobación de que entra un coche correctamente
        parkingService.entraCoche("ABC123");
        assertEquals(99, parkingService.getPlazasDisp());
    }

    @Test
    public void testSaleCoche() {
        // Comprobación de que sale un coche correctamente
        Vehiculo coche = new Vehiculo("ABC123");     
        parkingService.entraCoche("ABC123");
        coche.getTicket().setFechaPago(LocalDateTime.now()); //Hacer mejor cuando exista ventanilla
        parkingService.saleCoche("ABC123");
        assertEquals(100, parkingService.getPlazasDisp());
    }
}