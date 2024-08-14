import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AirplaneTest {

    @Test
    public void testValidAirplane() {
        Airplane airplane1 = new Airplane(1, "Boeing 737", 20, 150, 10);
        assertEquals(180, airplane1.getTotalSeatsNumber());

        Airplane airplane2 = new Airplane(2, "Boeing 747", 25, 160, 15);
        assertEquals(200, airplane2.getTotalSeatsNumber());
    }

    @Test
    public void testInvalidAirplaneID() {
        assertThrows(IllegalArgumentException.class, () -> new Airplane(0, "Boeing 737", 20, 150, 10));
        assertThrows(IllegalArgumentException.class, () -> new Airplane(21, "Boeing 737", 20, 150, 10));
    }

    @Test
    public void testInvalidAirplaneModel() {
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "SSR A320", 20, 150, 10));
    }

    @Test
    public void testInvalidBusinessSitsNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 0, 150, 10));
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 301, 150, 10));
    }

    @Test
    public void testInvalidEconomySitsNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 301, 10));
    }

    @Test
    public void testInvalidCrewSitsNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 150, 40)); // 过多的机组人员座位

        // 检查边界值，例如最大允许的机组人员座位数
        assertDoesNotThrow(() -> new Airplane(1, "Boeing 737", 20, 150, 17));
        assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 150, 18));
    }

    @Test
    public void testGetAirPlaneInfo() {
        Airplane airplane1 = Airplane.getAirPlaneInfo(1);
        assertEquals("Boeing 737", airplane1.getAirplaneModel());
        assertEquals(20, airplane1.getBusinessSitsNumber());
        assertEquals(150, airplane1.getEconomySitsNumber());
        assertEquals(15, airplane1.getCrewSitsNumber());
        assertEquals(185, airplane1.getTotalSeatsNumber());

        assertThrows(IllegalArgumentException.class, () -> Airplane.getAirPlaneInfo(999)); // 测试一个不存在的ID
    }

    @Test

    public void testEdgeCases() {
        // 测试最小值和最大值边界条件
        assertDoesNotThrow(() -> new Airplane(1, "Boeing 737", 1, 1, 1));
        assertDoesNotThrow(() -> new Airplane(20, "Boeing 747", 300, 300, 60)); // 假设允许的最大值
    }

    @Test
    public void testToString() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 150, 10);
        String expected = "Airplane{ID=1, model=Boeing 737, business sits=20, economy sits=150, crew sits=10, total sits=180}";
        assertEquals(expected, airplane.toString());
    }
}
