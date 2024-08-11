//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
public class PersonTest {

    @Test
    public void testValidPerson() {
        Person person = new PersonImpl("Jerry", "Smith", 30, "Man");
        assertEquals("Jerry", person.getFirstName());
        assertEquals("Smith", person.getSecondName());
        assertEquals(30, person.getAge());
        assertEquals("Man", person.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName() {
        new PersonImpl("1Jerry", "Smith", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSecondName() {
        new PersonImpl("Jerry", "Smith3", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAge() {
        new PersonImpl("Jerry", "Smith", -1, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        new PersonImpl("Smith", "Smith", 30, "Male");
    }

    @Test
    public void testValidGenders() {
        String[] validGenders = {"Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"};
        for (String gender : validGenders) {
            Person person = new PersonImpl("Jerry", "Smith", 30, gender);
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
        Person person = new PersonImpl("Jerry", "Smith", 30, "Man");
        String expected = "Person{firstName='Jerry', secondName='Smith', age=30, gender='Man'}";
        assertEquals(expected, person.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameWithSymbols() {
        new PersonImpl("John@", "Bond", 30, "Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameWithSymbols() {
        new PersonImpl("John", "Bond!", 30, "Man");
    }

    @Test
    public void testSetters() {
        Person person = new PersonImpl();
        person.setFirstName("Jane");
        person.setSecondName("Bond");
        person.setAge(25);
        person.setGender("Woman");

        assertEquals("Jane", person.getFirstName());
        assertEquals("Bond", person.getSecondName());
        assertEquals(25, person.getAge());
        assertEquals("Woman", person.getGender());
    }

    //测试年龄设置的改进，包括上限检查。
    @Test
    public void testSetAge() {
        Person person = new PersonImpl();
        person.setAge(30);
        assertEquals(30, person.getAge());

        try {
            person.setAge(151);
            fail("Should throw IllegalArgumentException for age > 150");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
    //测试性别设置的改进，包括空白处理和null输入。
    @Test
    public void testSetGender() {
        Person person = new PersonImpl();
        person.setGender(" Man ");  // 测试去除空白
        assertEquals("Man", person.getGender());

        try {
            person.setGender(null);
            fail("Should throw IllegalArgumentException for null gender");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    // 测试名字设置的改进，包括更复杂的名字格式和空白处理。
    @Test
    public void testSetName() {
        Person person = new PersonImpl();
        person.setFirstName("John-Paul");
        assertEquals("John-Paul", person.getFirstName());

        person.setSecondName("O'Connor");
        assertEquals("O'Connor", person.getSecondName());

        person.setFirstName(" Alice ");  // 测试去除空白
        assertEquals("Alice", person.getFirstName());
    }

    // 测试新增的 equals() 方法。
    @Test
    public void testEquals() {
        Person person1 = new PersonImpl("John", "Doe", 30, "Man");
        Person person2 = new PersonImpl("John", "Doe", 30, "Man");
        Person person3 = new PersonImpl("Jane", "Doe", 25, "Woman");

        assertTrue(person1.equals(person2));
        assertFalse(person1.equals(person3));
    }

    // 测试新增的 hashCode() 方法，包括在 HashSet 中的使用。
    @Test
    public void testHashCode() {
        Person person1 = new PersonImpl("John", "Doe", 30, "Man");
        Person person2 = new PersonImpl("John", "Doe", 30, "Man");

        assertEquals(person1.hashCode(), person2.hashCode());

        Set<Person> personSet = new HashSet<>();
        personSet.add(person1);
        assertTrue(personSet.contains(person2));
    }

    // T测试年龄上限的验证
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAgeUpper() {
        new PersonImpl("Jerry", "Smith", 151, "Man");
    }
    private static class PersonImpl extends Person {
        public PersonImpl() {}

        public PersonImpl(String firstName, String secondName, int age, String gender) {
            super(firstName, secondName, age, gender);
        }
    }

}