package login;

import login.password.PasswordPass;
import login.password.PasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                When adding websites to the antMatcher, these websites are not mandatory to be logged in (/** is all)
                .antMatchers("/", "/home", "/form", "/results", "/index", "/indexTest", "/frontpage", "/trythis", "/diseaseResults", "/login2").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public String configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        String sql = "select username from User";
        Object mysqlSelectUser = jdbcTemplate.queryForList(sql, new MapSqlParameterSource());
        mysqlSelectUser = mysqlSelectUser.toString().replace("[","");
        mysqlSelectUser = mysqlSelectUser.toString().replace("]","");

        System.out.println(mysqlSelectUser.toString());

        List<String> usernameList = Arrays.asList(mysqlSelectUser.toString().split(","));

        for (String items : usernameList){
            String AdjustedItem = PasswordUser.PasswordPass(items);
            System.out.println(AdjustedItem);

        }

        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("henri").password("12345").roles("USER");
        return "redirect:/results";
    }


}