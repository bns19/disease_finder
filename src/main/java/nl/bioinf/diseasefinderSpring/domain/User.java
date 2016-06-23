/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 28-4-16.
 */
package nl.bioinf.diseasefinderSpring.domain;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Information stored about the user.
 */
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
    @Column(unique = true)
    @Size(min = 5, max = 30)
    private String username;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min = 5, max = 240)
    private String password;

    /**
     * Password needs to be between 5 and 30 characters in size.
     */
    @Size(min = 5, max = 240)
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
    private boolean enabled;

    /**
     * The account needs to be enabled to work.
     */
    private String role = "USER";

    /**
     * the date and time this user was registered.
     */
    private LocalDateTime createdAt;

    /**
     * Mandatory JPA constructor.
     */
    protected User() {
        super();
    }

    /**
     * this functioncreates the user.
     * @param username username
     * @param password passsword
     * @param confirmPassword password confirmation
     * @param email email
     * @param authority authority
     * @param enabled isEnabled
     * @param role role
     * @param createdAt date of creation
     */
    public User(final String username, final String password, final String confirmPassword, final String email,
                final String authority, final String enabled, final String role, final LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.authority = authority;
        this.enabled = true;
        this.role = role;
        this.createdAt = createdAt;
    }

    /**
     * Get uer id.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user id.
     * @param id id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Get username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     * @param username username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get user password.
     * @return password.
     */
    public String getPassword() {
        checkPassword(); //check
        return password;
    }

    /**
     * Set the user password.
     * @param password password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * get the confirmed pasdsword.
     * @return confirmed password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Set the user confirmed password.
     * @param confirmPassword confirmed password
     */
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkPassword(); //check
    }

    /**
     * Get user email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email.
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get authority.
     * @return authority level.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Set user authority.
     * @param authority authority
     */
    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    /**
     * Get if the user account is enabled or not (True or False).
     * @return enabled.
     */
    public boolean getEnabled() {
        return true;
    }

    /**
     * Set if the account is enabled.
     * @param enabled isEnabled
     */
    public void setEnabled(final boolean enabled) {

        this.enabled = true;
    }

    /**
     * Get the date of creation.
     * @return date of creation.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the creation date.
     * @param createdAt date and time of creation
     */
    public void setCreatedAt(final LocalDateTime createdAt) {
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

    /**
     * Get the role of the user (e.g. Admin or User)
     * @return user role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the role of the user.
     * @param role role
     */
    public void setRole(final String role) {
        this.role = role;
    }
}
