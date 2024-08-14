//The author of this module code is Xinghui Huang

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Objects;
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
//新增边缘验证
    @Test
    public void testInvalidAge0() {
        new PersonImpl("Jerry", "Smith", 0, "Man");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAgeNegative() {
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

//    @Test(expected = IllegalStateException.class)
//    public void testMissingFields() {
//        Person person = new PersonImpl();
//        person.validatePersonFields();
//    }


    //新增validatePersonFields的多项验证
@Test
public void testValidPersonFields() {
    Person person = new PersonImpl();
    person.setFirstName("John");
    person.setSecondName("Doe");
    person.setAge(25);
    person.setGender("Man");

    // 期待 validatePersonFields 正常运行，不抛出异常
    person.validatePersonFields();
}

    @Test(expected = IllegalStateException.class)
    public void testNullFirstName() {
        Person person = new PersonImpl();
        person.setSecondName("Doe");
        person.setAge(25);
        person.setGender("Man");

        // firstName 为空时应抛出异常
        person.validatePersonFields();
    }

    @Test(expected = IllegalStateException.class)
    public void testNullSecondName() {
        Person person = new PersonImpl();
        person.setFirstName("John");
        person.setAge(25);
        person.setGender("Man");

        // secondName 为空时应抛出异常
        person.validatePersonFields();
    }

    @Test(expected = IllegalStateException.class)
    public void testNullAge() {
        Person person = new PersonImpl();
        person.setFirstName("John");
        person.setSecondName("Doe");
        person.setGender("Man");

        // age 为空时应抛出异常
        person.validatePersonFields();
    }

    @Test(expected = IllegalStateException.class)
    public void testNullGender() {
        Person person = new PersonImpl();
        person.setFirstName("John");
        person.setSecondName("Doe");
        person.setAge(25);

        // gender 为空时应抛出异常
        person.validatePersonFields();
    }

    @Test(expected = IllegalStateException.class)
    public void testAllFieldsNull() {
        Person person = new PersonImpl();

        // 所有字段均为空时应抛出异常
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
//针对PIT的改进，新增边界测试
    @Test
    public void testEqualsSameObject() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        // 同一个对象的比较，应返回true
        assertTrue(person1.equals(person1));
    }

    @Test
    public void testEqualsDifferentObjectSameValues() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        Person person2 = new PersonImpl();
        person2.setFirstName("John");
        person2.setSecondName("Doe");
        person2.setAge(25);
        person2.setGender("Man");

        // 不同对象但字段相同，应返回true
        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person1));
    }

    @Test
    public void testEqualsDifferentObjectDifferentValues() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        Person person2 = new PersonImpl();
        person2.setFirstName("Jane");
        person2.setSecondName("Doe");
        person2.setAge(25);
        person2.setGender("Woman");

        // 不同对象且字段不同，应返回false
        assertFalse(person1.equals(person2));
        assertFalse(person2.equals(person1));
    }

    @Test
    public void testEqualsNullObject() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        // 与null比较，应返回false
        assertFalse(person1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        String notAPerson = "Not a Person Object";

        // 与不同类的对象比较，应返回false
        assertFalse(person1.equals(notAPerson));
    }

    @Test
    public void testHashCodeConsistency() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        // 相同对象的hashCode应一致
        int initialHashCode = person1.hashCode();
        assertEquals(initialHashCode, person1.hashCode());
    }

    @Test
    public void testHashCodeEqualObjects() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        Person person2 = new PersonImpl();
        person2.setFirstName("John");
        person2.setSecondName("Doe");
        person2.setAge(25);
        person2.setGender("Man");

        // 如果两个对象相等，则它们的hashCode也应该相等
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        Person person2 = new PersonImpl();
        person2.setFirstName("Jane");
        person2.setSecondName("Smith");
        person2.setAge(30);
        person2.setGender("Woman");

        // 如果两个对象不相等，则它们的hashCode可以不同
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testHashCodeInHashSet() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        HashSet<Person> set = new HashSet<>();
        set.add(person1);

        // 使用hashCode进行查找，应能够找到相同对象
        assertTrue(set.contains(person1));
    }

    @Test
    public void testHashCodeForSurvival() {
        Person person1 = new PersonImpl();
        person1.setFirstName("John");
        person1.setSecondName("Doe");
        person1.setAge(25);
        person1.setGender("Man");

        // 测试在hashCode返回0时的情况
        int hashCode = Objects.hash(person1.getFirstName(), person1.getSecondName(), person1.getAge(), person1.getGender());
        assertNotEquals(0, hashCode);
    }

    // T测试年龄上限的验证
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAgeUpper() {
        new PersonImpl("Jerry", "Smith", 151, "Man");
    }
    // PIT测试年龄上限的验证
    @Test
    public void testInvalidAgeUpper150() {
        new PersonImpl("Jerry", "Smith", 150, "Man");
    }
    private static class PersonImpl extends Person {
        public PersonImpl() {}

        public PersonImpl(String firstName, String secondName, int age, String gender) {
            super(firstName, secondName, age, gender);
        }
    }

}