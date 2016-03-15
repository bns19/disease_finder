package nl.bioinf.diseasefinderSpring.login;

import nl.bioinf.diseasefinderSpring.disease.Disease;
import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import nl.bioinf.diseasefinderSpring.score.ScoreCalculator;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Created by bnsikkema on 15-3-16.
 */

@RestController
public class secondControllerClass {


    @RequestMapping(value = "/post/", method = RequestMethod.POST,   produces="application/json")
    public String processInput(String[] symptoms) {
        try {
            //PrintWriter out = response.getWriter();
            //String[] symptoms = diseaseSymptoms.getSymptomList();
            DiseaseCollection diseases = new DiseaseCollection(symptoms);
            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
            HashMap<String, Disease> hashMapOfDiseases = diseases
                    .getDiseaseCollection();
            Iterator it = hashMapOfDiseases.entrySet().iterator();
            List<Disease> diseasesInformation =  new ArrayList();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Disease disease = (Disease) pair.getValue();
                //out.println(disease.printSummary());
                /*The summary gives the de disease-results based on the given symptoms in a html output!!!!!*/
                /**Results do work**/
//                response.getWriter().println(disease.printSummary());
                diseasesInformation.add(disease);
                System.out.println(disease.printSummary());
                it.remove(); // avoids a ConcurrentModificationException
            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //out.close();
        }

        return null;
    }
}
