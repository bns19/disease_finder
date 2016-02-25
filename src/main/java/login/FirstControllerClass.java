package login;

import Beans.DiseaseSymptoms;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bnsikkema on 23-2-16.
 * In progress
 * The connection to the classes is made and they slumber in the application
 * The connections and classpaths do work, but a controller/application process has not been created yet
 * The application does compile however. The controller links to a "testIndex" because of some thymeleaf-errors
 * that I have not yet solved.
 *
 * The code that has been silenced by comments is the content of the servlet with a few alterations to made it fit Spring.
 * It has not yet been fixed however.
 *
 */


@Controller
public class FirstControllerClass {
    @RequestMapping(value="/trythis", method = RequestMethod.GET)
    public String indexPage(DiseaseSymptoms diseaseSymptoms) {
        String groet = "hallo dan";
        return "indexTest";
        //link to testPage
    }




//    @RequestMapping(value="/",  method= RequestMethod.POST)
//    public void processInput(DiseaseSymptoms diseaseSymptoms, Model model) {
//        try {
//            String[] symptoms = diseaseSymptoms.getSymptomList();
//            DiseaseCollection diseases = new DiseaseCollection(symptoms);
//            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
//            HashMap<String, Disease> hashMapOfDiseases = diseases
//                    .getDiseaseCollection();
//            Iterator it = hashMapOfDiseases.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                Disease disease = (Disease) pair.getValue();
//                out.println(disease.printSummary());
//                it.remove(); // avoids a ConcurrentModificationException
//        }
//    } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//        out.close();
//    }
//    }
}
