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

/**
 * This class regulates the control of the registrationbean.
 * It checks the input of the user and fills in neccesary default values.
 */
public class PersonForm {

    /**
     * Username may not be empty.
     * Username size needs to be between 5 and 30 characters in size.
     */
    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    /**
     * Firstname of the User.
     */
    private String firstName;

    /**
     * Lastname of the User.
     */
    private String lastName;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min = 5, max = 30)
    private String password;

    /**
     * Second password may not be null.
     */
    @NotNull(message = "passwords do not match")
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

    /**
     * Get first name.
     *
     * @return FirstName of the User.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name.
     *
     * @param firstName firstname.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name.
     *
     * @return Lastname of the User.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name.
     *
     * @param lastName lastname.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get password.
     *
     * @return the Password.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Get email adres.
     *
     * @return Email adress.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email adress.
     *
     * @param email email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get date of birth.
     *
     * @return date of birth.
     */
    public String getbDate() {
        return bDate;
    }

    /**
     * Set day of birth.
     *
     * @param bDate bDate.
     */
    public void setbDate(final String bDate) {
        this.bDate = bDate;
    }

    /**
     * toString.
     *
     * @return person.
     */
    public String toString() {
        return "Person(Name: " + this.username + ")";
    }

    /**
     * Get username.
     *
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     *
     * @param username username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Set authority.
     *
     * @param authority authority.
     */
    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    /**
     * @return Authority level.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Set enabled.
     *
     * @param enabled enabled
     */
    public void setEnabled(final String enabled) {
        this.enabled = enabled;
    }

    /**
     * Get enabled.
     *
     * @return if the User account is enabled.
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * Get password2.
     *
     * @return password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Set password.
     *
     * @param password password.
     */
    public void setPassword(final String password) {
        this.password = password;
        checkPassword(); //check
    }

    /**
     * Set password2.
     *
     * @param password2 password2..
     */
    public void setPassword2(final String password2) {
        this.password2 = password2;
        checkPassword(); //check
    }

    /**
     * Checks if the password is the same as the confirmed (password2) password.
     */
    private void checkPassword() {
        if (this.password == null || this.password2 == null) {
            return;
        } else if (!this.password.equals(password2)) {
            this.password2 = null;
        }
    }

}