package nl.bioinf.diseasefinderSpring.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by henridupon on 4/20/2016.
 */
public class ShowSearchHistory {

    /**
     * Make the jdbcTemplate approachable.
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void selectSearchHistoryFromDatabase(){


        System.out.println("selectSearchHistoryFromDatabase");

        String sql = "select searchhistory from history where username = ?";


    }

}
