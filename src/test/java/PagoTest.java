
import static org.junit.Assert.*;

import java.beans.Transient;
import java.math.BigDecimal;
import org.junit.Test;

import es.uca.GaviotaPK.GaviotaParking.Abono;
import es.uca.GaviotaPK.GaviotaParking.AbonoMensual;
import es.uca.GaviotaPK.GaviotaParking.AbonoTrimestral;
import es.uca.GaviotaPK.GaviotaParking.AbonoAnual;
import es.uca.GaviotaPK.GaviotaParking.Pago;
import es.uca.GaviotaPK.GaviotaParking.Tarifa;
import es.uca.GaviotaPK.GaviotaParking.Ticket;
import es.uca.GaviotaPK.GaviotaParking.Vehiculo;
import es.uca.GaviotaPK.GaviotaParking.Cliente;

import org.junit.Before;
import org.junit.After;


public class PagoTest {
    private Cliente cliente;
    private Ticket ticket;
    private Tarifa tarifa;
    private Vehiculo vehiculo;
    private AbonoMensual abonoMensual;
    private AbonoTrimestral abonoTrimestral;
    private Abono abonoAnual;

    @Before
    public void setUp() {
        // Configuración previa a los tests
        vehiculo = new Vehiculo("ABC123");
        cliente = new Cliente("Marcos", "12345978f", vehiculo);
        ticket = new Ticket(vehiculo);
        tarifa = new Tarifa("TarifaEstandar", BigDecimal.valueOf(2.50), BigDecimal.valueOf(20.00),BigDecimal.valueOf(50.00),BigDecimal.valueOf(120.00),BigDecimal.valueOf(400.00));
        abonoMensual = new AbonoMensual(BigDecimal.valueOf(50), vehiculo);
        abonoTrimestral = new AbonoTrimestral(BigDecimal.valueOf(120), vehiculo);
        abonoAnual = new AbonoAnual(BigDecimal.valueOf(400), vehiculo);
    
    }

    @After
    public void tearDown() {
        // Limpiar después de los tests
        cliente = null;
        ticket = null;
        tarifa = null;
        vehiculo = null;
        abonoMensual = null;
        abonoTrimestral = null;
        abonoAnual = null;
    }

    @Test
    public void testPagoConValorYCliente() {
        // Crear un pago con valor y cliente
        Pago pago = new Pago(BigDecimal.valueOf(20), cliente);
        
        // Comprobar que el pago se ha creado correctamente
        assertNotNull(pago);
        assertEquals(BigDecimal.valueOf(20), pago.getValor());
        assertNotNull(pago.getFecha());
        assertEquals(cliente, pago.getCliente());
    }

    @Test
    public void testPagoAbonoMensual() {
        // Crear un pago con abono mensual
        Pago pago = new Pago(abonoMensual, cliente, vehiculo);

        // Comprobar que el pago se ha creado correctamente
        assertNotNull(pago);
        assertEquals(abonoMensual.getPrecio(), pago.getValor());
        assertNotNull(pago.getFecha());
        assertEquals(cliente, pago.getCliente());
    }

    @Test
    public void testPagoAbonoTrimestral() {
        // Crear un pago con abono trimestral
        Pago pago = new Pago(abonoTrimestral, cliente, vehiculo);

        // Comprobar que el pago se ha creado correctamente
        assertNotNull(pago);
        assertEquals(abonoTrimestral.getPrecio(), pago.getValor());
        assertNotNull(pago.getFecha());
        assertEquals(cliente, pago.getCliente());
    }

    @Test
    public void testPagoAbonoAnual() {
        // Crear un pago con abono anual
        Pago pago = new Pago(abonoAnual, cliente, vehiculo);

        // Comprobar que el pago se ha creado correctamente
        assertNotNull(pago);
        assertEquals(abonoAnual.getPrecio(), pago.getValor());
        assertNotNull(pago.getFecha());
        assertEquals(cliente, pago.getCliente());
    }

    @Test
    public void testPagoTicket() {
        // Crear un pago con ticket
        Pago pago = new Pago(ticket, cliente, tarifa);

        // Comprobar que el pago se ha creado correctamente
        assertNotNull(pago);
        assertNotNull(pago.getValor());
        assertNotNull(pago.getFecha());
        assertEquals(cliente, pago.getCliente());
    }
}