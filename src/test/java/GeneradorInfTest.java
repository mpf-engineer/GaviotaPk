
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Abono;
import es.uca.GaviotaPK.GaviotaParking.Cliente;
import es.uca.GaviotaPK.GaviotaParking.Entrada;
import es.uca.GaviotaPK.GaviotaParking.GeneradorInf;
import es.uca.GaviotaPK.GaviotaParking.GeneradorInfTXT;
import es.uca.GaviotaPK.GaviotaParking.Pago;
import es.uca.GaviotaPK.GaviotaParking.ParkingService;
import es.uca.GaviotaPK.GaviotaParking.AbonoService;
import es.uca.GaviotaPK.GaviotaParking.EntradaService;
import es.uca.GaviotaPK.GaviotaParking.PagoService;
import es.uca.GaviotaPK.GaviotaParking.SalidaService;
import es.uca.GaviotaPK.GaviotaParking.Salida;
import es.uca.GaviotaPK.GaviotaParking.Movimiento;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;
import es.uca.GaviotaPK.GaviotaParking.MemoryAbonoRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryEntradaRepository;
import es.uca.GaviotaPK.GaviotaParking.MemorySalidaRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryVehiculoRepository;
import es.uca.GaviotaPK.GaviotaParking.MemoryPagoRepository;

import org.junit.After;
import org.junit.Before;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class GeneradorInfTest {

     private EntradaService EntradaService;
    private SalidaService SalidaService;
    private PagoService PagoService;
    private GeneradorInf generadorInf;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        EntradaService = new EntradaService(new MemoryEntradaRepository());
        SalidaService = new SalidaService(new MemorySalidaRepository());
        PagoService = new PagoService(new MemoryPagoRepository());
        generadorInf = new GeneradorInfTXT(EntradaService, SalidaService, PagoService);
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        EntradaService = null;
        SalidaService = null;
        PagoService = null;
        generadorInf = null;
    }

    @Test
    public void testGenerarInformeES() throws IOException {
        // Crear datos de prueba
        LocalDateTime fecha = LocalDateTime.now();
        Vehiculo coche =  new Vehiculo("ABC123");
        EntradaService.addEntrada("ABC123", LocalDateTime.now());

        // Ejecutar el método que se va a probar
        List<Movimiento> movimientos = generadorInf.generarInformeES(fecha.toLocalDate(), coche.getMatricula());

        // Comprobar que el informe se ha generado correctamente
        File informe = new File("informe.txt");
        assertEquals(1, movimientos);
        assertEquals(true, informe.exists());

        // Eliminar el archivo de prueba creado
        informe.delete();
    }

    @Test
    public void testGenerarInformeIngresos() throws IOException {
        // Crear datos de prueba
        LocalDateTime fechaIni = LocalDateTime.now().minusDays(1);
        LocalDateTime fechaFin = LocalDateTime.now().plusDays(1);
        Vehiculo coche =  new Vehiculo("ABC123");
        Cliente cliente = new Cliente("Alejandro", "235834F", coche);
        Pago pago = new Pago(BigDecimal.valueOf(50), cliente);
        PagoService.addPago(pago);

        // Ejecutar el método que se va a probar
        List<Pago>ingresosPagos = generadorInf.generarInformeIngresos(fechaIni.toLocalDate(), fechaFin.toLocalDate());

        // Comprobar que el informe se ha generado correctamente
        File informe = new File("Informe_Ingresos.txt");
        assertEquals(BigDecimal.valueOf(50), ingresosPagos);
        assertEquals(true, informe.exists());

        // Eliminar el archivo de prueba creado
        informe.delete();
    }
}
