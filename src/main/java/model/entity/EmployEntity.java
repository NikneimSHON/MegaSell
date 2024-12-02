package model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@Builder
public class EmployEntity {
    private Long id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date dateRegistration;
    private String email;
    private String password;
    private String number;
    private Long activityId;
    private Long accessPermissionId;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$";
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÿ]+([ '-][A-Za-zÀ-ÿ]+)*$";

    public EmployEntity(Long id, String firstName, String lastName, Date birthDate, Date dateRegistration,
                        String email, String password, String number, Long activityId, Long accessPermissionId) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setDateRegistration(dateRegistration);
        setEmail(email);
        setPassword(password);
        setNumber(number);
        this.activityId = activityId;
        this.accessPermissionId = accessPermissionId;
    }

    public void setFirstName(String firstName) {
        if (!validateName(firstName)) {
            throw new IllegalArgumentException("Invalid firstName: " + firstName);
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (!validateName(lastName)) {
            throw new IllegalArgumentException("Invalid lastName: " + lastName);
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Invalid password: " + password);
        }
        this.password = password;
    }

    public void setNumber(String number) {
        if (!validatePhone(number)) {
            throw new IllegalArgumentException("Invalid phone number: " + number);
        }
        this.number = number;
    }

    public void setBirthDate(Date birthDate) {
        if (!validateBirthDate(birthDate)) {
            throw new IllegalArgumentException("Invalid birth date: " + birthDate);
        }
        this.birthDate = birthDate;
    }

    public void setDateRegistration(Date dateRegistration) {
        if (!validateDateRegistration(dateRegistration)) {
            throw new IllegalArgumentException("Invalid registration date: " + dateRegistration);
        }
        this.dateRegistration = dateRegistration;
    }

    public boolean validateEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean validatePhone(String number) {
        return number != null && number.matches(PHONE_REGEX);
    }

    public boolean validateName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean validateBirthDate(Date birthDate) {
        if (birthDate == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        LocalDate birthLocalDate = convertToLocalDate(birthDate);
        return !birthLocalDate.isAfter(today);
    }

    public boolean validateDateRegistration(Date dateRegistration) {
        if (dateRegistration == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        LocalDate registrationLocalDate = convertToLocalDate(dateRegistration);
        return !registrationLocalDate.isAfter(today);
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
    }
}