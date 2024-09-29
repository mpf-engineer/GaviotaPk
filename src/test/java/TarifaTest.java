import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Tarifa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TarifaTest {
    private Tarifa tarifa;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        tarifa = new Tarifa("Tarifa", BigDecimal.valueOf(2.50),BigDecimal.valueOf(20.00),BigDecimal.valueOf(50.00),BigDecimal.valueOf(120.00), BigDecimal.valueOf(400.00));
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        tarifa = null;
    }

    @Test
    public void testGetCostePorHora() {
        // Comprobar que se obtiene el coste por hora correctamente
        assertEquals(BigDecimal.valueOf(2.50), tarifa.getCostePorHora());
    }

    @Test
    public void testGetCostePorDia() {
        // Comprobar que se obtiene el coste por día correctamente
        assertEquals(BigDecimal.valueOf(20.00), tarifa.getCostePorDia());
    }

    @Test
    public void testgetCosteAbonoMensual() {
        // Comprobar que se obtiene el coste del abono mensual correctamente
        assertEquals(BigDecimal.valueOf(50.00), tarifa.getCosteAbonoMensual());
    }

    @Test
    public void testGetCosteAbonoTrimestral() {
        // Comprobar que se obtiene el coste del abono trimestral correctamente
        assertEquals(BigDecimal.valueOf(120.00), tarifa.getCosteAbonoTrimestral());
    }

    @Test
    public void testGetCosteAbonoAnual() {
        // Comprobar que se obtiene el coste del abono anual correctamente
        assertEquals(BigDecimal.valueOf(400.00), tarifa.getCosteAbonoAnual());
    }

    @Test
    public void testCalcularPrecioHora() {
        // Comprobar que se calcula correctamente el precio por hora
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 4, 10, 10, 0); // Hora de entrada
        LocalDateTime horaSalida = LocalDateTime.of(2024, 4, 10, 12, 30); // Hora de salida
        BigDecimal precioEsperado = BigDecimal.valueOf(5.0); 
        assertEquals(precioEsperado, tarifa.calcularPrecioHora(horaEntrada, horaSalida));
    }

    @Test
    public void testCalcularPrecioDia() {
        // Comprobar que se calcula correctamente el precio por día
        int dias = 3; // 3 días de estacionamiento
        BigDecimal precioEsperado = BigDecimal.valueOf(60.00); // Precio esperado para 3 días a 20 por día
        assertEquals(precioEsperado, tarifa.calcularPrecioDia(dias));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPrecioHora_ExceptionHoraSalidaAntes() {
        // Comprobar que se lanza una excepción si la hora de salida es anterior a la hora de entrada
        LocalDateTime horaEntrada = LocalDateTime.of(2024, 4, 10, 12, 0); // Hora de entrada
        LocalDateTime horaSalida = LocalDateTime.of(2024, 4, 10, 10, 0); // Hora de salida (anterior a la de entrada)
        tarifa.calcularPrecioHora(horaEntrada, horaSalida);
    }
}