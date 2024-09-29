
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Ticket;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    private Vehiculo coche;

    @Before
    public void setUp() {
        coche = new Vehiculo("6533CRD");
        ticket = new Ticket(coche);
    }

    @After
    public void tearDown() {
        ticket = null;
    }

    @Test
    public void testgetIdTicket() {
        assertNotNull(ticket.getId());
    }

    @Test
    public void testgetCodigoQR() {
        assertNotNull(ticket.getCodigo());
    }

    @Test
    public void testgetFechaEntrada() {
        assertNotNull(ticket.getFechaEntrada());
    }

    @Test
    public void testgetDias() {
        LocalDateTime fechaActual = LocalDateTime.now();
        int dias = ticket.getDias(fechaActual);
        assertEquals(0, dias); // Se espera que el ticket tenga 0 días de antigüedad
    }

    @Test
    public void testgetMatricula() {
        assertEquals("6533CRD", ticket.getVehiculo().getMatricula());
    }

    @Test
    public void testEstaActivo() {
        assertTrue(ticket.estaActivo());
        ticket.desactivar();
        assertFalse(ticket.estaActivo());
    }

    @Test
    public void testDesactivar() {
        assertTrue(ticket.estaActivo());
        ticket.desactivar();
        assertFalse(ticket.estaActivo());
    }

    @Test
    public void testToString() {
        String expectedString = "ID del Ticket: " + ticket.getId() + ", Código QR: " + ticket.getCodigo() + ", Fecha de Entrada: " + ticket.getFechaEntrada() + ", Matrícula: " + ticket.getVehiculo().getMatricula();
        assertEquals(expectedString, ticket.toString());
    }
}
