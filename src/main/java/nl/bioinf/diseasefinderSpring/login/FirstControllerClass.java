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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bnsikkema on 23-2-16.
 * In progress
 * The application now prints the founded diseases based on the provided symptoms to screen in a mechanical way.
 * The first servlet is therefore sucesfully implemented.
 * Implementing the next servlet is in progress.
 * The code that has been silenced by comments is the content of the servlet with a few alterations to made it fit Spring.
 * It has not yet been fixed however.
 *
 */


@Controller
public class FirstControllerClass {
    @RequestMapping(value="/trythis", method = RequestMethod.GET)
    public String indexPage(DiseaseSymptoms diseaseSymptoms) {
        return "frontpage";
        /**link to formPage**/
    }




    @RequestMapping(value="/diseaseResults",  method= RequestMethod.POST)
    @ResponseBody
    public String processInput(HttpServletResponse response, DiseaseSymptoms diseaseSymptoms, Model model) {
        try {
            //PrintWriter out = response.getWriter();
            String[] symptoms = diseaseSymptoms.getSymptomList();
            DiseaseCollection diseases = new DiseaseCollection(symptoms);
            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
            HashMap<String, Disease> hashMapOfDiseases = diseases
                    .getDiseaseCollection();
            Iterator it = hashMapOfDiseases.entrySet().iterator();
            response.getWriter().println("<html><body>");
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Disease disease = (Disease) pair.getValue();
                //out.println(disease.printSummary());
                /*The summary gives the de disease-results based on the given symptoms in a html output!!!!!*/
                /**Results do work**/
                response.getWriter().println(disease.printSummary());

                System.out.println(disease.printSummary());
                it.remove(); // avoids a ConcurrentModificationException
        }
            response.getWriter().println("</body></html>");


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
