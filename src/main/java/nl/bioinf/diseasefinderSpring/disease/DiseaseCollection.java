package nl.bioinf.diseasefinderSpring.disease;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
//import nl.bioinf.DiseaseFinder.connection.OmimDataRetriever;
//import nl.bioinf.DiseaseFinder.dataFinder.DiseasePhenotypeGetter;
import nl.bioinf.diseasefinderSpring.connection.OmimDataRetriever;
import nl.bioinf.diseasefinderSpring.dataFinder.DiseasePhenotypeGetter;
import org.json.JSONException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * This class collects all the possible diseases which are found.
 *
 * @author mkslofstra and aroeters
 */
public class DiseaseCollection {

    /**
     * diseaseCollection is the collection of all found diseases given a
     * phenotype. This HashMap has as id the OMIM number and as value the
     * Disease object which belongs to this OMIM number.
     *
     * @author mkslofstra
     */
    private LinkedHashMap<String, Disease> diseaseCollection
            = new LinkedHashMap();

    /**
     * DiseaseCollector is the constructor of the class.
     *
     * @param features the features to look for
     * @throws JSONException when the page is not valid JSON
     * @throws IOException if the URL is invalid.
     * @author mkslofstra
     */
    public DiseaseCollection(final String[] features) throws JSONException,
            IOException {
        try {
            for (String i : features) {
                if(features.length < 2 && i.isEmpty()) {
                    throw new IllegalStateException("there has to be at least one feature");
                } else if(features.length <2 && !i.matches(".*[a-zA-Z]+.*")) {
                    throw new IllegalArgumentException("At least one feature containing a possible term is required (no letters detected)");
                }
            }
        } catch(AssertionError ae) {
            throw ae;
        } catch(StringIndexOutOfBoundsException siobe) {
            throw siobe;
        }
        HashMap diseaseMatches = this.getOmimNumbers(features);
        this.fillDiseaseCollection(diseaseMatches);
    }

    /**
     * This void fills the diseasecollection.
     *
     * @param diseaseMatches the matches a disease has.
     * @throws IOException when URL is malformed.
     * @throws JSONException when json structure is invalid.
     * @author mkslofstra
     */
    private void fillDiseaseCollection(final HashMap diseaseMatches)
            throws IOException, JSONException {
        Iterator it = diseaseMatches.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String id = (String) pair.getKey();
            Disease disease = this.getDiseaseContent(id);
            String match = (String) pair.getValue();
            List matches = Arrays.asList(match.split(","));
            try {
                //if the disease is not empty
                disease.setMatches(matches);
            } catch (Exception e) {
                String importance = "not";
            }
            addToDiseaseCollection(disease, id);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * This function sorts the diseasecollection.
     *
     * @author mkslofstra
     */
    public final void sortDiseaseCollection() {
//        System.out.println(dise);
        List<Disease> diseaseList = new ArrayList(diseaseCollection.values());
        this.diseaseCollection = new LinkedHashMap();
        Collections.sort(diseaseList);
        for (Object sortedDisease : diseaseList) {
            Disease disease = (Disease) sortedDisease;
            addToDiseaseCollection(disease, disease.getMimNumber());
        }
    }

    /**
     * getDiseaseCollection is the getter of diseaseCollection.
     *
     * @return diseaseCollection the collection of all found diseases.
     * @author mkslofstra
     */
    public final HashMap<String, Disease> getDiseaseCollection() {
        return diseaseCollection;
    }

    /**
     * getOmimNumbers gets the id's of the possible diseases.
     *
     * @param features is the list of features to look for.
     * @throws JSONException when page is invalid JSON.
     * @throws IOException when URL is invalid.
     * @return diseases, an ArrayList of diseases.
     * @author mkslofstra and aroeters
     */
    private HashMap<String, String> getOmimNumbers(final String[] features)
            throws JSONException, IOException {
        // Using the config files for security reasons made by aroeters


        Properties config = new Properties();

        InputStream in = getClass().getResourceAsStream(
                "/config/config.properties");

       // System.out.println("this is the result of the inputstream: "+ in);


        config.load(in);
       // System.out.println("this is the result of the properties: "+ config);
        String url = config.getProperty("omimUrlNumbers");
        String apiKey = config.getProperty("omimKey");
        in.close();
        OmimDataRetriever omimResultGetter = new OmimDataRetriever(url, apiKey);
        HashMap<String, String> diseases = omimResultGetter
                .getOmimNumbers(features);

        return diseases;
    }

    /**
     * Retrieves the content of the disease.
     *
     * @param disease the disease to get the content from
     * @return a disease Object
     * @throws IOException if the URL is malformed
     * @throws JSONException if the JSON format is not correct
     * @author mkslofstra
     */
    private Disease getDiseaseContent(final String disease) throws IOException,
            JSONException {
        DiseasePhenotypeGetter diseasePhenotype
                = new DiseasePhenotypeGetter(disease);
        return diseasePhenotype.getDisease();
    }

    /**
     * the setter of diseaseCollection.
     *
     * @param disease the disease of the id.
     * @param mimNumber the id of the disease.
     * @author mkslofstra
     */
    public final void addToDiseaseCollection(
            final Disease disease, final String mimNumber) {
        //input should not be null
        if (mimNumber != null && disease != null) {
            diseaseCollection.put(mimNumber, disease);
        }
        // this is covered later
    }

    /**
     * getInfoOfDisease needs an OMIM number and gets the information of this
     * disease.
     *
     * @param omimNumber is the OMIM number of the disease a person wants
     * information of.
     * @author mkslofstra
     * @return info the information about the disease.
     */
    public final String getInfoOfDisease(final String omimNumber) {
        String info = this.diseaseCollection.get(omimNumber).toString();
        return info;
    }
}
