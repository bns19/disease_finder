/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bnsikkema on 8-4-16.
 * Part of code created by mslofstra
 */
package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.disease.Disease;
import nl.bioinf.diseasefinderSpring.disease.DiseaseCollection;
import nl.bioinf.diseasefinderSpring.disease.ScoreCalculator;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;

/**
 * This class regulates the processing of disease terms and gets the corresponding diseases which it will return to the
 * SymptomProcessingController.
 */
public class SymptomProcessor {

    /**
     * public constructor.
     *
     * @param symptoms the given symptoms as string
     */
    public SymptomProcessor(final String symptoms) {

        processSymptoms(symptoms);
    }


    /**
     * the retrieved diseases based on the given symptoms.
     */
    private String diseases;
    private List<List> diseaseData = new ArrayList();

    /**
     * this method regulates the core processingpart.
     *
     * @param symptoms the given symptoms as string
     */
    private void processSymptoms(final String symptoms) {
        StringBuilder sb = new StringBuilder();

        String[] symptomsList = symptoms.split(",");

        try {
            DiseaseCollection diseases = new DiseaseCollection(symptomsList);
            ScoreCalculator scoreCalculator = new ScoreCalculator(diseases);
            HashMap<String, Disease> hashMapOfDiseases = diseases
                    .getDiseaseCollection();
            Iterator it = hashMapOfDiseases.entrySet().iterator();
            while (it.hasNext()) {
                List<Object> featuresOfCurrentDisease = new ArrayList();
                Map.Entry pair = (Map.Entry) it.next();
                Disease disease = (Disease) pair.getValue();
              //  sb.append(disease.printSummary());
                featuresOfCurrentDisease.add(disease.getTitle());
                featuresOfCurrentDisease.add((disease.getMimNumber()));
                featuresOfCurrentDisease.add(disease.getMatches());
                this.diseaseData.add(featuresOfCurrentDisease);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.diseases = sb.toString();
    }

    /**
     * diseasegetter.
     *
     * @return the diseases
     */
    public String getDiseases() {
        return this.diseases;
    }

    public List getDiseaseData() {
        return this.diseaseData;
    }

}
