package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.login.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by hjdupon on 20-4-16.
 */

@Controller
public class SaveSearchSymptoms {

    @RequestMapping(value = "/saveUser", method = RequestMethod.GET)
    @ResponseBody
    public void saveSearchSymptoms() {

        System.out.println("dit");
    }

}