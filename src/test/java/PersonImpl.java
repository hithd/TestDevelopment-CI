//为了测试person抽象函数所做的继承
public class PersonImpl extends Person {
    public PersonImpl() {
        super();
    }

    public PersonImpl(String firstName, String secondName, int age, String gender) {
        super(firstName, secondName, age, gender);
    }
}