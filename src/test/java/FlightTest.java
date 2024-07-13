//The author of this module code is Jianxin Zhou

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.junit.Assert.*;

public class FlightTest {

    @Before
    public void setUp() {
        // Clear the flights list before each test if FlightCollection class is used
        FlightCollection.getFlights().clear();
    }

    @Test
    public void testFlightCreationSuccess() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        assertNotNull(flight);
        assertEquals(1, flight.getFlightID());
        assertEquals("New York", flight.getDepartTo());
        assertEquals("Los Angeles", flight.getDepartFrom());
        assertEquals("NYC123", flight.getCode());
        assertEquals("Delta", flight.getCompany());
        assertEquals(airplane, flight.getAirplane());
        assertEquals(dateFrom, flight.getDateFrom());
        assertEquals(dateTo, flight.getDateTo());
    }

    @Test
    public void testFlightCreationWithNullFields() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 10:00:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 14:00:00");
        try {
            new Flight(2, null, "Los Angeles", "LAX123", "American Airlines", dateFrom, dateTo, airplane);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("All fields are required.", e.getMessage());
        }
    }

    @Test
    public void testFlightCreationWithInvalidCity() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        try {
            new Flight(4, "InvalidCity", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid city name.", e.getMessage());
        }
    }

    @Test
    public void testFlightCreationWithSameCity() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        try {
            new Flight(5, "New York", "New York", "NYC123", "Delta", dateFrom, dateTo, airplane);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Departure and destination cities cannot be the same.", e.getMessage());
        }
    }

    @Test
    public void testFlightCreationWithInvalidDates() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 18:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 14:30:00");  // dateFrom is after dateTo
        try {
            new Flight(6, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Departure date cannot be after arrival date.", e.getMessage());
        }
    }

    @Test
    public void testSetAndGetFields() throws ParseException {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        flight.setFlightID(2);
        assertEquals(2, flight.getFlightID());

        flight.setDepartTo("San Francisco");
        assertEquals("San Francisco", flight.getDepartTo());

        flight.setDepartFrom("Chicago");
        assertEquals("Chicago", flight.getDepartFrom());

        flight.setCode("SFO456");
        assertEquals("SFO456", flight.getCode());

        flight.setCompany("United");
        assertEquals("United", flight.getCompany());

        Timestamp newDateFrom = Timestamp.valueOf("2024-07-16 08:00:00");
        flight.setDateFrom(newDateFrom);
        assertEquals(newDateFrom, flight.getDateFrom());

        Timestamp newDateTo = Timestamp.valueOf("2024-07-16 12:00:00");
        flight.setDateTo(newDateTo);
        assertEquals(newDateTo, flight.getDateTo());

        Airplane newAirplane = new Airplane(2, "Airbus A320", 25, 160, 12);
        flight.setAirplane(newAirplane);
        assertEquals(newAirplane, flight.getAirplane());
    }

    @Test
    public void testValidCities() {
        assertTrue(Flight.getValidCities().contains("New York"));
        assertTrue(Flight.getValidCities().contains("Los Angeles"));
        assertFalse(Flight.getValidCities().contains("InvalidCity"));
    }

    @Test
    public void testToString() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        String expected = "Flight{flightID=1, departTo='New York', departFrom='Los Angeles', code='NYC123', company='Delta', dateFrom=2024-07-15 14:30:00.0, dateTo=2024-07-15 18:30:00.0, airplane=Airplane{ID=1, model=Boeing 737, business sits=20, economy sits=150, crew sits=10}}";
        assertEquals(expected, flight.toString());
    }
}
