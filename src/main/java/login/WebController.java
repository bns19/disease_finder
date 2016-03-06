package login;

/**
 * Created by hjdupon on 24-2-16.
 */

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
    public String showForm(PersonForm User) {

        return "form";
    }

    /**
     * Make the jdbcTemplate approachable
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     *
     * @param User data from the
     * @param bindingResult
     * @return the result page
     */
    //    @PreAuthorize("Admin")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid PersonForm User, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        // create table User if it not exists with the properties
        log.info("Creating tables");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), age INT(3), password VARCHAR(255), " +
                        "email VARCHAR(255), username VARCHAR(255), birthdate VARCHAR(10))"
                , new MapSqlParameterSource());

        String inputMysql = String.format("INSERT INTO User (first_name, last_name, age, password, email, username, birthdate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                User.getFirstName(), User.getLastName(), User.getAge(), User.getPassword(), User.getEmail(), User.getUsername(), User.getbDate());
        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        // create table User if it not exists with the properties
//        UserDAO.UserDAO(User.getFirstName(), User.getLastName(), User.getAge(), User.getPassword(), User.getEmail(), User.getbDate(), User.getbDate());


        // return the next page
        return "redirect:/login";
    }


}






