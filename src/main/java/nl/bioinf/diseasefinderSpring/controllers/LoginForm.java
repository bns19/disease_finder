/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 7-3-16.
 */

package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginForm {

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

    /**
     * @return first name of the user.
     */
    final public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName = first name of the user.
     */
    final public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last name of the user.
     */
    final public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName = last name of the user.
     */
    final public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return password from the user.
     */
    final public String getPassword() {
        return password;
    }

    /**
     * @param password = password from the user.
     */
    final public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return email adress of the user.
     */
    final public String getEmail() {
        return email;
    }

    /**
     * @param email = email adress of the user.
     */
    final public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return birth date of the user.
     */
    final public String getbDate() {
        return bDate;
    }

    /**
     * @param bDate = set birth date.
     */
    final public void setbDate(final String bDate) {
        this.bDate = bDate;
    }

    /**
     * @return person's ma,e
     */
    final public String toString() {
        return "Person(Name: " + this.username + ")";
    }

    /**
     * @return username.
     */
    final public String getUsername() {

        return username;
    }

    /**
     * @param username = set the username.
     */
    final public void setUsername(final String username) {

        this.username = username;
    }

    /**
     * @param authority = authority level can be changed in the future.
     */
    final public void setAuthority(final String authority) {

        this.authority = "USER";
    }

    /**
     * @return the authorty level.
     */
    final public String getAuthority() {

        return authority;
    }

    /**
     * @param enabled = shows if the user account is enabled.
     */
    final public void setEnabled(final String enabled) {

        this.enabled = "true";
    }

    /**
     * @return enabled.
     */
    final public String getEnabled() {

        return enabled;
    }

    /**
     * @param password2 = confirm password.
     */
    final public void setPassword2(final String password2) {

        this.password2 = password2;
    }

    /**
     * @return the confirmation password.
     */
    final public String getPassword2() {

        return password2;
    }
}