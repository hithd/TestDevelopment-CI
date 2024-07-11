
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    @Test
    public void testValidAirplane() {
        assertDoesNotThrow(() -> new Airplane(1, "Boeing 737", 20, 150, 10));
        assertDoesNotThrow(() -> new Airplane(2, "Boeing 747", 25, 160, 12));
    }

    @Test
    public void testInvalidAirplaneID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(0, "Boeing 737", 20, 150, 10));
        assertEquals("Airplane ID must be in the range [1, 20].", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(21, "Boeing 737", 20, 150, 10));
        assertEquals("Airplane ID must be in the range [1, 20].", exception.getMessage());
    }

    @Test
    public void testInvalidAirplaneModel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "SSR A320", 20, 150, 10));
        assertEquals("Invalid Airplane model.", exception.getMessage());
    }

    @Test
    public void testInvalidBusinessSitsNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 0, 150, 10));
        assertEquals("Business seats number must be in the range [1, 300].", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 301, 150, 10));
        assertEquals("Business seats number must be in the range [1, 300].", exception.getMessage());
    }

    @Test
    public void testInvalidEconomySitsNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 0, 10));
        assertEquals("Economy seats number must be in the range [1, 300].", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 301, 10));
        assertEquals("Economy seats number must be in the range [1, 300].", exception.getMessage());
    }

    @Test
    public void testInvalidCrewSitsNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 150, 0));
        assertEquals("Crew seats number must be in the range [1, 300].", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Airplane(1, "Boeing 737", 20, 150, 301));
        assertEquals("Crew seats number must be in the range [1, 300].", exception.getMessage());
    }
}
