/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 3/21/2016.
 */
package nl.bioinf.diseasefinderSpring.symptomdatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

        // input the form data //
        String inputMysql = String.format("INSERT INTO History (searchedsymptoms, username) VALUES ('%s', '%s')",
                searchhistory, "Henri");

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }

}
