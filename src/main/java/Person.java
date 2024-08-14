//The author of this module code is Xinghui Huang

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;// 新增：用于 equals 和 hashCode 方法

public abstract class Person {
    private String firstName;
    private String secondName;
    private int age;
    private String gender;
    private static final Set<String> VALID_GENDERS = new HashSet<>(Arrays.asList(
            "Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"
    ));

    public Person() {}

    public Person(String firstName, String secondName, int age, String gender) {
        setFirstName(firstName);
        setSecondName(secondName);
        setAge(age);
        setGender(gender);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // 改进：添加上限检查，增强数据有效性验证
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150.");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        // 改进：处理 null 输入和去除空白
        if (gender == null || !VALID_GENDERS.contains(gender.trim())) {
            throw new IllegalArgumentException("Invalid gender. Allowed values are: " + String.join(", ", VALID_GENDERS));
        }
        this.gender = gender.trim();
    }

    public void setGender_k(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName.trim();// 改进：去除首尾空白
    }

    public void setSecondName(String secondName) {
        validateName(secondName, "Second name");
        this.secondName = secondName.trim();// 改进：去除首尾空白
    }

    private void validateName(String name, String fieldName) {
        // 改进：更复杂的名字验证正则表达式，允许更多有效的名字格式
        if (name == null || name.trim().isEmpty() || !name.trim().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
            throw new IllegalArgumentException(fieldName + " must contain only alphabetic characters, spaces, hyphens, apostrophes, or periods, and cannot be empty.");
        }
    }

    public void setFirstName_k(String firstName) {
        validateName_k(firstName);
        this.firstName = firstName;
    }

    public void setSecondName_k(String secondName) {
        validateName_k(secondName);
        this.secondName = secondName;
    }

    private void validateName_k(String name) {}
    
    public void validatePersonFields() {
        // 改进：使用 String.format 提高可读性和性能
        if (firstName == null || secondName == null || age < 0 || gender == null) {
            throw new IllegalStateException("All fields must be set before using the object.");
        }
    }
    @Override
    public String toString() {
        return String.format("Person{firstName='%s', secondName='%s', age=%d, gender='%s'}",
                firstName, secondName, age, gender);
    }
    // 新增：equals 方法，用于对象比较
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName) &&
                Objects.equals(gender, person.gender);
    }
    // 新增：hashCode 方法，配合 equals 使用，用于哈希集合
    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, age, gender);
    }
}
