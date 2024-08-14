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
        passenger.setFirstName("John");
        passenger.setSecondName("Doe");
        passenger.setAge(30);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
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
    public void testFlightWithNegativeID() {
        flight.setFlightID(-1);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testFlightWithNullAirplane() {
        flight.setAirplane(null);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testValidTicketInformation() {
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidTicketInformation() {
        ticket.setPrice_k(-100);
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
    public void testTicketWithZeroPrice() {
        ticket.setPrice_k(0);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithNullFlight() {
        ticket.setFlight(null);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testValidPassengerInformation() {
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertTrue(isValid);
    }

    @Test
    public void testPassengerWithNullEmail() {
        passenger.setEmail_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptyCardNumber() {
        passenger.setCardNumber("");
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
    public void testTicketPriceDisplay() {
        // Set up the ticket with base price
        ticket.setPrice_k(500);

        // Scenario 1: Passenger is an adult (age 30), no discount should be applied
        passenger.setAge(30);
        int finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(560, finalPrice);

        // Scenario 2: Passenger is a child (age 10), 50% discount should be applied
        passenger.setAge(10);
        ticket.setPrice_k(500); // Reset price before applying discount
        finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(280, finalPrice);

        // Scenario 3: Passenger is an elder (age 65), 100% discount should be applied
        passenger.setAge(65);
        ticket.setPrice_k(500); // Reset price before applying discount
        finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(0, finalPrice);

        // Scenario 4: Passenger is a teenager (age 16), no discount should be applied
        passenger.setAge(16);
        ticket.setPrice_k(500); // Reset price before applying discount
        finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(560, finalPrice);
    }

    @Test
    public void testInvalidFlightID() {
        flight.setFlightID(-1);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightID1() {
        flight.setFlightID(0);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightDepartTo() {
        flight.setDepartTo("");
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightDepartFrom() {
        flight.setDepartFrom("");
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightCode() {
        flight.setCode("");
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightCompany() {
        flight.setCompany("");
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightDateFrom() {
        flight.setDateFrom(null);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightDateTo() {
        flight.setDateTo(null);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidFlightAirplane() {
        flight.setAirplane(null);
        boolean isValid = ticketSystem.validateFlight(flight);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidPassengerEmail() {
        passenger.setEmail_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidPassengerCardNumber() {
        passenger.setCardNumber("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidPassengerSecurityCode() {
        passenger.setSecurityCode(0);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidTicketFlight() {
        ticket.setFlight(null);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidTicketPassenger() {
        ticket.setPassenger(null);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptyFirstName() {
        passenger.setFirstName_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullFirstName() {
        passenger.setFirstName_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptySecondName() {
        passenger.setSecondName_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullSecondName() {
        passenger.setSecondName_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNegativeAge() {
        passenger.setAge(-1);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithZeroAge() {
        passenger.setAge(0);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptyGender() {
        passenger.setGender_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullGender() {
        passenger.setGender_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptyPhoneNumber() {
        passenger.setPhoneNumber_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullPhoneNumber() {
        passenger.setPhoneNumber_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithEmptyPassport() {
        passenger.setPassport_k("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullPassport() {
        passenger.setPassport_k(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithNullCardNumber() {
        passenger.setCardNumber(null);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test
    public void testPassengerWithInvalidSecurityCode() {
        passenger.setSecurityCode(-1);
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }


    @Test
    public void testTicketWithInvalidID() {
        ticket.setTicketId(-1);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithZeroID() {
        ticket.setTicketId(0);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithNegativePrice() {
        ticket.setPrice_k(-1);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithInvalidFlight() {
        flight.setFlightID(-1);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithNullPassenger() {
        ticket.setPassenger(null);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }

    @Test
    public void testTicketWithInvalidPassenger() {
        passenger.setFirstName_k(null);
        boolean isValid = ticketSystem.validateTicket(ticket);
        assertFalse(isValid);
    }
}
