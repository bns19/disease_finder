package nl.bioinf.diseasefinderSpring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by henridupon on 3/21/2016.
 */

public class SearchHistory {
    String history;


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/sendSymptoms", method = RequestMethod.GET)
    public void processSearchSymptoms(String symptoms) {

        this.history = symptoms;
        System.out.println("symptoms1");
    }


    @RequestMapping(value = "/sendSymptoms", method = RequestMethod.POST)
    public String saveHistory() {

        System.out.println("symptoms2");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS History(" + "id SERIAL, username VARCHAR(255)," +
                        "email VARCHAR(255),history VARCHAR(255))"
                , new MapSqlParameterSource());

        // input the form data //
        String inputMysql = String.format("INSERT INTO User (username, email, history) VALUES ('%s', '%s', '%s')",
                "Henri",
                "hjdupon@gmail.com",
                this.history);

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

        return "/sendSymptoms";
    }

}
