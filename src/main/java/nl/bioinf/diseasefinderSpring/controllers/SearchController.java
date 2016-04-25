package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 25-4-16.
 */

import nl.bioinf.diseasefinderSpring.symptomsdatabase.GetSearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;
import java.util.List;

@Configuration
public class MyConfiguration {
    @Bean(name = "urlService")
    public UrlService urlService() {
        GetSearchHistory gh = new GetSearchHistory(); // some implementation
        return (UrlService) gh;
    }


    public interface UrlService {
        String getApplicationUrl();
    }
}