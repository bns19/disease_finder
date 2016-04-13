package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import nl.bioinf.diseasefinderSpring.login.SearchHistory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import nl.bioinf.diseasefinderSpring.disease.ScoreCalculator;
import java.io.IOException;

/**
 * Created by bnsikkema on 23-2-16.
 *This class now contains the controller responsible for finding diseases based on (dummy) symptoms given by the user.
 * The functionality is succesfully ported.
 *
 * TODO: the output of the controllers should not be HTML, the HTML code will be deleted from the classes.
 *
 */
@Controller
public class SymptomProsessingController {
    /**
     * Make the jdbcTemplate approachable
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * processInput.
     * This requestmapping method takes the symptom(s) provided by the user and uses them to find the corresponding disease(s).
     * @param symptoms the given symptoms (in plain text)
     * @return The disease(s) in HTML-format (for now).
     */
    @RequestMapping(value="/sendSymptoms",  method= RequestMethod.POST)
    @ResponseBody
    public String processInput(String symptoms) {
        /* Save the search history of the user */
        SearchHistory sh = new SearchHistory();
        //sh.SearchHistory(symptoms, jdbcTemplate);
        SymptomProcessor sp = new SymptomProcessor(symptoms);
        return sp.getDiseases();
    }

    /**
     * loadDisease.
     * This requestmapping method loads the diseaseinformation of the disease that the user selected.
     * @param omimNumber the omimnumber of the disease.
     * @param symptoms the symptoms of the disease.
     * @return The information of the disease in HTML-format.
     * @throws JSONException
     * @throws IOException
     */
    @RequestMapping(value="/diseaseInformation", method = RequestMethod.POST)
    @ResponseBody
    public String loadDisease(String omimNumber, String symptoms) throws JSONException, IOException{
        String[] symptomSet = symptoms.split(",");
        DiseaseCollection diseases = new DiseaseCollection(symptomSet);
        ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
        String information = diseases.getInfoOfDisease(omimNumber);
        return information;
    }

}



