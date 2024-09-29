import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Entrada;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import org.junit.Before;
import org.junit.After;
import java.time.LocalDateTime;

public class EntradaTest {
    private Entrada entrada;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        Vehiculo vehiculo = new Vehiculo("ABC123");
        LocalDateTime fecha = LocalDateTime.of(2022, 4, 10, 12, 0); // Fecha de ejemplo
        entrada = new Entrada(vehiculo, fecha);
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        entrada = null;
    }

    @Test
    public void testGetMatricula() {
        // Comprobar que se obtiene la matrícula del vehículo correctamente
        assertEquals("ABC123", entrada.getMatricula());
    }

    @Test
    public void testGetFecha() {
        // Comprobar que se obtiene la fecha de la entrada correctamente
        LocalDateTime fechaEsperada = LocalDateTime.of(2022, 4, 10, 12, 0); // Fecha de ejemplo
        assertEquals(fechaEsperada, entrada.getFecha());
    }
}