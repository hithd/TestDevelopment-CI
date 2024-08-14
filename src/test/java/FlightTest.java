import org.junit.Before;
import org.junit.Test;
import java.sql.Timestamp;
import static org.junit.Assert.*;

public class FlightTest {

    private Airplane defaultAirplane;
    private Timestamp dateFrom;
    private Timestamp dateTo;

    @Before
    public void setUp() {
        defaultAirplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        dateTo = Timestamp.valueOf("2024-07-15 18:30:00");
    }

    @Test
    public void testFlightCreationWithStatus() {
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled");

        assertNotNull(flight);
        assertEquals(1, flight.getFlightID());
        assertEquals("New York", flight.getDepartTo());
        assertEquals("Los Angeles", flight.getDepartFrom());
        assertEquals("NYC123", flight.getCode());
        assertEquals("Delta", flight.getCompany());
        assertEquals(dateFrom, flight.getDateFrom());
        assertEquals(dateTo, flight.getDateTo());
        assertEquals(defaultAirplane, flight.getAirplane());
        assertEquals("Scheduled", flight.getStatus());
    }

    @Test
    public void testSetFlightStatus() {
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled");

        flight.setStatus("Completed");
        assertEquals("Completed", flight.getStatus());

        assertThrows(IllegalArgumentException.class, () -> flight.setStatus(null)); // 测试设置为null
        assertThrows(IllegalArgumentException.class, () -> flight.setStatus("")); // 测试设置为空字符串
    }

    @Test
    public void testFlightCreationSuccess() {
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled");

        assertNotNull(flight);
        assertEquals(1, flight.getFlightID());
        assertEquals("New York", flight.getDepartTo());
        assertEquals("Los Angeles", flight.getDepartFrom());
        assertEquals("NYC123", flight.getCode());
        assertEquals("Delta", flight.getCompany());
        assertEquals(defaultAirplane, flight.getAirplane());
        assertEquals(dateFrom, flight.getDateFrom());
        assertEquals(dateTo, flight.getDateTo());
        assertEquals("Scheduled", flight.getStatus());
    }

    @Test
    public void testFlightCreationWithNullFields() {
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, null, "Los Angeles", "LAX123", "American Airlines", dateFrom, dateTo, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", null, "LAX123", "American Airlines", dateFrom, dateTo, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", "Los Angeles", null, "American Airlines", dateFrom, dateTo, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", "Los Angeles", "LAX123", null, dateFrom, dateTo, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", "Los Angeles", "LAX123", "American Airlines", null, dateFrom, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", "Los Angeles", "LAX123", "American Airlines", dateFrom, null, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "New York", "Los Angeles", "LAX123", "American Airlines", dateFrom, dateTo, defaultAirplane, null));
    }

    @Test
    public void testFlightCreationWithInvalidCity() {
        assertThrows(IllegalArgumentException.class, () -> new Flight(4, "InvalidCity", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled"));
        assertThrows(IllegalArgumentException.class, () -> new Flight(4, "New York", "InvalidCity", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled"));
    }

    @Test
    public void testFlightCreationWithSameCity() {
        assertThrows(IllegalArgumentException.class, () -> new Flight(5, "New York", "New York", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled"));
    }

    @Test
    public void testFlightCreationWithInvalidDates() {
        Timestamp invalidDateFrom = Timestamp.valueOf("2024-07-15 18:30:00");
        Timestamp invalidDateTo = Timestamp.valueOf("2024-07-15 14:30:00"); // dateFrom is after dateTo

        assertThrows(IllegalArgumentException.class, () -> new Flight(6, "New York", "Los Angeles", "NYC123", "Delta", invalidDateFrom, invalidDateTo, defaultAirplane, "Scheduled"));
    }

    @Test
    public void testSetAndGetFields() {
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled");

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

        flight.setStatus("Delayed");
        assertEquals("Delayed", flight.getStatus());
    }

    @Test
    public void testValidCities() {
        assertTrue(Flight.getValidCities().contains("New York"));
        assertTrue(Flight.getValidCities().contains("Los Angeles"));
        assertFalse(Flight.getValidCities().contains("InvalidCity"));
    }

    @Test
    public void testToString() {
        Flight flight = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom, dateTo, defaultAirplane, "Scheduled");

        String expected = "Flight{flightID=1, departTo='New York', departFrom='Los Angeles', code='NYC123', company='Delta', dateFrom=2024-07-15 14:30:00.0, dateTo=2024-07-15 18:30:00.0, airplane=Airplane{ID=1, model=Boeing 737, business sits=20, economy sits=150, crew sits=10, total sits=180}, status='Scheduled'}";
        assertEquals(expected, flight.toString());
    }
}
