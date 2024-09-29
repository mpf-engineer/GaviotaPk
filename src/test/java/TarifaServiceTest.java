
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.TarifaService;
import es.uca.GaviotaPK.GaviotaParking.MemoryTarifaRepository;
import es.uca.GaviotaPK.GaviotaParking.Tarifa;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.After;
import java.util.List;

public class TarifaServiceTest {
    private TarifaService TarifaService;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        TarifaService = new TarifaService(new MemoryTarifaRepository());
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        TarifaService = null;
    }

    @Test
    public void testGetTarifas() {
        // Comprobar que inicialmente el repositorio de tarifas está vacío
        List<Tarifa> tarifas = TarifaService.getAllTarifas();
        assertTrue(tarifas.isEmpty());
    }

    @Test
    public void testaddTarifa() {
        // Añadir una tarifa al repositorio y comprobar que se ha añadido correctamente
        Tarifa tarifa = new Tarifa("TarifaEstandar",BigDecimal.valueOf(2.50),BigDecimal.valueOf(20.00),BigDecimal.valueOf(50.00),BigDecimal.valueOf(120.00),BigDecimal.valueOf(400.00));
        TarifaService.addTarifa(tarifa);

        List<Tarifa> tarifas = TarifaService.getAllTarifas();
        assertEquals(1, tarifas.size());
        assertEquals(tarifa, tarifas.get(0));
    }

    @Test
    public void testDeleteTarifa() {
        // Añadir una tarifa al repositorio y luego eliminarla
        Tarifa tarifa = new Tarifa("TarifaEstandar", BigDecimal.valueOf(2.50),BigDecimal.valueOf(20.00),BigDecimal.valueOf(50.00),BigDecimal.valueOf(120.00),BigDecimal.valueOf(400.00));
        TarifaService.addTarifa(tarifa);

        List<Tarifa> tarifasAntes = TarifaService.getAllTarifas();
        assertEquals(1, tarifasAntes.size());

        TarifaService.deleteTarifa(tarifa);

        List<Tarifa> tarifasDespues = TarifaService.getAllTarifas();
        assertTrue(tarifasDespues.isEmpty());
    }
}
