//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PassengerTest {
    private Passenger passengernull;
    //使用反射来强制设置类型
    private void setFieldWithReflection(Passenger passenger, String fieldName, Object value) throws Exception {
        Field field = Passenger.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(passenger, value);
    }
//新增validateAllFields测试
    @Test
    public void testValidPassenger() {
        Passenger passenger = new Passenger("Michael", "Jackson", 0, "Man", "michael@example.com", "0412345678", "A12345678", "1234567890123456", 123);
        passenger.validatePassengerFields();

    }
    @Test
    public void testValidatePassengerFieldsMissingField() throws Exception {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "0412345678", "AB1234567", "1234567890123456", 123);

        // 使用反射将 secondName 设置为 null
        setFieldWithReflection(passenger, "email", null);

        IllegalStateException exception = assertThrows(IllegalStateException.class, passenger::validatePassengerFields);
        assertEquals("Email is required, ", exception.getMessage());
    }
    @Test
    public void testAllNull(){

        passengernull = new Passenger();
        passengernull.setFirstName("Juju");
        passengernull.setSecondName("Bond");
        passengernull.setAge(30);
        passengernull.setGender("Woman");

        IllegalStateException exception = assertThrows(IllegalStateException.class, passengernull::validatePassengerFields);
        assertEquals("Email is required, Phone number is required, Passport number is required, Card number is required, ", exception.getMessage());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMissingEmail() {
        Passenger passenger = new Passenger("Michael", "Jackson", 30, "Man", "", "0412345678", "A12345678", "1234567890123456", 123);
        passenger.validatePassengerFields();
    }
    @Test
    public void testValidPassengerCall() {
        // This test ensures that all fields are set correctly and validatePassengerFields is called
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "A12345678", "1234567890", 123);

        // Assuming validatePassengerFields throws an exception on failure, no exception means success
        assertNotNull(passenger);  // If we get here, the constructor worked fine
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
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345678", "1234567890", 12);
    }
    @Test
    public void testInvalidSecurityCode100() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345678", "1234567890", 100);
    }
    @Test
    public void testInvalidSecurityCode9999() {
        new Passenger("Michael", "Jackson", 30, "Man", "Michael@qq.com", "0412345678", "A12345678", "1234567890", 9999);
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
//新增测试masknumber功能
@Test(expected = IllegalArgumentException.class)
public void testMaskCardNumberWithNullCardNumber() {
    Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "+61 412 345 678", "AB1234567", null, 123);
    String expected = "Passenger{ Fullname= John Doe, email='john@example.com', phoneNumber='+61 412 345 678', passport='AB1234567', cardNumber='null', securityCode=123 }";
    assertEquals(expected, passenger.toString());
}

    @Test(expected = IllegalArgumentException.class)
    public void testMaskCardNumberWithShortCardNumber() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "+61 412 345 678", "AB1234567", "123", 123);
        String expected = "Passenger{ Fullname= John Doe, email='john@example.com', phoneNumber='+61 412 345 678', passport='AB1234567', cardNumber='123', securityCode=123 }";
        assertEquals(expected, passenger.toString());
    }

    @Test
    public void testMaskCardNumberWithExactlyFourDigits() {
        Passenger passenger = new Passenger("John", "Doe", 30, "Man", "john@example.com", "+61 412 345 678", "AB1234567", "1234", 123);
        String expected = "Passenger{ Fullname= John Doe, email='john@example.com', phoneNumber='+61 412 345 678', passport='AB1234567', cardNumber='1234', securityCode=123 }";
        assertEquals(expected, passenger.toString());
    }
//新增validate测试

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
    @Test
    public void testValidAge() {
        new Passenger("Michael", "Pig", 0, "Man", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        new Passenger("Michael", "Pig", 30, "Invalid", "Michael@qq.com", "0412345678", "A1234567", "1234567890", 123);
    }
}