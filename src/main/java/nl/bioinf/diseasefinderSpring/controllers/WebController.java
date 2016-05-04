/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.security.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.time.LocalDateTime;


/**
 * Gets the information from the registration form and saves this in the UserForm class.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public WebController(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(final User user) {

        return "home";
    }

    /**
     * @param user data from the registration form.
     * @param bindingResult bindingResult.
     * @return the result page.
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid final User user, final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "form";
        }
        if (!bindingResult.hasErrors()) {

            String encrypted = EncryptPassword.encryptPassword(user.getPassword());

            user.setPassword(encrypted);

            user.setCreatedAt(LocalDateTime.now());
            userRepository.save(user);

            return "/home";
        }
        return "/home";

    }

}
