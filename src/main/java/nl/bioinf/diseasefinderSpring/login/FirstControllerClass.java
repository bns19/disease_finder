package nl.bioinf.diseasefinderSpring.login;

import nl.bioinf.diseasefinderSpring.Beans.DiseaseSymptoms;
import nl.bioinf.diseasefinderSpring.disease.Disease;
import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import nl.bioinf.diseasefinderSpring.score.ScoreCalculator;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by bnsikkema on 23-2-16.
 *This class now contains the two controllers responsible for finding diseases based on symptoms given by the user.
 * The functionality is succesfully ported.
 */


@Controller
public class FirstControllerClass {
    @RequestMapping(value="/trythis", method = RequestMethod.GET)
    public String indexPage(DiseaseSymptoms diseaseSymptoms) {
        return "frontpage";
        /**link to formPage**/
    }
    //Remove this!


    @RequestMapping(value="/sendSymptoms",  method= RequestMethod.POST)
    @ResponseBody
    public String processInput(String symptoms) {
        StringBuilder sb = new StringBuilder();
        String[] symptomsList = symptoms.split(",");
        try {
            DiseaseCollection diseases = new DiseaseCollection(symptomsList);
            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
            HashMap<String, Disease> hashMapOfDiseases = diseases
                    .getDiseaseCollection();
            Iterator it = hashMapOfDiseases.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Disease disease = (Disease) pair.getValue();
                sb.append(disease.printSummary());
                it.remove(); // avoids a ConcurrentModificationException
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

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



