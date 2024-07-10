import org.junit.Test;
import static org.junit.Assert.*;

public class PassengerTest {

    @Test
    public void testValidPassenger() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
        assertEquals("John", passenger.getFirstName());
        assertEquals("Doe", passenger.getSecondName());
        assertEquals(30, passenger.getAge());
        assertEquals("Man", passenger.getGender());
        assertEquals("john@example.com", passenger.getEmail());
        assertEquals("0412345678", passenger.getPhoneNumber());
        assertEquals("A1234567", passenger.getPassport());
        assertEquals("1234567890", passenger.getCardNumber());
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        new Passenger("John", "Doe", 30, "Man", "invalid-email", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPhoneNumber() {
        new Passenger("John", "Doe", 30, "Man", "john@example.com", "1234567890", "A1234567", "1234567890", 123);
    }

    @Test
    public void testValidPhoneNumbers() {
        String[] validNumbers = {"0412345678", "0512345678", "+61 412 345 678"};
        for (String number : validNumbers) {
            Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", number, "A1234567", "1234567890", 123);
            assertEquals(number, passenger.getPhoneNumber());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassportLength() {
        new Passenger("John", "Doe", 30, "Man", "john@example.com", "0412345678", "A123456789", "1234567890", 123);
    }

    @Test(expected = IllegalStateException.class)
    public void testMissingFields() {
        Passenger passenger = new Passenger();
        passenger.validatePassengerFields();
    }

    @Test
    public void testToString() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
        String expected = "Passenger{ Fullname= John Doe ,email='john@example.com', phoneNumber='0412345678', passport='A1234567', cardNumber='1234567890', securityCode=123}";
        assertEquals(expected, passenger.toString());
    }

    @Test
    public void testSetters() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Jane");
        passenger.setSecondName("Smith");
        passenger.setAge(25);
        passenger.setGender("Woman");
        passenger.setEmail("jane@example.com");
        passenger.setPhoneNumber("0423456789");
        passenger.setPassport("B1234567");
        passenger.setCardNumber("9876543210");
        passenger.setSecurityCode(456);

        assertEquals("Jane", passenger.getFirstName());
        assertEquals("Smith", passenger.getSecondName());
        assertEquals(25, passenger.getAge());
        assertEquals("Woman", passenger.getGender());
        assertEquals("jane@example.com", passenger.getEmail());
        assertEquals("0423456789", passenger.getPhoneNumber());
        assertEquals("B1234567", passenger.getPassport());
        assertEquals("9876543210", passenger.getCardNumber());
        assertEquals(456, passenger.getSecurityCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName() {
        new Passenger("John123", "Doe", 30, "Man", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSecondName() {
        new Passenger("John", "Doe!", 30, "Man", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAge() {
        new Passenger("John", "Doe", -1, "Man", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        new Passenger("John", "Doe", 30, "Invalid", "john@example.com", "0412345678", "A1234567", "1234567890", 123);
    }
}