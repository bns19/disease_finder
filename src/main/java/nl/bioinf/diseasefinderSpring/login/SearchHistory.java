package nl.bioinf.diseasefinderSpring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by henridupon on 3/21/2016.
 */

public class SearchHistory extends WebMvcConfigurerAdapter {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public String SearchHistory(String searchhistory) {

        System.out.println("symptoms: " + searchhistory);

        // input the form data //
        String inputMysql = String.format("INSERT INTO History (username, history) VALUES ('%s', '%s')", "Henri", searchhistory);

//                String inputMysql = String.format("INSERT INTO User (username) VALUES ('%s')", searchhistory);


//        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }

}
