//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;

public class PassengerTest {

    @Test
    public void testValidPassenger() {
        Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
        assertEquals("Michael", passenger.getFirstName());
        assertEquals("Jackson", passenger.getSecondName());
        assertEquals(30, passenger.getAge());
        assertEquals("Man", passenger.getGender());
        assertEquals("Michael@qq.com", passenger.getEmail());
        assertEquals("0412345678", passenger.getPhoneNumber());
        assertEquals("A1234567", passenger.getPassport());
        assertEquals("1234567890", passenger.getCardNumber());
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPhoneNumber() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "123456557890", "A1234567", "1234567890", 123);
    }

    @Test
    public void testValidPhoneNumbers() {
        String[] validNumbers = {"0412345678", "0512345678", "+61 123 345 678"};
        for (String number : validNumbers) {
            Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", number, "A1234567", "1234567890", 123);
            assertEquals(number, passenger.getPhoneNumber());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassportLength() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345006789", "1234567890", 123);
    }

    @Test(expected = IllegalStateException.class)
    public void testMissingFields() {
        Passenger passenger = new Passenger();
        passenger.validatePassengerFields();
    }

    @Test
    public void testToString() {
        Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
        String expected = "Passenger{ Fullname= Michael Jackson ,email='Michael@qq.com', phoneNumber='0412345678', passport='A1234567', cardNumber='1234567890', securityCode=123}";
        assertEquals(expected, passenger.toString());
    }

    @Test
    public void testSetters() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Michael");
        passenger.setSecondName("Jackson");
        passenger.setAge(25);
        passenger.setGender("Woman");
        passenger.setEmail("Michael@qq.com");
        passenger.setPhoneNumber("0423456789");
        passenger.setPassport("B1234567");
        passenger.setCardNumber("9876545555");
        passenger.setSecurityCode(4561);

        assertEquals("Michael", passenger.getFirstName());
        assertEquals("Jackson", passenger.getSecondName());
        assertEquals(25, passenger.getAge());
        assertEquals("Woman", passenger.getGender());
        assertEquals("Michael@qq.com", passenger.getEmail());
        assertEquals("0423456789", passenger.getPhoneNumber());
        assertEquals("B1234567", passenger.getPassport());
        assertEquals("9876545555", passenger.getCardNumber());
        assertEquals(4561, passenger.getSecurityCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName() {
        new Passenger("Michael123", "Pig", 30, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSecondName() {
        new Passenger("Michael", "Pig!", 30, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAge() {
        new Passenger("Michael", "Pig", -10, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        new Passenger("Michael", "Pig", 30, "Invalid", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }
}