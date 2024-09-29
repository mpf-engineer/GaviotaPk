
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Abono;
import es.uca.GaviotaPK.GaviotaParking.AbonoMensual;
import es.uca.GaviotaPK.GaviotaParking.AbonoService;
import es.uca.GaviotaPK.GaviotaParking.MemoryAbonoRepository;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import org.junit.Before;
import org.junit.After;
import java.util.List;
import java.math.BigDecimal;

public class AbonoServiceTest {
    private AbonoService AbonoService;
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        AbonoService = new AbonoService(new MemoryAbonoRepository());
        vehiculo = new Vehiculo("123ABC");
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        AbonoService = null;
    }

    @Test
    public void testGetAbonos() {
        // Comprobar que inicialmente el repositorio de abonos está vacío
        List<Abono> abonos = AbonoService.getAllAbonos();
        assertTrue(abonos.isEmpty());
    }

    @Test
    public void testaddAbono() {
        // Añadir un abono al repositorio y comprobar que se ha añadido correctamente
        Abono abono = new AbonoMensual(BigDecimal.valueOf(50), vehiculo);
        AbonoService.addAbono(abono);

        List<Abono> abonos = AbonoService.getAllAbonos();
        assertEquals(1, abonos.size());
        assertEquals(abono, abonos.get(0));
    }

    @Test
    public void testDeleteAbono() {
        // Añadir un abono al repositorio y comprobar que se ha añadido correctamente
        Abono abono = new AbonoMensual(BigDecimal.valueOf(50), vehiculo);
        AbonoService.addAbono(abono);

        List<Abono> abonos = AbonoService.getAllAbonos();
        assertEquals(1, abonos.size());
        assertEquals(abono, abonos.get(0));

        // Borrar el abono y comprobar que se ha borrado correctamente
        AbonoService.delete(abono);
        abonos = AbonoService.getAllAbonos();
        assertTrue(abonos.isEmpty());
    }
}