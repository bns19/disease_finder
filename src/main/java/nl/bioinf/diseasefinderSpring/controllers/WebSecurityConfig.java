/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * "@Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 * "@EnableWebSecurity enables websecurity.
 * "@EnableGlobalMethodSecurity easily secures methods with java configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Make the jdbcTemplate usable in the class.
     * This is the database connector.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * @param http is similar to Spring Security's XML element in the namespace configuration.
     *             It allows configuring web based security for specific http requests.
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    /**
     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
     * and various simple DataSource implementations.
     */
    @Autowired
    DataSource dataSource;


    /**
     * @param auth auth.
     * @throws Exception an exception.
     * The @Autowired annotation provides fine-grained control over where and how autowiring should be accomplished.
     * The annotation can be used to autowire bean on the setter method just like @Required annotation, constructor,
     * a property or methods with arbitrary names and/or multiple arguments.
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled from User where username = ?")
                .authoritiesByUsernameQuery("select username, authority from User where username = ?");
    }


    /**
     * passwordEncoder encodes the password given by the user.
     *
     * @return encoded password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}