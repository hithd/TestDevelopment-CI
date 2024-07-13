import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    private FlightStub flight;
    private Passenger passenger;

    @Before
    public void setUp() {
        flight = new FlightStub(); // 用FlightStub类模拟Flight行为
        passenger = new Passenger();
        passenger.setFirstName("Juju");
        passenger.setSecondName("Bond");
        passenger.setAge(30);
        passenger.setGender("Woman");
    }

    @Test
    public void testTicketStatusValues() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals("False", ticket.getExplicitStatus());
        ticket.setTicketStatus(true);
        assertEquals("True", ticket.getExplicitStatus());
    }

    @Test
    public void testDiscountForChildren() {
        passenger.setAge(10);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(56.0, ticket.getPrice(), 0.01); // 50% discount + 12% tax
    }

    @Test
    public void testDiscountForElders() {
        passenger.setAge(65);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(0.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testNoDiscountForAdults() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // Only 12% tax applied
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPrice() {
        ticket = new Ticket(1, -100.0, flight, false, passenger);
    }

    @Test
    public void testValidPrice() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertTrue(ticket.getPrice() > 0);
    }

    @Test
    public void testServiceTaxApplied() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // 12% tax applied
    }

    @Test
    public void testValidFlightAndPassengerInfo() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertNotNull(ticket.getFlight());
        assertNotNull(ticket.getPassenger());
    }


    @Test(expected = NullPointerException.class)
    public void testNullPassenger() {
        ticket = new Ticket(1, 100.0, flight, false, null);
    }


    @Test
    public void testSettersAndGetters() {
        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTicket_id(2);
        ticket.setPrice(200.0);
        ticket.setFlight(flight);
        ticket.setClassVip(true);
        ticket.setTicketStatus(true);
        ticket.setPassenger(passenger);

        assertEquals(2, ticket.getTicket_id());
        assertEquals(224.0, ticket.getPrice(), 0.01); // 200 + 12% tax

        assertTrue(ticket.getClassVip());
        assertTrue(ticket.ticketStatus());
        assertEquals(passenger, ticket.getPassenger());
    }

    @Test
    public void testToString() {
        ticket = new Ticket(1, 100.0, flight, true, passenger);
        String result = ticket.toString();
        assertTrue(result.contains("Price=112.00 KZT"));
        assertTrue(result.contains("Vip status=true"));
        assertTrue(result.contains("Ticket was purchased=False"));
    }
}