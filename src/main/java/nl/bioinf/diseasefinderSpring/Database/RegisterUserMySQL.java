package nl.bioinf.diseasefinderSpring.Database;

import nl.bioinf.diseasefinderSpring.controllers.PersonForm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by hjdupon on 30-3-16.
 */
public class RegisterUserMySQL {

    public void RegisterUserMySQL(String encrypted, PersonForm personForm, NamedParameterJdbcTemplate jdbcTemplate){

        // input the form data //
        String inputMysql = String.format("INSERT INTO User (first_name, last_name, password, email, username, birthdate, authority, enabled) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                personForm.getFirstName(),
                personForm.getLastName(),
                encrypted,
                personForm.getEmail(),
                personForm.getUsername(),
                personForm.getbDate(),
                personForm.getAuthority(),
                personForm.getEnabled());

        jdbcTemplate.update(inputMysql, new MapSqlParameterSource());

    }
}