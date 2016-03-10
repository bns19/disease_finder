package nl.bioinf.diseasefinderSpring.login;

/**
 * Created by hjdupon on 24-2-16.
 */

import nl.bioinf.diseasefinderSpring.login.password.PasswordPass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


@Controller
public class UserLogin extends WebMvcConfigurerAdapter {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String showForm(LoginForm loginForm) {
        return "login2";
    }


    //    @PreAuthorize("Admin")
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid LoginForm loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/login2";
        }

        String username = loginForm.username;
        String password = loginForm.password;

        String SQLuser = String.format("select password from User where username = '%s'", username);
        Object mysqlSelectUser = jdbcTemplate.queryForList(SQLuser, new MapSqlParameterSource());

        String bearPassword = PasswordPass.PasswordPass(mysqlSelectUser.toString());

        if (password.toString().equals(bearPassword.toString())){
            return "results";
        }

        // return the next page
        return "redirect:/login2";
    }

}






