package nl.bioinf.diseasefinderSpring.Database;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hjdupon on 30-3-16.
 */
@Service
public class MySQLCreateTables {

    /**
     * Make the jdbcTemplate approachable
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Method that created a user table and a history table in the MySQL database
     * @param jdbcTemplate
     */
    public void CreateUserTableMySQL(NamedParameterJdbcTemplate jdbcTemplate){

        /* Create User table */
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(255), " +
                        "email VARCHAR(255), username VARCHAR(255), birthdate VARCHAR(10), authority VARCHAR(10), enabled VARCHAR(10))"
                , new MapSqlParameterSource());

        /* Create history table */
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS History(id SERIAL, searchedsymptoms VARCHAR(255), username VARCHAR(255))"
                , new MapSqlParameterSource());
    }
}
