package nl.bioinf.diseasefinderSpring.login;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hjdupon on 24-2-16.
 */

public class PersonForm {



    private String username;

    private String firstName;

    private String lastName;

    private String password2;

    private String password;

    private String email;

    private String authority;

    private String enabled;


    // DD/MM/YYYY
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

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
        return "Person(Name: " + this.username + ")";
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setAuthority(String authority) {

        this.authority = "USER";

    }

    public String getAuthority(){

        return authority;}


    public void setEnabled(String enabled) {

        this.enabled = "true";

    }

    public String getEnabled(){

        return enabled;

    }


    public void setPassword2(String password2) {

        this.password2 = password2;

    }

    public String getPassword2(){

        return password2;}

}