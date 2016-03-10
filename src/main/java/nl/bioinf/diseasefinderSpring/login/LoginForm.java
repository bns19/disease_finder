package nl.bioinf.diseasefinderSpring.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hjdupon on 7-3-16.
 */
public class LoginForm {
    @NotNull
    @Size(min = 2, max = 30)
    public String username;

    @NotNull
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
