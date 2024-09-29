import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.SalidaService;
import es.uca.GaviotaPK.GaviotaParking.Salida;
import es.uca.GaviotaPK.GaviotaParking.MemorySalidaRepository;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import org.junit.After;
import org.junit.Before;
import java.util.List;
import java.time.LocalDateTime;

public class SalidaServiceTest {

    private SalidaService repoS;

    @Before
    public void setUp() {
        repoS = new SalidaService(new MemorySalidaRepository());
    }

    @Test
    public void testConstructor() {
        assertNotNull(repoS.getAllSalidas());
    }

    @Test
    public void testRegistrarSalida() {
        Salida salida = new Salida(new Vehiculo("123ABC"), LocalDateTime.now());
        repoS.addSalida(salida);
        List<Salida> salidas = repoS.getAllSalidas();
        assertTrue(salidas.contains(salida));
    }
}