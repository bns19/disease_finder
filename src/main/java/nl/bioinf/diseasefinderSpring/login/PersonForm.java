package nl.bioinf.diseasefinderSpring.login;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hjdupon on 24-2-16.
 */

public class PersonForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @Min(18)
    private Integer age;

    private String firstName;

    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    // DD/MM/YYYY
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String toString() {
        return "Person(Name: " + this.username + ", Age: " + this.age + ")";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}