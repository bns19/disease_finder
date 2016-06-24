/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 23-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import model.Findtrait;
import nl.bioinf.diseasefinderSpring.beans.DiseaseInformation;
import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.searchpackage.SearchSystem;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SaveSearchedSymptoms;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomProcessor;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomsCalculationInformation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class now contains the controller responsible for finding diseases based on  symptoms given by the user.
 * It also contains the disease information retrieving controller.
 *
 */
@Controller
public class SymptomProcessingController {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

    /**
     *
     * @param userRepository
     * @param searchHistoryRepository
     */
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
     * @param shortSymptoms the leafsymptoms
     * @param algorithm the user specified algorithm
     * @param runtime the user specified runtime
     * @param queryType the type of query that has to be used (short symptoms or symptoms)
     * @return The list of founded diseases
     * @Throws Exception an exception for the case the database has not been implemented well
     */
    @RequestMapping(value = "/sendSymptoms",  method = RequestMethod.POST)
    @ResponseBody
    public List processInput(final String symptoms, final String shortSymptoms, final String algorithm, final int runtime, final String queryType) throws Exception{
        try {
            SaveSearchedSymptoms saveSymptoms = new SaveSearchedSymptoms(userRepository, searchHistoryRepository);
            saveSymptoms.saveSymptoms(shortSymptoms, symptoms);
            /*symptoms saved in database*/
            SymptomsCalculationInformation symptomsCalculationInformation =
                    new SymptomsCalculationInformation(userRepository, searchHistoryRepository);
            symptomsCalculationInformation.calculateSymptomsSearch();
        } catch(Exception e) {
            System.out.println("Database was not implemented");
        }
        String symptomsToSearch;
        if (queryType.equals("long")) {
            /*query type that is selected is checked here*/
            symptomsToSearch = symptoms;
        } else {
            symptomsToSearch = shortSymptoms;
        }
        List returnableDiseaseList;
        if (algorithm.equals("j")) {
            /*if J-algorithm is selected, this will happen*/
            List<List> diseasesList = new ArrayList();
            SearchSystem ss = new SearchSystem(symptomsToSearch, runtime);
            Findtrait diseases = ss.getResults();
            for (List<String> i : diseases.getFinalres().keySet()) {
                /*the matches of the J-algorithm are placed in a List*/
                List<String> bridgeList = new ArrayList();
                for (String contents : i) {
                    /*to prevent current modification exceptions this bridgelist is used*/
                    bridgeList.add(contents);
                }
                bridgeList.add(diseases.getFinalres().get(i));
                diseasesList.add(bridgeList);
                /*diseases and matches added to diseases*/
                System.out.println("Disorder    " + i.get(1));
                System.out.println("id    " + i.get(0));
                System.out.println("match    " + diseases.getFinalres().get(i) + "\n");
            }
            returnableDiseaseList = diseasesList;
        } else {
            /*if AM-algorithm is selected, this will happen*/
            SymptomProcessor sp = new SymptomProcessor();
            sp.startProcessing(symptomsToSearch);
            returnableDiseaseList =  sp.getDiseaseData();
        }
        return returnableDiseaseList;
    }

    /**
     * loadDisease.
     * This requestmapping method loads the disease information of the disease that the user selected.
     * @param omimNumber the omimnumber of the disease.
     * @param symptoms the symptoms of the disease.
     * @return The information of the disease in HTML-format.
     * @throws JSONException JSONException.
     * @throws IOException JSONException.
     */
    @RequestMapping(value = "/diseaseInformation", method = RequestMethod.POST)
    @ResponseBody
    public DiseaseInformation loadDisease(final String omimNumber, final String symptoms, final String algorithm) throws JSONException, IOException {
            String[] symptomSet = symptoms.split(",");
            DiseaseCollection diseases = new DiseaseCollection(symptomSet);
            DiseaseInformation information = diseases.getInfoOfDisease(omimNumber, algorithm);
            /*disease information is extracted and returned*/
            return information;
    }
}



