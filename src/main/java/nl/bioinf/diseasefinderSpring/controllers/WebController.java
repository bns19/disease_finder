/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.security.EncryptPassword;
//import nl.bioinf.diseasefinderSpring.symptomsdatabase.LoadSearchedSymptoms;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.swing.*;
import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Gets the information from the registration form and saves this in the UserForm class.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
     * and various simple DataSource implementations.
     */
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Model model, final User user) {
        //session.setAttribute("mySessionAttribute", "someValue");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        SymptomsCalculationInformation symptomsCalculationInformation =
                new SymptomsCalculationInformation(userRepository, searchHistoryRepository);
        try {
            symptomsCalculationInformation.calculateSymptomsSearch();

        model.addAttribute("statistics", symptomsCalculationInformation.getStatisticalInformation());
        } catch (Exception e) {}
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

            return "home";
        }
        if (!bindingResult.hasErrors()) {
            String encrypted = EncryptPassword.encryptPassword(user.getPassword());

            user.setPassword(encrypted);
            user.setConfirmPassword(encrypted);
            user.setEnabled(true);
            user.setCreatedAt(LocalDateTime.now());

            userRepository.save(user);
            return "home";
        }
        return "home";

    }

}
