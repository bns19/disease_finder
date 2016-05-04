package nl.bioinf.diseasefinderSpring.domain;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class User {


    /**
     * id is the primary key.
     * id is the user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Username may not be empty.
     * Username size needs to be between 5 and 30 characters in size.
     */
    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min = 5)
    private String password;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min = 5)
    private String confirmPassword;

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
     * The account needs to be enabled to work.
     */
    private String role = "USER";

    private LocalDateTime createdAt;

    /**
     * Mandatory JPA constructor.
     */
    protected User() {
    }


    public User(String username, String password, String confirmPassword, String email, String authority, String enabled, String role, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.authority = authority;
        this.enabled = enabled;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        this.password = password;
        checkPassword(); //check
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkPassword(); //check
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Checks if the password is the same as the confirmed (password2) password.
     */
    private void checkPassword() {
        if (this.password == null || this.confirmPassword == null) {
            return;
        } else if (!this.password.equals(confirmPassword)) {
            this.confirmPassword = null;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
