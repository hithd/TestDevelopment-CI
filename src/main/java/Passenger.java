
//The author of this module code is Xinghui Huang

import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Passenger extends Person {
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(04|05)\\d{8}$|^\\+61 4\\d{2} \\d{3} \\d{3}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PASSPORT_PATTERN = Pattern.compile("^[A-Z0-9]{1,9}$");

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
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format. It should be in the format abc@domain.com.");
        }
        this.email = email;
    }

    public void setEmail_k(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format. It should start with 04 or 05 and have 8 digits, or be in international format (+61 4xx xxx xxx).");
        }
        this.phoneNumber = formatPhoneNumber(phoneNumber);// Added phone number formatting
    }

    public void setPhoneNumber_k(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("+61")) {
            return phoneNumber;
        }
        return "+61 " + phoneNumber.substring(1, 4) + " " + phoneNumber.substring(4, 7) + " " + phoneNumber.substring(7);
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        if (passport == null || !PASSPORT_PATTERN.matcher(passport).matches()) {
            throw new IllegalArgumentException("Invalid passport number. It should be 9 characters long and contain only uppercase letters and numbers.");
        }
        this.passport = passport;
    }

    public void setPassport_k(String passport) {
        this.passport = passport;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty.");
        }
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        if (securityCode < 100 || securityCode > 9999) {
            throw new IllegalArgumentException("Security code must be between 3 and 4 digits.");
        }
        this.securityCode = securityCode;
    }

    public List<String> validateAllFields() {
        List<String> errors = new ArrayList<>();
        try {
            validatePassengerFields();
        } catch (IllegalStateException e) {
            for (String error : e.getMessage().split(",")) {
                errors.add(error.trim());
            }
        }
        return errors;
    }
    // Added method to return list of validation errors

    public void validatePassengerFields() {
        StringBuilder errorMessage = new StringBuilder();
        if (getFirstName() == null || getFirstName().isEmpty()) errorMessage.append("First name is required, ");
        if (getSecondName() == null || getSecondName().isEmpty()) errorMessage.append("Second name is required, ");
        if (getAge() < 0) errorMessage.append("Age must be non-negative, ");
        if (getGender() == null || getGender().isEmpty()) errorMessage.append("Gender is required, ");
        if (email == null || email.isEmpty()) errorMessage.append("Email is required, ");
        if (phoneNumber == null || phoneNumber.isEmpty()) errorMessage.append("Phone number is required, ");
        if (passport == null || passport.isEmpty()) errorMessage.append("Passport number is required, ");
        if (cardNumber == null || cardNumber.isEmpty()) errorMessage.append("Card number is required, ");

        if (errorMessage.length() > 0) {
            errorMessage.setLength(errorMessage.length() - 2);  // Remove last comma and space
            throw new IllegalStateException(errorMessage.toString());
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Passenger{ Fullname= %s %s, email='%s', phoneNumber='%s', passport='%s', cardNumber='%s', securityCode=%d }",
                getFirstName(),
                getSecondName(),
                email,
                phoneNumber,
                passport,
                maskCardNumber(cardNumber),
                securityCode
        );
    }

    private String maskCardNumber(String cardNumber) {
        // Added method to mask card number
        if (cardNumber == null || cardNumber.length() <= 4) {
            return cardNumber;
        }
        return "*".repeat(cardNumber.length() - 4) + cardNumber.substring(cardNumber.length() - 4);
    }
}
