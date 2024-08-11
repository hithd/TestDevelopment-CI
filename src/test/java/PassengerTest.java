//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PassengerTest {


    @Test
    public void testValidPassenger() {
        Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345678", "1234567890", 123);
        assertEquals("Michael", passenger.getFirstName());
        assertEquals("Jackson", passenger.getSecondName());
        assertEquals(30, passenger.getAge());
        assertEquals("Man", passenger.getGender());
        assertEquals("Michael@qq.com", passenger.getEmail());
        assertEquals("+61 412 345 678", passenger.getPhoneNumber());
        assertEquals("A12345678", passenger.getPassport());
        assertEquals("1234567890", passenger.getCardNumber());
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPhoneNumber() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "123456557890", "A12345678", "1234567890", 123);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSecurityCode() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "123456557890", "A12345678", "1234567890", 12);
    }
    @Test
    public void testValidPhoneNumbers() {
        // Test data
        String[] validNumbers = {"0412345678", "0512345678", "+61 423 345 678"};
        String[] expectedNumbers = {"+61 412 345 678", "+61 512 345 678", "+61 423 345 678"};

        for (int i = 0; i < validNumbers.length; i++) {
            Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", validNumbers[i], "A12345678", "1234567890", 123);
            // Assume the Passenger class has a method to standardize phone numbers to a certain format
            String standardizedNumber = passenger.getPhoneNumber();
            assertEquals(expectedNumbers[i], standardizedNumber);
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassportLength() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345006789", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPassportab1234567() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "ab1234567", "1234567890", 123);
    }


    @Test
    public void testToString() {
        Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345678", "1234567890", 123);
        String expected = "Passenger{ Fullname= Michael Jackson, email='Michael@qq.com', phoneNumber='+61 412 345 678', passport='A12345678', cardNumber='******7890', securityCode=123 }";
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
        passenger.setPassport("B12345678");
        passenger.setCardNumber("9876545555");
        passenger.setSecurityCode(4561);

        assertEquals("Michael", passenger.getFirstName());
        assertEquals("Jackson", passenger.getSecondName());
        assertEquals(25, passenger.getAge());
        assertEquals("Woman", passenger.getGender());
        assertEquals("Michael@qq.com", passenger.getEmail());
        assertEquals("+61 423 456 789", passenger.getPhoneNumber());
        assertEquals("B12345678", passenger.getPassport());
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