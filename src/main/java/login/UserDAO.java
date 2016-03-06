package login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by henridupon on 3/1/2016.
 */
public class UserDAO {


    @Autowired
    static NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void UserDAO(String getFirstName, String getLastName, Integer getAge, String getPassword, String getEmail, String getUsername, String getbDate) {

        log.info("Creating tables");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS User(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), age INT(3), password VARCHAR(255), " +
                        "email VARCHAR(255), username VARCHAR(255), birthdate VARCHAR(10))"
                , new MapSqlParameterSource());

        String inputMysql = String.format("INSERT INTO User (first_name, last_name, age, password, email, username, birthdate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                getFirstName, getLastName, getAge, getPassword, getEmail, getUsername, getbDate);
        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

    }
}

