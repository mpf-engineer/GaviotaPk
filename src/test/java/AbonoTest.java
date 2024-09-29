import es.uca.GaviotaPK.GaviotaParking.Abono;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;
import es.uca.GaviotaPK.GaviotaParking.AbonoAnual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AbonoTest {
    private Abono abono;
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        vehiculo = new Vehiculo("ABC123");
        abono = new AbonoAnual(BigDecimal.valueOf(100), vehiculo);
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        abono = null;
    }

    @Test
    public void testGetPrecio() {
        // Comprobación del precio del abono
        assertEquals(BigDecimal.valueOf(100), abono.getPrecio());
    }

    @Test
    public void testEstaCaducado() {
        // Comprobación de que el abono no está caducado justo después de crearlo
        assertFalse(abono.estaCaducado());
    }

    @Test
    public void testGetVehiculo() {
        // Comprobación del vehículo asociado al abono
        assertNotNull(abono.getVehiculo());
        assertEquals("ABC123", abono.getVehiculo().getMatricula());
    }

    @Test
    public void testGetFechaCaducidad() {
               // Comprobación de que la fecha de caducidad es un año después de la fecha actual
               LocalDateTime fEsperadaMax = LocalDateTime.now().plusYears(1).plusSeconds(10);
               LocalDateTime fEsperadaMin = LocalDateTime.now().plusYears(1).minusSeconds(10);
       
               assert(abono.getFechaCaducidad().isBefore(fEsperadaMax) && 
                       abono.getFechaCaducidad().isAfter(fEsperadaMin));
    }

    @Test
    public void testGetFechaCreacion() {
        // Comprobación de que la fecha de creación del abono es correcta
        assertNotNull(abono.getFechaCreacion());
        assertTrue(abono.getFechaCreacion().isBefore(LocalDateTime.now()));
    }
}