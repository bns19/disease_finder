
package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 24-2-16.
 */

import nl.bioinf.diseasefinderSpring.login.password.EncryptPassword;
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

            // create table User if it not exists with the properties //
            log.info("Creating tables");
            jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(255), " +
                            "email VARCHAR(255), username VARCHAR(255), birthdate VARCHAR(10), authority VARCHAR(10), enabled VARCHAR(10))"
                    , new MapSqlParameterSource());


            jdbcTemplate.update("CREATE TABLE IF NOT EXISTS History(" + "id SERIAL, searchfield VARCHAR(255))"
                , new MapSqlParameterSource());

            // input the form data //
            String inputMysql = String.format("INSERT INTO User (first_name, last_name, password, email, username, birthdate, authority, enabled) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    personForm.getFirstName(),
                    personForm.getLastName(),
                    encrypted,
                    personForm.getEmail(),
                    personForm.getUsername(),
                    personForm.getbDate(),
                    personForm.getAuthority(),
                    personForm.getEnabled());

            jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

            return "/login";
        }
        // return the next page
        return "/login";
    }

}
