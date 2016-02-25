package login;

/**
 * Created by hjdupon on 24-2-16.
 */
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm(PersonForm personForm) {
        return "form";
    }

//    @PreAuthorize("Admin")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }

}
