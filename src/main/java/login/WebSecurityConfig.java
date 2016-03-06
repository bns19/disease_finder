package login;

import login.password.PasswordPass;
import login.password.PasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                When adding websites to the antMatcher, these websites are not mandatory to be logged in (/** is all)
                .antMatchers("/", "/home", "/form", "/results", "/index", "/indexTest", "/frontpage", "/trythis", "/diseaseResults").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                give the login page
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public String configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");


//        String username = "hans";
//        String SQLuser = String.format("select username from user where username = '%s'", username);
//        Object mysqlSelectUser = new Object();
//        mysqlSelectUser = jdbcTemplate.queryForList(SQLuser, new MapSqlParameterSource());
//        System.out.println(mysqlSelectUser.toString());
//
//        String SQLpass = String.format("select password from user where username = '%s'", username);
//        Object mysqlSelectPass = new Object();
//        mysqlSelectPass = jdbcTemplate.queryForList(SQLpass, new MapSqlParameterSource());
//        System.out.println(mysqlSelectPass.toString());

//        PasswordPass.PasswordPass(mysqlSelectPass.toString());
//        PasswordUser.PasswordUser(mysqlSelectUser.toString());


        auth.inMemoryAuthentication().withUser("henri").password("12345").roles("USER");
        return "redirect:/results";
    }

}