
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AirplaneIntegrationTest {

    @Before
    public void setUp() {
        FlightCollection.getFlights().clear();
    }

    @Test
    public void testFlightBookingProcess() {
        // Step 1: Create airplanes
        Airplane airplane1 = new Airplane(1, "Boeing 737", 20, 150, 10);
        Airplane airplane2 = new Airplane(2, "Airbus A320", 25, 160, 12);

        // Step 2: Create flights
        Timestamp dateFrom1 = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo1 = Timestamp.valueOf("2024-07-15 18:30:00");
        Flight flight1 = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom1, dateTo1, airplane1);

        Timestamp dateFrom2 = Timestamp.valueOf("2024-07-16 10:00:00");
        Timestamp dateTo2 = Timestamp.valueOf("2024-07-16 14:00:00");
        Flight flight2 = new Flight(2, "Paris", "Berlin", "PAR123", "Air France", dateFrom2, dateTo2, airplane2);

        // Step 3: Add flights to FlightCollection
        ArrayList<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight1);
        flightsToAdd.add(flight2);
        FlightCollection.addFlights(flightsToAdd);

        // Step 4: Query flights by cities
        Flight resultFlight1 = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(resultFlight1);
        assertEquals(flight1, resultFlight1);

        Flight resultFlight2 = FlightCollection.getFlightInfo("Paris", "Berlin");
        assertNotNull(resultFlight2);
        assertEquals(flight2, resultFlight2);

        // Step 5: Query flights by single city
        Flight resultFlightByCity = FlightCollection.getFlightInfo("New York");
        assertNotNull(resultFlightByCity);
        assertEquals(flight1, resultFlightByCity);

        // Step 6: Query flight by ID
        Flight resultFlightByID = FlightCollection.getFlightInfo(1);
        assertNotNull(resultFlightByID);
        assertEquals(flight1, resultFlightByID);

        // Step 7: Update flight information
        resultFlight1.setCompany("United Airlines");
        assertEquals("United Airlines", resultFlight1.getCompany());

        // Step 8: Verify the update is reflected in the collection
        Flight updatedFlight = FlightCollection.getFlightInfo(1);
        assertNotNull(updatedFlight);
        assertEquals("United Airlines", updatedFlight.getCompany());
    }
}
