package nl.bioinf.diseasefinderSpring.controllers;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hjdupon on 24-2-16.
 */

public class PersonForm {

    /**
     * Username may not be empty
     * Username size needs to be between 5 and 30
     */
    @NotEmpty
    @Size(min=5, max=30)
    private String username;

    /**
     * firstname of the User
     */
    private String firstName;

    /**
     * lastname of the User
     */
    private String lastName;

    /**
     * Password needs to be between 5 and 30 characters in size
     */
    @Size(min=5, max=30)
    private String password;

    /**
     * Second password may not be null
     */
    @NotNull(message="passwords do not match")
    private String password2;

    /**
     * Email may not be empty
     * Email needs to have an Email structure
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * Authority must be USER of can be something different when specified
     */
    private String authority = "USER";

    /**
     * The account needs to be enabled to work
     */
    private String enabled = "true";


    /**
     * Date must have a Date structure
     */
    // DD/MM/YYYY
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

    /**
     * @return FirstName of the User
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Lastname of the User
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @return Email adress
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return date of birth
     */
    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String toString() {
        return "Person(Name: " + this.username + ")";
    }

    /**
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @return Authority level
     */
    public String getAuthority(){
        return authority;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return if the User account is enabled
     */
    public String getEnabled(){
        return enabled;
    }

    public String getPassword2(){
        return password2;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();//check
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
        checkPassword();//check
    }

    /**
     * Checks if the password is the same as the confirmed (password2) password
     */
    private void checkPassword() {
        if(this.password == null || this.password2 == null){
            return;
        }else if(!this.password.equals(password2)){
            this.password2 = null;
        }
    }

}