package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOFileReader;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOJsonObjectCreator;
import nl.bioinf.diseasefinderSpring.HPOProcessor.HPOTerm;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bnsikkema on 18-4-16.
 */
@Controller
public class SymptomsSearchingController {

    @RequestMapping(value = "/autocompleteSymptoms")
    @ResponseBody
    public String processRequest(String query) throws IOException {
        System.out.println(query + "++++++++++++++rsgfewt43ttttttt2wrwer+++");
        boolean test = true;
/////Hoe geven we een argument mee?
        HPOJsonObjectCreator hj = new HPOJsonObjectCreator();

        String path = HPOJsonObjectCreator.class.getClassLoader()
                .getResource("config/hp.obo").toString();
        HPOFileReader hr = new HPOFileReader(path.split(":")[1]);
        HashMap collection = hr.readFile().getHPOHashMap();
        ArrayList<String> terms = new ArrayList<String>();
        for (Object term : collection.values()) {
            HPOTerm idGetter = (HPOTerm) term;
            terms.add(idGetter.getName());
        }
//        response.setContentType("text/html");
//        response.setHeader("Cache-control", "no-cache, no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "-1");
        JSONArray arrayObj = new JSONArray();
        //String query = request.getParameter("term");
        query = query.toLowerCase();
        System.out.println(query);
        for (String term : terms) {
            String country = term.toLowerCase();
            System.out.println(country);
            if (country.startsWith(query.toLowerCase())) {
                arrayObj.put(term);
            }
        }
//        out.println(arrayObj.toString());
//        out.close();
        return arrayObj.toString();
    }


}
