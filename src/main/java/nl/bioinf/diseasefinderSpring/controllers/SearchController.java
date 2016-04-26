package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 25-4-16.
 */

import nl.bioinf.diseasefinderSpring.symptomsdatabase.GetSearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class SearchController extends WebMvcConfigurerAdapter {

    List<SearchHis> ListWithMysqlInformation;

    /**
     * Make the jdbcTemplate usable in the class.
     * This is the database connector.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public Object executeHis(final PersonForm personForm, HttpSession session) {

        GetSearchHistory dit = new GetSearchHistory();

        SearchHis ss = new SearchHis();

        ListWithMysqlInformation = dit.GetSearchHistory("klaasje", jdbcTemplate);

//        model.addAttribute("ListWithMysqlInformation", ListWithMysqlInformation); // This is important

        PersonForm personform = new PersonForm();
        personform.setUsername("HANS");

        session.setAttribute("mySessionAttribute", "someValue");

        System.out.println("HIER");

        return ListWithMysqlInformation;
    }

//    @ResponseBody
//    @RequestMapping(value = "/history", method = RequestMethod.POST)
//    public String execute(@Valid final Model model, final BindingResult bindingResult) {
//
//        return "/home";
//    }
}