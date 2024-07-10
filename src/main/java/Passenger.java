import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Passenger extends Person {
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(04|05)\\d{8}$|^\\+61 4\\d{2} \\d{3} \\d{3}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    public Passenger() {
        // Default constructor for flexibility
    }

    public Passenger(String firstName, String secondName, int age, String gender, String email, String phoneNumber, String passport, String cardNumber, int securityCode) {
        super(firstName, secondName, age, gender);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassport(passport);
        setCardNumber(cardNumber);
        setSecurityCode(securityCode);
        validatePassengerFields();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format. It should be in the format abc@domain.com.");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format. It should start with 04 or 05 and have 8 digits.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        if (passport.length() > 9) {
            throw new IllegalArgumentException("Passport number cannot be more than 9 characters long.");
        }
        this.passport = passport;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public void validatePassengerFields() {
        if (getFirstName() == null || getFirstName().isEmpty() ||
                getSecondName() == null || getSecondName().isEmpty() ||
                getAge() < 0 ||
                getGender() == null || getGender().isEmpty() ||
                email == null || email.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty() ||
                passport == null || passport.isEmpty() ||
                cardNumber == null || cardNumber.isEmpty()) {
            throw new IllegalStateException("All fields must be set before using the object.");
        }
    }

    @Override
    public String toString() {
        return "Passenger{" + " Fullname= " + super.getFirstName() + " " + super.getSecondName() +
                " ,email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", securityCode=" + securityCode +
                '}';
    }
}
