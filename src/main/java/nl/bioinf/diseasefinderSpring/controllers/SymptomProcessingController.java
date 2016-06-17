/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bnsikkema on 23-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import model.Findtrait;
import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import nl.bioinf.diseasefinderSpring.disease.ScoreCalculator;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.searchPackage.SearchSystem;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SaveSearchedSymptoms;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomProcessor;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomsCalculationInformation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class now contains the controller responsible for finding diseases based on (dummy) symptoms given by the user.
 * The functionality is successfully ported.
 *
 */
@Controller
public class SymptomProcessingController {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public SymptomProcessingController(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    /**
     * Make the jdbcTemplate approachable
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors.
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * processInput.
     * This requestmapping method takes the symptom(s) provided by the user and uses them to find the corresponding
     * disease(s).
     * @param symptoms the given symptoms (in plain text)
     * @return The disease(s) in HTML-format (for now).
     */
    @RequestMapping(value = "/sendSymptoms",  method = RequestMethod.POST)
    @ResponseBody
    public List processInput(final String symptoms, final String shortSymptoms, final String algorithm, final int runtime, final String queryType) throws Exception{
        try {
            SaveSearchedSymptoms saveSymptoms = new SaveSearchedSymptoms(userRepository, searchHistoryRepository);
            saveSymptoms.saveSymptoms(shortSymptoms, symptoms);

            SymptomsCalculationInformation symptomsCalculationInformation =
                    new SymptomsCalculationInformation(userRepository, searchHistoryRepository);
            symptomsCalculationInformation.calculateSymptomsSearch();
        } catch(Exception e) {}
        String symptomsToSearch;
        if (queryType.equals("long")) {
            symptomsToSearch = symptoms;
        } else {
            symptomsToSearch = shortSymptoms;
        }
        List returnableDiseaseList;
        if (algorithm.equals("j")) {
            List<List> diseasesList = new ArrayList();
//            List<String> diseasesList = new ArrayList();
            SearchSystem ss = new SearchSystem(symptomsToSearch, runtime);
            Findtrait diseases = ss.getResults();
            System.out.println(diseases.getFinalres().keySet()+ "jeunards keyset");

            for (List<String> i : diseases.getFinalres().keySet()) {
                List<String> bridgeList = new ArrayList();
                for (String contents : i) {
                    bridgeList.add(contents);
                }

                bridgeList.add(diseases.getFinalres().get(i));
                diseasesList.add(bridgeList);
                System.out.println("Disorder    " + i.get(1));
                System.out.println("id    " + i.get(0));
                System.out.println("match    " + diseases.getFinalres().get(i) + "\n");
            }
            returnableDiseaseList = diseasesList;
            System.out.println(returnableDiseaseList);

        } else {
            SymptomProcessor sp = new SymptomProcessor(symptomsToSearch);
            returnableDiseaseList =  sp.getDiseaseData();
        }
        return returnableDiseaseList;
    }

    /**
     * loadDisease.
     * This requestmapping method loads the diseaseinformation of the disease that the user selected.
     * @param omimNumber the omimnumber of the disease.
     * @param symptoms the symptoms of the disease.
     * @return The information of the disease in HTML-format.
     * @throws JSONException JSONException.
     * @throws IOException JSONException.
     */
    @RequestMapping(value = "/diseaseInformation", method = RequestMethod.POST)
    @ResponseBody
    public DiseaseInformation loadDisease(final String omimNumber, final String symptoms) throws JSONException, IOException {
        String[] symptomSet = symptoms.split(",");
        DiseaseCollection diseases = new DiseaseCollection(symptomSet);
        ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
        DiseaseInformation information = diseases.getInfoOfDisease(omimNumber);
        return information;
    }

//    @RequestMapping(value = "/diseaseInformation", method = RequestMethod.POST)
//    @ResponseBody
//    public String loadDisease(final String omimNumber, final String symptoms) throws JSONException, IOException {
//        String[] symptomSet = symptoms.split(",");
//        DiseaseCollection diseases = new DiseaseCollection(symptomSet);
//        ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
//        String information = diseases.getInfoOfDisease(omimNumber);
//        return information;
//    }

}



