
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.EntradaService;
import es.uca.GaviotaPK.GaviotaParking.MemoryEntradaRepository;

import org.junit.Before;
import java.time.LocalDateTime;

public class EntradaServiceTest {

    private EntradaService repoE;

    @Before
    public void setUp() {
        repoE = new EntradaService(new MemoryEntradaRepository());
    }

    @Test
    public void testConstructor() {
        assertNotNull(repoE.getAllEntradas());
    }

    @Test
    public void testRegistrarEntrada() {
        repoE.addEntrada("123ABC", LocalDateTime.now());
        assertNotNull(repoE.getEntradasByVehiculoAndFecha("123ABC", LocalDateTime.now().toLocalDate())); //Ojo con
        //el rango de fechas
    }
}