
import static org.junit.Assert.*;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Abono;
import es.uca.GaviotaPK.GaviotaParking.AbonoAnual;
import es.uca.GaviotaPK.GaviotaParking.AbonoMensual;
import es.uca.GaviotaPK.GaviotaParking.Ticket;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import org.junit.Before;
import org.junit.After;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehiculoTest {
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        vehiculo = new Vehiculo("ABC123");
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        vehiculo = null;
    }

    @Test
    public void testGetMatricula() {
        // Comprobar que se obtiene la matrícula correctamente
        assertEquals("ABC123", vehiculo.getMatricula());
    }

    @Test
    public void testGetAbono_SinAbono() {
        // Comprobar que inicialmente el vehículo no tiene un abono asignado
        assertNull(vehiculo.getAbono());
    }

    @Test
    public void testGetTicket_SinTicket() {
        // Comprobar que inicialmente el vehículo no tiene un ticket asignado
        assertNull(vehiculo.getTicket());
    }

    @Test
    public void testsetTicket() {
        // Asignar un ticket al vehículo y comprobar que se ha asignado correctamente
        Ticket ticket = new Ticket(vehiculo);
        vehiculo.setTicket(ticket);
        assertEquals(ticket, vehiculo.getTicket());
    }

    @Test
    public void testaddAbono() {
        // Asignar un abono al vehículo y comprobar que se ha asignado correctamente
        Abono abono = new AbonoMensual(BigDecimal.valueOf(50), vehiculo);
        vehiculo.setAbonoActual(abono);
        assertEquals(abono, vehiculo.getAbono());
    }

    @Test
    public void testEstaAbonado_SinAbono() {
        // Comprobar que el vehículo no está abonado cuando no tiene un abono asignado
        assertFalse(vehiculo.estaAbonado());
    }

    @Test
    public void testEstaAbonado_AbonoCaducado() {
        // Asignar un abono caducado al vehículo y comprobar que no está abonado
        Abono abonoCaducado = new AbonoMensual(BigDecimal.valueOf(50), vehiculo);
        abonoCaducado.setFechaCaducidad(LocalDateTime.now().minusDays(1));
        vehiculo.setAbonoActual(abonoCaducado);
        assertFalse(vehiculo.estaAbonado());
    }

    @Test
    public void testEstaAbonado_AbonoActivo() {
        // Asignar un abono activo al vehículo y comprobar que está abonado
        Abono abonoActivo = new AbonoAnual(BigDecimal.valueOf(500), vehiculo);
        vehiculo.setAbonoActual(abonoActivo);
        assertTrue(vehiculo.estaAbonado());
    }
}