//The author of this module code is Kaihua Tian

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TicketSystemTest {

    private TicketSystem ticketSystem;
    private Flight flight;
    private Ticket ticket;
    private Passenger passenger;
    private Airplane airplane;

    @Before
    public void setUp() {
        ticketSystem = new TicketSystem();
        airplane = new Airplane();
        passenger = new Passenger();
        passenger.setFirstName("Kah");
        passenger.setSecondName("Ronaldo");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("Kah.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("1234567812345678");
        passenger.setSecurityCode(123);

        flight = new Flight(1, "New York", "Los Angeles", "NY123", "Delta", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 100000), airplane);
        ticket = new Ticket(1, 500, flight, false, passenger);

        // Initialize the TicketCollection and add the test ticket
        TicketCollection.tickets = new ArrayList<>();
        TicketCollection.tickets.add(ticket);
    }

    @Test
    public void testValidFlightInformation() {

        boolean isValid = ticketSystem.validateFlight(flight);

        assertTrue(isValid);
    }

    @Test
    public void testInvalidFlightInformation() {

        flight.setDepartTo("");

        boolean isValid = ticketSystem.validateFlight(flight);

        assertFalse(isValid);
    }

    @Test
    public void testIncompleteFlightInformation() {

        flight.setDateFrom(null);

        boolean isValid = ticketSystem.validateFlight(flight);

        assertFalse(isValid);
    }

    @Test
    public void testValidTicketInformation() {

        boolean isValid = ticketSystem.validateTicket(ticket);

        assertTrue(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTicketInformation() {

        ticket.setPrice(-100);

        boolean isValid = ticketSystem.validateTicket(ticket);

        assertFalse(isValid);
    }

    @Test
    public void testIncompleteTicketInformation() {

        ticket.setPassenger(null);

        boolean isValid = ticketSystem.validateTicket(ticket);

        assertFalse(isValid);
    }

    @Test
    public void testValidPassengerInformation() {
        passenger.setFirstName("Kah");
        passenger.setSecondName("Ronaldo");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("Kah.Ronaldo@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        boolean isValid = ticketSystem.validatePassenger(passenger);

        assertTrue(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassengerInformation() {
        // Set up the passenger with invalid information (missing first name)
        passenger.setFirstName("");
        passenger.setSecondName("Ronaldo");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("Kah.Ronaldo@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        boolean isValid = ticketSystem.validatePassenger(passenger);

        assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncompletePassengerInformation() {
        // Set up the passenger with incomplete information (missing age)
        passenger.setFirstName("Kah");
        passenger.setSecondName("Ronaldo");
        passenger.setAge(-1);
        passenger.setGender("Man");
        passenger.setEmail("Kah.Ronaldo@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        boolean isValid = ticketSystem.validatePassenger(passenger);

        assertFalse(isValid);
    }

    @Test
    public void testValidCitySelection() {

        boolean isValidCity = ticketSystem.validateCity("New York");

        assertTrue(isValidCity);
    }

    @Test
    public void testInvalidCitySelection() {

        boolean isValidCity = ticketSystem.validateCity("");

        assertFalse(isValidCity);
    }

    @Test
    public void testBookedTicketErrorMessage() throws Exception {
        // Simulate a booked ticket
        ticket.setTicketStatus(true);

        // Mock the TicketCollection to return the booked ticket
        TicketCollection.tickets.set(0, ticket);

        // Attempt to book the already booked ticket
        String result = ticketSystem.buyTicketAndValidate(ticket.getTicket_id());

        assertEquals("This ticket is already booked.", result);
    }

    @Test
    public void testValidTicketBookingMessage() throws Exception {
        // Simulate an available ticket
        ticket.setTicketStatus(false);

        // Mock the TicketCollection to return the available ticket
        TicketCollection.tickets.set(0, ticket);

        // Prepare the simulated user input
        String input = "Kah\nRonaldo\n30\nMan\nKah.Ronaldo@example.com\n0412345678\n123456789\n1\n1234567812345678\n123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ticketSystem.setScanner(new Scanner(in));

        // Attempt to book the available ticket
        String result = ticketSystem.buyTicketAndValidate(ticket.getTicket_id());

        assertEquals("Ticket booked successfully.", result);
    }

    @Test
    public void testTicketPriceDisplay() {
        // Calculate the final price of the ticket
        int finalPrice = ticketSystem.calculateFinalPrice(ticket);

        assertEquals(560, finalPrice);
    }
}
