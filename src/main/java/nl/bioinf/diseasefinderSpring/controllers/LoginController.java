/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon on 17-6-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    /**
     * @param error
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        System.out.println(error);
        return new ModelAndView("login", "error", error);
    }
}
