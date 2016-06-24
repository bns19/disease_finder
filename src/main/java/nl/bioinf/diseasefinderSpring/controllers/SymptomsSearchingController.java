/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 18-4-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.hpoprocessor.HPOFileLoader;
import nl.bioinf.diseasefinderSpring.treehandler.AutoCompleteSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.HashMap;

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
