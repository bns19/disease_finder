/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 3/21/2016.
 */
package nl.bioinf.diseasefinderSpring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO: make history name dynamic (who is logged in), if no one is logged in name becomes "anonimous".
 * A Class that controls the search history.
 */
public class SearchHistory extends WebMvcConfigurerAdapter {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * @param searchhistory = the symptoms that are looked for.
     * @return website.
     */
    public String SearchHistory(String searchhistory, NamedParameterJdbcTemplate jdbcTemplate) {

        System.out.println("symptoms: " + searchhistory);

        // input the form data //
//        String inputMysql = String.format("INSERT INTO History (username, searchedsymptoms) VALUES ('%s', '%s')", "Henri", searchhistory);
        String inputMysql = String.format("INSERT INTO History (searchedsymptoms, username) VALUES ('%s', '%s')",searchhistory,  "Henri");

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }

}
