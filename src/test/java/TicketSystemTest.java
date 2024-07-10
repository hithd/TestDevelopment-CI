package org.example;

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
//        passenger.setFirstName("John");
//        passenger.setSecondName("Doe");
//        passenger.setAge(30);
//        passenger.setGender("Man");
//        passenger.setEmail("john.doe@example.com");
//        passenger.setPhoneNumber("0412345678");
//        passenger.setPassport("123456789");
//        passenger.setCardNumber("1234567812345678");
//        passenger.setSecurityCode(123);

        flight = new Flight(1, "New York", "Los Angeles", "NY123", "Delta", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 100000), airplane);
        ticket = new Ticket(1, 1000, flight, false, passenger);

        // Initialize the TicketCollection and add the test ticket
        TicketCollection.tickets = new ArrayList<>();
        TicketCollection.tickets.add(ticket);
    }

    @Test
    public void testValidFlightInformation() {
        // Validate flight information
        boolean isValid = ticketSystem.validateFlight(flight);

        // Assert that the validation returns true for valid information
        assertTrue(isValid);
    }

    @Test
    public void testInvalidFlightInformation() {
        // Set flight with invalid information
        flight.setDepartTo("");

        // Validate flight information
        boolean isValid = ticketSystem.validateFlight(flight);

        // Assert that the validation returns false for invalid information
        assertFalse(isValid);
    }

    @Test
    public void testIncompleteFlightInformation() {
        // Set flight with incomplete information
        flight.setDateFrom(null);

        // Validate flight information
        boolean isValid = ticketSystem.validateFlight(flight);

        // Assert that the validation returns false for incomplete information
        assertFalse(isValid);
    }

    @Test
    public void testValidTicketInformation() {
        // Validate ticket information
        boolean isValid = ticketSystem.validateTicket(ticket);

        // Assert that the validation returns true for valid information
        assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTicketInformation() {

        // Set ticket with invalid information
        ticket.setPrice(-100);

        // Validate ticket information
        boolean isValid = ticketSystem.validateTicket(ticket);

        // Assert that the validation returns false for invalid information
        assertFalse(isValid);
    }

    @Test
    public void testIncompleteTicketInformation() {
        // Set ticket with incomplete information
        ticket.setPassenger(null);

        // Validate ticket information
        boolean isValid = ticketSystem.validateTicket(ticket);

        // Assert that the validation returns false for incomplete information
        assertFalse(isValid);
    }

    @Test
    public void testValidPassengerInformation() {
        // Set up the passenger with valid information
        passenger.setFirstName("John");
        passenger.setSecondName("Doe");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        // Validate passenger information
        boolean isValid = ticketSystem.validatePassenger(passenger);

        // Assert that the validation returns true for valid information
        assertTrue(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassengerInformation() {
        // Set up the passenger with invalid information (missing first name)
        passenger.setFirstName("");
        passenger.setSecondName("Doe");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        // Validate passenger information
        boolean isValid = ticketSystem.validatePassenger(passenger);

        // Assert that the validation returns false for invalid information
        assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncompletePassengerInformation() {
        // Set up the passenger with incomplete information (missing age)
        passenger.setFirstName("John");
        passenger.setSecondName("Doe");
        passenger.setAge(-1);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("4111111111111111");
        passenger.setSecurityCode(123);

        // Validate passenger information
        boolean isValid = ticketSystem.validatePassenger(passenger);

        // Assert that the validation returns false for incomplete information
        assertFalse(isValid);
    }

    @Test
    public void testValidCitySelection() {
        // Validate city selection
        boolean isValidCity = ticketSystem.validateCity("New York");

        // Assert that the validation returns true for valid city
        assertTrue(isValidCity);
    }

    @Test
    public void testInvalidCitySelection() {
        // Validate city selection
        boolean isValidCity = ticketSystem.validateCity("");

        // Assert that the validation returns false for invalid city
        assertFalse(isValidCity);
    }

    @Test
    public void testBookedTicketErrorMessage() throws Exception {
        // Simulate a booked ticket
        ticket.setTicketStatus(true);

        // Mock the TicketCollection to return the booked ticket
        TicketCollection.tickets.set(0, ticket);

        // Attempt to book the already booked ticket
        String result = ticketSystem.buyTicketAndValidate(ticket.getTicketId());

        // Assert that the appropriate error message is returned
        assertEquals("This ticket is already booked.", result);
    }

    @Test
    public void testValidTicketBookingMessage() throws Exception {
        // Simulate an available ticket
        ticket.setTicketStatus(false);

        // Mock the TicketCollection to return the available ticket
        TicketCollection.tickets.set(0, ticket);

        // Prepare the simulated user input
        String input = "John\nDoe\n30\nMan\njohn.doe@example.com\n0412345678\n123456789\n1\n1234567812345678\n123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ticketSystem.setScanner(new Scanner(in));

        // Attempt to book the available ticket
        String result = ticketSystem.buyTicketAndValidate(ticket.getTicketId());

        // Assert that the appropriate success message is returned
        assertEquals("Ticket booked successfully.", result);
    }

    @Test
    public void testTicketPriceDisplay() {
        // Calculate the final price of the ticket
        int finalPrice = ticketSystem.calculateFinalPrice(ticket);

        // Assert that the final price is displayed correctly
        assertEquals(560, finalPrice);
    }
}
