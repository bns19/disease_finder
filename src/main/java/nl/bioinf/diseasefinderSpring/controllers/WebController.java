/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 24-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.security.EncryptPassword;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.LoadSearchedSymptoms;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomsCalculationInformation;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


 /**
 * Gets the information from the registration form and saves this in the UserForm class.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

     /**
      * the search history database.
      */
    @Autowired
    SearchHistoryRepository searchHistoryRepository;

     /**
      * the search history database.
      */
    @Autowired
    UserRepository userRepository;

     /**
      * the jdbc template.
      */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
     * and various simple DataSource implementations.
     */
    @Autowired
    DataSource dataSource;

     /**
      * on page load, the history and statistics models are updated by extracting data from database and updating model.
      * @param model the Thymeleaf model
      * @param user the username
      * @return homepage
      */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Model model, final User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        SymptomsCalculationInformation symptomsCalculationInformation =
                new SymptomsCalculationInformation(userRepository, searchHistoryRepository);
        try {
            /*calculate statistics*/
            symptomsCalculationInformation.calculateSymptomsSearch();
            /*place updated information on model.*/
            model.addAttribute("statistics", symptomsCalculationInformation.getStatisticalInformation());
        } catch (Exception e) {
        }
        /*if user is logged in the search history of his account is updated and placed on the model*/
        if (!username.equals("anonymousUser")) {
            LoadSearchedSymptoms loadHistory = new LoadSearchedSymptoms(userRepository, searchHistoryRepository);
            List<SearchHistory> searchHistory = loadHistory.loadSearchedSymptoms();
            model.addAttribute("history", searchHistory);
        }
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

            return "redirect:/";
        }
        if (!bindingResult.hasErrors()) {
            String encrypted = EncryptPassword.encryptPassword(user.getPassword());
            /*password is encrypted*/
            user.setPassword(encrypted);
            user.setConfirmPassword(encrypted);
            user.setEnabled(true);
            user.setCreatedAt(LocalDateTime.now());
            /*if there are no errors. The user gets the standardvalues plus date and time of creation*/
            try {
                userRepository.save(user);
            } catch(Exception e){
            }
            return "redirect:/";
        }
        return "redirect:/";

    }

     /**
      * @param username of the user at registration form.
      * @return if the user already exists or not in String (True or False)
      * @throws IOException IOexception
      */
    @RequestMapping(value = "getRegisteredUsers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String RegisteredUser(String username) throws IOException {
        /*checks if username already exists*/
        if (userRepository.findByUsername(username) == null) {
            return "False";
        } else {
            return "True";
        }

    }
}
