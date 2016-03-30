
package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 24-2-16.
 */

import nl.bioinf.diseasefinderSpring.Database.MySQLCreateTables;
import nl.bioinf.diseasefinderSpring.Database.RegisterUserMySQL;
import nl.bioinf.diseasefinderSpring.login.EncryptPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm(PersonForm personForm) {

        return "form";
    }

    /**
     * Make the jdbcTemplate approachable
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * @param personForm data from the
     * @param bindingResult
     * @return the result page
     */
    // @PreAuthorize("Admin")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        if (! bindingResult.hasErrors()) {

            EncryptPassword pw = new EncryptPassword();
            String encrypted = pw.EncryptPassword(personForm.getPassword());

            MySQLCreateTables MySQLCreateTables = new MySQLCreateTables();
            MySQLCreateTables.CreateUserTableMySQL(jdbcTemplate);

            RegisterUserMySQL RegisterUser = new RegisterUserMySQL();
            RegisterUser.RegisterUserMySQL(encrypted, personForm, jdbcTemplate);

            return "/login";
        }

        return "/login";
    }

}
