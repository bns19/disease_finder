package Controllers;

import Beans.DiseaseSymptoms;
import disease.Disease;
import disease.DiseaseCollection;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import score.ScoreCalculator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static java.lang.System.out;
/**
 * Created by bnsikkema on 23-2-16.
 */


@Controller
public class ControllerClass {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexPage(DiseaseSymptoms diseaseSymptoms) {
        return "index";
    }

    @RequestMapping(value="/",  method= RequestMethod.POST)
    public void processInput(DiseaseSymptoms diseaseSymptoms, Model model) {
        try {
            String[] symptoms = diseaseSymptoms.getSymptomList();
            DiseaseCollection diseases = new DiseaseCollection(symptoms);
            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
            HashMap<String, Disease> hashMapOfDiseases = diseases
                    .getDiseaseCollection();
            Iterator it = hashMapOfDiseases.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Disease disease = (Disease) pair.getValue();
                out.println(disease.printSummary());
                it.remove(); // avoids a ConcurrentModificationException
        }
    } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        out.close();
    }
    }
}
