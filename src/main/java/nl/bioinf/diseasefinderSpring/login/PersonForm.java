package nl.bioinf.diseasefinderSpring.login;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

/**
 * Created by hjdupon on 24-2-16.
 */

@Entity
public class PersonForm {

    @NotEmpty
    @Size(min=5, max=30)
    private String username;

    private String firstName;

    private String lastName;

    @Size(min=5, max=30)
    private String password;

    @NotNull(message="passwords do not match")
    private String password2;

    @NotEmpty
    @Email
    private String email;

    private String authority = "USER";

    private String enabled = "true";


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
        this.authority = authority;
    }

    public String getAuthority(){
        return authority;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

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

    private void checkPassword() {
        if(this.password == null || this.password2 == null){
            return;
        }else if(!this.password.equals(password2)){
            this.password2 = null;
        }
    }

}