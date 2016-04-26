/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 3/21/2016.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * A Class that controls the search history.
 */
@Service
public class SearchHistory extends WebMvcConfigurerAdapter {

    /**
     * jdbc template autowiring.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * public constructor.
     */
    public SearchHistory() {
    }

    /**
     * @param searchhistory = the symptoms that are looked for.
     * @param jdbcTemplate  jdbc template.
     * @return website.
     */
    public String searchHistory(final String searchhistory, final NamedParameterJdbcTemplate jdbcTemplate) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Date datetime = new Date();

        String inputMysql = String.format("INSERT INTO history (username, searchedsymptoms, datetime) VALUES ('%s', '%s', '%s')",
                username, searchhistory, datetime);

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }


}
