package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOFileReader;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOTerm;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bnsikkema on 18-4-16.
 */
@Controller
public class SymptomsSearchingController {

    @RequestMapping(value = "/autocompleteSymptoms")
    @ResponseBody
    public String processRequest(@RequestParam("term") String query) throws IOException {
        boolean test = true;
//        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();
        HashMap collection =  HPOFileLoader.LoadHPOFile();
        AutoCompleteSystem acs =  new AutoCompleteSystem(query, collection);
        return acs.getAutoCompleteObject().toString();
    }


}
