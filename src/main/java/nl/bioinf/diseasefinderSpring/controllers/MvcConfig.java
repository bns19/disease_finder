package nl.bioinf.diseasefinderSpring.controllers;

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
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/form").setViewName("form");
        registry.addViewController("/frontpage").setViewName("frontpage");
        registry.addViewController("/diseaseResults").setViewName("diseaseResults");
        registry.addViewController("/trylanguage").setViewName("trylanguage");
    }

}