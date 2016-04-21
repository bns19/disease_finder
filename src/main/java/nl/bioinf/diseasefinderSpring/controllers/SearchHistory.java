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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

/**
 * A Class that controls the search history.
 */
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

        System.out.println("symptoms: " + searchhistory);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Date datetime = new Date();

        System.out.println(username);
        // input the form data //
        String inputMysql = String.format("INSERT INTO history (username, searchedsymptoms, datetime) VALUES ('%s', '%s', '%s')",
                username, searchhistory, datetime);

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }

}