/**
 * Project: Disease Finder
 * Theme 11/12
 * Bas Sikkema & Henri du Pon
 */

package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class regulates the views of websites when controllers return a page.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Map a view controller to the given URL path (or pattern) in order to render a response with a pre-configured
     * status code and view.
     *
     * @param registry
     * @ViewControllerRegistry Assists with the registration of simple automated controllers pre-configured with status
     * code and/or a view.
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/diseaseResults").setViewName("diseaseResults");
    }


}