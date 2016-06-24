/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema on 8-4-16.
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
     */
    public SymptomProcessor() {
    }

    /**
     * start function.
     * @param symptoms the symptoms given by the user.
     */
    public void startProcessing(final String symptoms) {
        processSymptoms(symptoms);
    }

    /**
     * the retrieved diseases based on the given symptoms.
     */
    private String diseases;
    /**
     * List of disease data.
     */
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
                featuresOfCurrentDisease.add((disease.getMimNumber()));
                featuresOfCurrentDisease.add(disease.getTitle());
                featuresOfCurrentDisease.add(disease.getMatches());
                featuresOfCurrentDisease.add(disease.getHits());
                featuresOfCurrentDisease.add(disease.getScore());

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
     * disease getter.
     *
     * @return the diseases
     */
    public String getDiseases() {
        return this.diseases;
    }

    /**
     * diseasedata getter.
     * @return the disease data
     */
    public List getDiseaseData() {
        return this.diseaseData;
    }

    /**
     * disease data setter.
     * @param diseaseDataII the disease data.
     */
    public void setDiseaseData(final List diseaseDataII) {
        this.diseaseData.add(diseaseDataII);
    }

}
