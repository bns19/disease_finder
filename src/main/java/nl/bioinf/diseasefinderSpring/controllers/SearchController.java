package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 25-4-16.
 */

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.LoadSearchedSymptoms;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController extends WebMvcConfigurerAdapter {


    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public SearchController(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }
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
    public Object executeHistoryView(final User user, HttpSession session) {

        session.setAttribute("mySessionAttribute", "someValue");

        LoadSearchedSymptoms loadHistory = new LoadSearchedSymptoms(userRepository, searchHistoryRepository);
        List<SearchHistory> searchHistory = loadHistory.loadSearchedSymptoms();

        return searchHistory;
    }


}