import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.AbonoTrimestral;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AbonoTrimestralTest {

    private AbonoTrimestral abono;
    private Vehiculo coche;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        coche = new Vehiculo("ABC123");
        abono = new AbonoTrimestral(BigDecimal.valueOf(150), coche);
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        abono = null;
        coche = null;
    }

    @Test
    public void testCalcularFechaCaducidad() {
       // Comprobación de que la fecha de caducidad es un año después de la fecha actual
       LocalDateTime fEsperadaMax = LocalDateTime.now().plusMonths(3).plusSeconds(10);
       LocalDateTime fEsperadaMin = LocalDateTime.now().plusMonths(3).minusSeconds(10);

       assert(abono.getFechaCaducidad().isBefore(fEsperadaMax) && 
               abono.getFechaCaducidad().isAfter(fEsperadaMin));}

    @Test
    public void testPrecio() {
        // Comprobación de que el precio del abono es el esperado
        assertEquals(BigDecimal.valueOf(150), abono.getPrecio());
    }
}
