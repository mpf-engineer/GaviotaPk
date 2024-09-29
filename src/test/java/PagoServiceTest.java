
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Pago;
import es.uca.GaviotaPK.GaviotaParking.PagoService;
import es.uca.GaviotaPK.GaviotaParking.MemoryPagoRepository;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;
import es.uca.GaviotaPK.GaviotaParking.Cliente;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.After;
import java.util.ArrayList;
import java.util.List;

public class PagoServiceTest {
    private PagoService PagoService;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        PagoService = new PagoService(new MemoryPagoRepository());
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        PagoService = null;
    }

    @Test
    public void testGetPagos() {
        // Comprobar que inicialmente el repositorio de pagos está vacío
        List<Pago> pagos = PagoService.getAllPagos();
        assertTrue(pagos.isEmpty());
    }

    @Test
    public void testaddPago() {
        // Añadir un pago al repositorio y comprobar que se ha añadido correctamente
        Vehiculo vehiculo = new Vehiculo("ABC123");
        Cliente cliente = new Cliente("Alejandro", "423587f", vehiculo);
        Pago pago = new Pago(BigDecimal.valueOf(20), cliente);
        PagoService.addPago(pago);

        List<Pago> pagos = PagoService.getAllPagos();
        assertEquals(1, pagos.size());
        assertEquals(pago, pagos.get(0));
    }
}

