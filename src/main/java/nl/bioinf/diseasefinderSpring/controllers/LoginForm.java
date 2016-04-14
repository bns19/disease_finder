/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 7-3-16.
 */

package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * This class represents the bean that contains all the information of the registrationform.
 */
public class LoginForm {

    /**
     * username.
     */
    private String username;

    /**
     * firstname.
     */
    private String firstName;

    /**
     * lastname.
      */
    private String lastName;

    /**
     * password
     */
    private String password2;

    /**
     * password (check).
     */
    private String password;

    /**
     * email.
     */
    private String email;

    /**
     * authority (default = Non-admin)
     */
    private String authority;

    /**
     * enabled(default = true)
     */
    private String enabled;

    /**
     * birthdate
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String bDate;

    /**
     * @return first name of the user.
     */
     public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName = first name of the user.
     */
     public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last name of the user.
     */
     public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName = last name of the user.
     */
     public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return password from the user.
     */
     public String getPassword() {
        return password;
    }

    /**
     * @param password = password from the user.
     */
     public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return email adress of the user.
     */
     public String getEmail() {
        return email;
    }

    /**
     * @param email = email adress of the user.
     */
     public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return birth date of the user.
     */
     public String getbDate() {
        return bDate;
    }

    /**
     * @param bDate = set birth date.
     */
     public void setbDate(final String bDate) {
        this.bDate = bDate;
    }

    /**
     * @return person's ma,e
     */
     public String toString() {
        return "Person(Name: " + this.username + ")";
    }

    /**
     * @return username.
     */
     public String getUsername() {

        return username;
    }

    /**
     * @param username = set the username.
     */
     public void setUsername(final String username) {

        this.username = username;
    }

    /**
     * @param authority = authority level can be changed in the future.
     */
     public void setAuthority(final String authority) {

        this.authority = "USER";

    }

    /**
     * @return the authorty level.
     */
     public String getAuthority(){

        return authority;}


    /**
     * @param enabled = shows if the user account is enabled.
     */
     public void setEnabled(final String enabled) {

        this.enabled = "true";

    }

    /**
     * @return enabled.
     */
     public String getEnabled(){

        return enabled;

    }


    /**
     * @param password2 = confirm password.
     */
     public void setPassword2(final String password2) {

        this.password2 = password2;

    }

    /**
     * @return the confirmation password.
     */
     public String getPassword2(){

        return password2;}

}