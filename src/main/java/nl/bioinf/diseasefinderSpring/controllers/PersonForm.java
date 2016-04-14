/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-2-16.
 */

package nl.bioinf.diseasefinderSpring.controllers;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
public class PersonForm {

    /**
     * Username may not be empty.
     * Username size needs to be between 5 and 30 characters in size.
     */
    @NotEmpty
    @Size(min=5, max=30)
    private String username;

    /**
     * firstname of the User.
     */
    private String firstName;

    /**
     * lastname of the User.
     */
    private String lastName;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min=5, max=30)
    private String password;

    /**
     * Second password may not be null.
     */
    @NotNull(message="passwords do not match")
    private String password2;

    /**
     * Email may not be empty.
     * Email needs to have an Email structure.
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * Authority must be USER of can be something different when specified.
     */
    private String authority = "USER";

    /**
     * The account needs to be enabled to work.
     */
    private String enabled = "true";


    /**
     * Date must have a Date structure.
     */
    // DD/MM/YYYY
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

    /**
     * @return FirstName of the User.
     */
    final public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    final public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Lastname of the User.
     */
    final public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    final public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the Password.
     */
    final public String getPassword() {
        return password;
    }


    /**
     * @return Email adress.
     */
    final public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    final public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return date of birth.
     */
    final public String getbDate() {
        return bDate;
    }

    /**
     * @param bDate
     */
    final public void setbDate(final String bDate) {
        this.bDate = bDate;
    }

    /**
     * @return Username
     */
    final public String toString() {
        return "Person(Name: " + this.username + ")";
    }

    /**
     * @return Username.
     */
    final public String getUsername() {
        return username;
    }

    /**
     * @param username needed.
     */
    final public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @param authority level needed.
     */
    final public void setAuthority(final String authority) {
        this.authority = authority;
    }

    /**
     * @return Authority level.
     */
    final public String getAuthority(){
        return authority;
    }

    /**
     * @param enabled enable account.
     */
    final public void setEnabled(final String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return if the User account is enabled.
     */
    final public String getEnabled(){
        return enabled;
    }

    /**
     * @return confirmation password.
     */
    final public String getPassword2(){
        return password2;
    }

    /**
     * @param password
     */
    final public void setPassword(final String password) {
        this.password = password;
        checkPassword();//check
    }

    /**
     * @param password2 = confimation password.
     */
    final public void setPassword2(final String password2) {
        this.password2 = password2;
        checkPassword();//check
    }

    /**
     * Checks if the password is the same as the confirmed (password2) password.
     */
    final private void checkPassword() {
        if(this.password == null || this.password2 == null){
            return;
        }else if(!this.password.equals(password2)){
            this.password2 = null;
        }
    }

}