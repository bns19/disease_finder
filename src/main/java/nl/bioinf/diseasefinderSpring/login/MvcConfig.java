package nl.bioinf.diseasefinderSpring.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

//    Map a view controller to the given URL path (or pattern) in order to render a response with a pre-configured status code and view.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/form").setViewName("form");
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/indexTest").setViewName("indexTest");
        registry.addViewController("/frontpage").setViewName("frontpage");
        registry.addViewController("/trythis").setViewName("trythis");
        registry.addViewController("/diseaseResults").setViewName("diseaseResults");
        registry.addViewController("/login2").setViewName("login2");
    }

}