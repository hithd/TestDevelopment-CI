//The author of this module code is Jianxin Zhou

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public class FlightCollectionTest {

    @Before
    public void setUp() {
        FlightCollection.getFlights().clear();
    }

    @Test
    public void testAddFlightsSuccess() {
        Airplane airplane1 = new Airplane(1, "Boeing 737", 20, 150, 10);
        Airplane airplane2 = new Airplane(2, "Boeing 737", 25, 160, 12);

        Timestamp dateFrom1 = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo1 = Timestamp.valueOf("2024-07-15 18:30:00");
        Timestamp dateFrom2 = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo2 = Timestamp.valueOf("2024-07-15 18:30:00");

        Flight flight1 = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom1, dateTo1, airplane1);
        Flight flight2 = new Flight(2, "Paris", "Berlin", "PAR123", "Air France", dateFrom2, dateTo2, airplane2);

        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight1);
        flightsToAdd.add(flight2);

        FlightCollection.addFlights(flightsToAdd);
        assertEquals(2, FlightCollection.getFlights().size());
    }

    @Test
    public void testAddFlightsWithInvalidCity() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");

        try {
            new Flight(1, "InvalidCity", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid city name.", e.getMessage());
        }
    }

    @Test
    public void testGetFlightInfoByCities() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight);
        FlightCollection.addFlights(flightsToAdd);

        Flight result = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(result);
        assertEquals(flight, result);
    }

    @Test
    public void testGetFlightInfoByCity() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight);
        FlightCollection.addFlights(flightsToAdd);

        Flight result = FlightCollection.getFlightInfo("New York");
        assertNotNull(result);
        assertEquals(flight, result);
    }

    @Test
    public void testGetFlightInfoById() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, airplane);

        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight);
        FlightCollection.addFlights(flightsToAdd);

        Flight result = FlightCollection.getFlightInfo(1);
        assertNotNull(result);
        assertEquals(flight, result);
    }
}
