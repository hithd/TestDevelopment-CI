//The author of this module code is Kaihua Tian

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TicketSystemTest {
    private Passenger passengernull;
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
        ticket = new Ticket();
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
        ticket.setPrice(0);
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
        passengernull = new Passenger();
        passengernull.setFirstName("Juju");
        passengernull.setSecondName("Bond");
        passengernull.setAge(30);
        passengernull.setGender("Woman");
        boolean isValid = ticketSystem.validatePassenger(passengernull);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithNullFirstName() {
        passengernull = new Passenger();
        passengernull.setSecondName("Bond");
        passengernull.setAge(30);
        passengernull.setGender("Woman");
        boolean isValid = ticketSystem.validatePassenger(passengernull);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithNullSecondName() {
        passengernull = new Passenger();
        passengernull.setFirstName("Juju");
        passengernull.setAge(30);
        passengernull.setGender("Woman");
        boolean isValid = ticketSystem.validatePassenger(passengernull);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithNullGender() {
        passengernull = new Passenger();
        passengernull.setFirstName("Juju");
        passengernull.setSecondName("Bond");
        passengernull.setAge(30);
        boolean isValid = ticketSystem.validatePassenger(passengernull);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithInvalidSecurityCode() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setSecondName("Brown");
        passenger.setAge(35);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("1234567812345678");
        //passenger.setSecurityCode(123); // 设置为 0 以触发错误
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithInvalidPhoneNumber() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setSecondName("Brown");
        passenger.setAge(35);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        //passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        passenger.setCardNumber("1234567812345678");
        passenger.setSecurityCode(123); // 设置为 0 以触发错误
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithInvalidPassport() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setSecondName("Brown");
        passenger.setAge(35);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        // passenger.setPassport("123456789");
        passenger.setCardNumber("1234567812345678");
        passenger.setSecurityCode(123); // 设置为 0 以触发错误
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }
    @Test
    public void testPassengerWithInvalidCardNumber() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setSecondName("Brown");
        passenger.setAge(35);
        passenger.setGender("Man");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhoneNumber("0412345678");
        passenger.setPassport("123456789");
        //passenger.setCardNumber("1234567812345678");
        passenger.setSecurityCode(123); // 设置为 0 以触发错误
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }



    @Test(expected = IllegalArgumentException.class)
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
        ticket.setPrice(500);

        // Scenario 1: Passenger is an adult (age 30), no discount should be applied
        passenger.setAge(30);
        int finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(560, finalPrice);

        // Scenario 2: Passenger is a child (age 10), 50% discount should be applied
        passenger.setAge(10);
        ticket.setPrice(500); // Reset price before applying discount
        finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(280, finalPrice);

        // Scenario 3: Passenger is an elder (age 65), 100% discount should be applied
        passenger.setAge(65);
        ticket.setPrice(500); // Reset price before applying discount
        finalPrice = ticketSystem.calculateFinalPrice(ticket);
        assertEquals(0, finalPrice);

        // Scenario 4: Passenger is a teenager (age 16), no discount should be applied
        passenger.setAge(16);
        ticket.setPrice(500); // Reset price before applying discount
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



    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassengerCardNumber() {
        passenger.setCardNumber("");
        boolean isValid = ticketSystem.validatePassenger(passenger);
        assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
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




    @Test(expected = IllegalArgumentException.class)
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



    @Test(expected = IllegalArgumentException.class)
    public void testPassengerWithNullCardNumber() {
        passenger.setCardNumber(null);
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


}
