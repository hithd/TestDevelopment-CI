//The author of this module code is Xinghui Huang

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Person //abstract class Person
{
    private String firstName;
    private String secondName;
    private int age;
    private String gender;
    private static final Set<String> VALID_GENDERS = new HashSet<>(Arrays.asList(
            "Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"
    ));

    public Person(){}

    public Person(String firstName, String secondName, int age, String gender){
        setFirstName(firstName);
        setSecondName(secondName);
        setAge(age);
        setGender(gender);
        validatePersonFields();
    }



    public int getAge() {
        return age;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    //确保age值有效
    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative.");
        this.age = age;
    }
    public String getGender() {
        return gender;
    }

//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    //确保性别符合要求
    public void setGender(String gender) {
        if (!VALID_GENDERS.contains(gender)) {
            throw new IllegalArgumentException("Invalid gender. Allowed values are: Woman, Man, Non-binary | gender diverse, Prefer not to say, Other.");
        }
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setSecondName(String secondName) {
//        this.secondName = secondName;
//    }
//    确保名字符合格式

    public void setFirstName(String firstName) {
        validateName(firstName);
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        validateName(secondName);
        this.secondName = secondName;
    }
    //    验证名字格式
    private void validateName(String name) {
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Name must contain only alphabetic characters and cannot start with a number or symbol.");
        }
    }
    //验证所有的fields
    public void validatePersonFields() {
        if (firstName == null || secondName == null || age < 0 || gender == null) {
            throw new IllegalStateException("All fields must be set before using the object.");
        }
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
