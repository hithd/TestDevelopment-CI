
import org.junit.Test;
import static org.junit.Assert.*;

public class AirplaneTest {

    @Test
    public void testValidAirplane() {
        try {
            new Airplane(1, "Boeing 737", 20, 150, 10);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

        try {
            new Airplane(2, "Boeing 747", 25, 160, 12);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testInvalidAirplaneID() {
        try {
            new Airplane(0, "Boeing 737", 20, 150, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Airplane ID must be in the range [1, 20].", e.getMessage());
        }

        try {
            new Airplane(21, "Boeing 737", 20, 150, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Airplane ID must be in the range [1, 20].", e.getMessage());
        }
    }

    @Test
    public void testInvalidAirplaneModel() {
        try {
            new Airplane(1, "SSR A320", 20, 150, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Airplane model.", e.getMessage());
        }
    }

    @Test
    public void testInvalidBusinessSitsNumber() {
        try {
            new Airplane(1, "Boeing 737", 0, 150, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Business seats number must be in the range [1, 300].", e.getMessage());
        }

        try {
            new Airplane(1, "Boeing 737", 301, 150, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Business seats number must be in the range [1, 300].", e.getMessage());
        }
    }

    @Test
    public void testInvalidEconomySitsNumber() {
        try {
            new Airplane(1, "Boeing 737", 20, 0, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Economy seats number must be in the range [1, 300].", e.getMessage());
        }

        try {
            new Airplane(1, "Boeing 737", 20, 301, 10);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Economy seats number must be in the range [1, 300].", e.getMessage());
        }
    }

    @Test
    public void testInvalidCrewSitsNumber() {
        try {
            new Airplane(1, "Boeing 737", 20, 150, 0);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Crew seats number must be in the range [1, 300].", e.getMessage());
        }

        try {
            new Airplane(1, "Boeing 737", 20, 150, 301);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Crew seats number must be in the range [1, 300].", e.getMessage());
        }
    }
}
