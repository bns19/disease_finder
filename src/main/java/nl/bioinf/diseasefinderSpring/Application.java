/**
 * Project: Disease Finder
 * Theme 11/12
 * Bas Sikkema & Henri du Pon
 */

package nl.bioinf.diseasefinderSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main class of the application.
 * "@SpringBootApplication adds a lot of convenience annotation:
 * "@configuration tags the class as a source of bean definitions for the application context.
 * "@EnableAutoConfiguration adds beans based on classpath setting, other beans and property settings.
 * "@EnableWebMvc tags the class as a web application and activates key behaviors.
 * "@ComponentScan tells spring to look for specific configuration and services and allows it to find the controllers.
 */
@SpringBootApplication
public class Application {

    /**
     * Main class.
     *
     * @param args args.
     */
    public static void main(final String args[]) {
        SpringApplication.run(Application.class, args);
    }
}


