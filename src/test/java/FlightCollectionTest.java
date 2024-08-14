import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightCollectionTest {

    private Airplane airplane1;
    private Airplane airplane2;
    private Timestamp dateFrom1;
    private Timestamp dateTo1;
    private Timestamp dateFrom2;
    private Timestamp dateTo2;
    private Flight flight1;
    private Flight flight2;

    @Before
    public void setUp() {
        FlightCollection.clearFlights(); // 清空航班列表，确保测试独立性

        airplane1 = new Airplane(1, "Boeing 737", 20, 150, 10);
        airplane2 = new Airplane(2, "Airbus A320", 25, 160, 12);

        dateFrom1 = Timestamp.valueOf("2024-07-15 14:30:00");
        dateTo1 = Timestamp.valueOf("2024-07-15 18:30:00");
        dateFrom2 = Timestamp.valueOf("2024-07-16 08:00:00");
        dateTo2 = Timestamp.valueOf("2024-07-16 12:00:00");

        flight1 = new Flight(1, "New York", "Los Angeles", "NYC123", "Delta", dateFrom1, dateTo1, airplane1, "Scheduled");
        flight2 = new Flight(2, "Paris", "Berlin", "PAR123", "Air France", dateFrom2, dateTo2, airplane2, "Completed");
    }

    @Test
    public void testAddFlightsSuccess() {
        List<Flight> flightsToAdd = new ArrayList<>();
        flightsToAdd.add(flight1);
        flightsToAdd.add(flight2);

        FlightCollection.addFlights(flightsToAdd);
        assertEquals(2, FlightCollection.getFlights().size()); // 确认航班列表大小为2
    }

    @Test
    public void testAddFlightsWithDuplicateID() {
        FlightCollection.addFlights(List.of(flight1));

        Flight duplicateFlight = new Flight(1, "Paris", "Berlin", "PAR124", "Air France", dateFrom2, dateTo2, airplane2, "Scheduled");

        assertThrows(IllegalArgumentException.class, () -> FlightCollection.addFlights(List.of(duplicateFlight)));
    }

    @Test


    public void testAddFlightsWithInvalidCity() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-07-15 14:30:00");
        Timestamp dateTo = Timestamp.valueOf("2024-07-15 18:30:00");

        // 首先添加一个有效航班
        Flight validFlight = new Flight(3, "Los Angeles", "New York", "LA123", "Delta", dateFrom, dateTo, airplane, "Scheduled");
        FlightCollection.addFlights(List.of(validFlight));

        // 然后尝试添加一个无效航班
        assertThrows(IllegalArgumentException.class, () -> {
            Flight invalidFlight = new Flight(4, "InvalidCity", "Los Angeles", "INV123", "Invalid Airlines", dateFrom, dateTo, airplane, "Scheduled");
            FlightCollection.addFlights(List.of(invalidFlight));
        });

        // 确保有效航班已被正确添加
        assertEquals(1, FlightCollection.getFlights().size());
    }



    @Test
    public void testGetFlightsByStatus() {
        FlightCollection.addFlights(List.of(flight1, flight2));

        List<Flight> scheduledFlights = FlightCollection.getFlightsByStatus("Scheduled");
        assertEquals(1, scheduledFlights.size());
        assertEquals(flight1, scheduledFlights.get(0));

        List<Flight> completedFlights = FlightCollection.getFlightsByStatus("Completed");
        assertEquals(1, completedFlights.size());
        assertEquals(flight2, completedFlights.get(0));

        // 测试空状态和null状态
        assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightsByStatus(null)); // 测试null状态
        assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightsByStatus(""));   // 测试空字符串状态

        // 添加更多状态的测试
        Flight delayedFlight = new Flight(3, "San Francisco", "Tokyo", "SFO123", "United", dateFrom1, dateTo1, airplane1, "Delayed");
        FlightCollection.addFlights(List.of(delayedFlight));

        List<Flight> delayedFlights = FlightCollection.getFlightsByStatus("Delayed");
        assertEquals(1, delayedFlights.size());
        assertEquals(delayedFlight, delayedFlights.get(0));
    }

    @Test
    public void testGetFlightInfoByCities() {
        FlightCollection.addFlights(List.of(flight1));

        Flight result = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(result);
        assertEquals(flight1, result);

        // 添加测试验证反向城市匹配
        result = FlightCollection.getFlightInfo("Los Angeles", "New York");
        assertNull(result); // 应该返回null，因为方向不同
    }

    @Test
    public void testGetFlightInfoByCity() {
        FlightCollection.addFlights(List.of(flight1));

        Flight result = FlightCollection.getFlightInfo("New York");
        assertNotNull(result);
        assertEquals(flight1, result);

        // 添加测试验证未找到的城市
        result = FlightCollection.getFlightInfo("InvalidCity");
        assertNull(result); // 应该返回null，因为城市不存在
    }

    @Test
    public void testGetFlightInfoById() {
        FlightCollection.addFlights(List.of(flight1));

        Flight result = FlightCollection.getFlightInfo(1);
        assertNotNull(result);
        assertEquals(flight1, result);

        // 测试无效的flightID
        assertNull(FlightCollection.getFlightInfo(999)); // 应返回null，因为ID不存在

        // 测试负数flightID
        assertNull(FlightCollection.getFlightInfo(-1)); // 应返回null，因为ID为负数
    }

    @Test
    public void testGetFlightInfoByNullCity() {
        assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightInfo((String) null));
    }

    @Test
    public void testAddFlightsWithNullFlight() {
        List<Flight> nullFlights = new ArrayList<>();
        nullFlights.add(null); // 添加一个空航班

        assertThrows(IllegalArgumentException.class, () -> FlightCollection.addFlights(nullFlights));
    }
}
