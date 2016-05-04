package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 25-4-16.
 */

import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SearchHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

@Controller
public class SearchController extends WebMvcConfigurerAdapter {

    List<SearchHis> ListWithMysqlInformation;

    /**
     * Make the jdbcTemplate usable in the class.
     * This is the database connector.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
     * and various simple DataSource implementations.
     */
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public Object executeHis(final User user, HttpSession session) {

//        GetSearchHistory dit = new GetSearchHistory();

        SearchHis ss = new SearchHis();

//        ListWithMysqlInformation = dit.GetSearchHistory("klaasje", jdbcTemplate);

//        model.addAttribute("ListWithMysqlInformation", ListWithMysqlInformation); // This is important

//        UserForm personform = new UserForm();
//        personform.setUsername("HANS");

        session.setAttribute("mySessionAttribute", "someValue");

        System.out.println("HIER");

        return ListWithMysqlInformation;
    }


}