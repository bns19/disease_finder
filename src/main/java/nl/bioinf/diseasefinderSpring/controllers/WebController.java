/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.Database.MySQLCreateTables;
import nl.bioinf.diseasefinderSpring.Database.RegisterUserMySQL;
import nl.bioinf.diseasefinderSpring.login.EncryptPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


/**
 * Gets the information from the registration form and saves this in the PersonForm class.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    /**
     * Initializes the Personform bean and maps id on /form.
     *
     * @param personForm personForm.
     * @return the form template.
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm(final PersonForm personForm) {

        return "form";
    }

    /**
     * Make the jdbcTemplate usable in the class.
     * This is the database connector.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * @param personForm    data from the registration form.
     * @param bindingResult bindingResult.
     * @return the result page.
     */
    // @PreAuthorize("Admin")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid final PersonForm personForm, final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        if (!bindingResult.hasErrors()) {

           // EncryptPassword pw = new EncryptPassword();
            String encrypted = EncryptPassword.encryptPassword(personForm.getPassword());

            MySQLCreateTables mySQLCreateTables = new MySQLCreateTables();
            mySQLCreateTables.createUserTableMySQL(jdbcTemplate);

            RegisterUserMySQL registerUser = new RegisterUserMySQL();
            registerUser.registerUserMySQL(encrypted, personForm, jdbcTemplate);

            return "/login";
        }

        return "/login";
    }

}
