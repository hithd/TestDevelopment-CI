//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testValidPerson() {
        Person person = new PersonImpl("John", "Doe", 30, "Man");
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getSecondName());
        assertEquals(30, person.getAge());
        assertEquals("Man", person.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName() {
        new PersonImpl("123John", "Doe", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSecondName() {
        new PersonImpl("John", "Doe123", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAge() {
        new PersonImpl("John", "Doe", -1, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        new PersonImpl("John", "Doe", 30, "Invalid");
    }

    @Test
    public void testValidGenders() {
        String[] validGenders = {"Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"};
        for (String gender : validGenders) {
            Person person = new PersonImpl("John", "Doe", 30, gender);
            assertEquals(gender, person.getGender());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testMissingFields() {
        Person person = new PersonImpl();
        person.validatePersonFields();
    }

    @Test
    public void testToString() {
        Person person = new PersonImpl("John", "Doe", 30, "Man");
        String expected = "Person{firstName='John', secondName='Doe', age=30, gender='Man'}";
        assertEquals(expected, person.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameWithSymbols() {
        new PersonImpl("John@", "Doe", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameWithSymbols() {
        new PersonImpl("John", "Doe!", 30, "Man");
    }

    @Test
    public void testSetters() {
        Person person = new PersonImpl();
        person.setFirstName("Jane");
        person.setSecondName("Smith");
        person.setAge(25);
        person.setGender("Woman");

        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getSecondName());
        assertEquals(25, person.getAge());
        assertEquals("Woman", person.getGender());
    }

    // Helper class to test the abstract Person class

}