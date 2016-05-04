/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bnsikkema on 23-2-16.
 */
package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

//    /**
//     * Make the jdbcTemplate approachable
//     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors.
//     */
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    GetSearchHistory getSearchHistory;


//    /**
//     * processInput.
//     * This requestmapping method takes the symptom(s) provided by the user and uses them to find the corresponding
//     * disease(s).
//     * @param symptoms the given symptoms (in plain text)
//     * @return The disease(s) in HTML-format (for now).
//     */
//    @RequestMapping(value = "/sendSymptoms",  method = RequestMethod.POST)
//    @ResponseBody
//    public String processInput(final String symptoms) {
//        /* Save the search history of the user */
//
//        SymptomProcessor sp = new SymptomProcessor(symptoms);
//
//        SearchHistory sh = new SearchHistory();
//        sh.searchHistory(symptoms, jdbcTemplate);
//
////        GetSearchHistory dit = new GetSearchHistory();
////        List ListWithMysqlInformation = dit.GetSearchHistory("klaasje", jdbcTemplate);
//
//        return sp.getDiseases();
//    }
//
//    /**
//     * loadDisease.
//     * This requestmapping method loads the diseaseinformation of the disease that the user selected.
//     * @param omimNumber the omimnumber of the disease.
//     * @param symptoms the symptoms of the disease.
//     * @return The information of the disease in HTML-format.
//     * @throws JSONException JSONException.
//     * @throws IOException JSONException.
//     */
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



