package nl.bioinf.diseasefinderSpring.dataFinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import nl.bioinf.diseasefinderSpring.bodyFeatures.FeatureCollection;
import nl.bioinf.diseasefinderSpring.connection.OmimDataRetriever;
import nl.bioinf.diseasefinderSpring.disease.Disease;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.logging.Logger;

/**
 * DiseasePhenotypeGetter gets the pgenotype of a disease.
 *
 * @author mkslofstra
 */
public class DiseasePhenotypeGetter {

    /**
     * features: The selected content of the webpage from omim in json
     * structure.
     */
    private JSONObject features;
    /**
     * featuresToFind: the features selected from the webpage where the parent
     * is true.
     */
    private final ArrayList<String> featuresToFind = new ArrayList();
    /**
     * phenotypes are the phenotypes of the disease.
     */
    private final TreeMap<String, String> phenotypes = new TreeMap();
    /**
     * Disease is the disease object of the found disease.
     */
    private Disease disease;

    /**
     * The getter of the created diseaseobject.
     * @return disease the disease object.
     */
    public final Disease getDisease() {
        return disease;
    }

    /**
     * The constructor of this class.
     *
     * @param omimNumber the id of the disease to get information from.
     * @throws IOException thrown when the url from the connector is
     * invalid.
     * @throws org.json.JSONException when the structure of the website is not
     * valid json.
     * @author mkslofstra
     */
    public DiseasePhenotypeGetter(final String omimNumber)
            throws IOException, JSONException {
        try {
            this.getOmimData(omimNumber);
            this.getFeatures();
            this.collectPhenotypes(featuresToFind);
            String title = this.getTitleOfDisease();
            this.saveDisease(omimNumber, title);
        } catch (org.json.JSONException ex) {
            //when the clinical synopsis of a disease is not available, this disease
            //is not interesting for the application.
            Logger logger = Logger.getLogger(DiseasePhenotypeGetter.class
                    .getName());
            logger.log(Level.FINEST, omimNumber + " not usefulll, clinical"
                    + " synopsis misses, which causes invalid JSON. This"
                    + " disease is excluded from the results.", ex);
        }

    }
    /**
     * getOmimData gets all the data from the omim page of a given omim number
     * in a String.
     *
     * @throws IOException when the string of the website cannot be made.
     * @throws JSONException if the stirng from the website is not valid json.
     * @return omimData the webpage of omim in a string.
     * @param omimNr the number of the disease.
     * @author mkslofstra and aroeters
     */
    private JSONObject getOmimData(final String omimNr)
            throws IOException, JSONException {
        Properties config = new Properties();
        //get information from properties file
        InputStream in = getClass().getResourceAsStream(
                "/config/config.properties");
        config.load(in);
        //save the url and the apiKey
        String url = config.getProperty("omimUrlResult");
        String apiKey = config.getProperty("omimKey");
        in.close();
        OmimDataRetriever omimConnector = new OmimDataRetriever(url, apiKey);
        features = omimConnector.getOmimResult(omimNr);
        return features;
    }

    /**
     * checkFeature: checks if the given feature is true in the json structure.
     *
     * @param feature the feature which should be checked.
     * @throws JSONException when the website has not a valid json structure.
     * @return check tells if the given feature in this disease is true or not.
     * @author mkslofstra
     */
    private Boolean checkFeature(final String feature) throws JSONException {
        String featureExists = feature + "Exists";
        //getBoolean gets the value of the given String in featureExists which
        //is in the json structure.
        Boolean check = features.getBoolean(featureExists);
        return check;
    }

    /**
     * getPhenotypeOfFeature gets the phenotype from a given feature of the
     * website in the json structure.
     *
     * @param feature the feature wherefrom the fenotype is needed.
     * @throws JSONException when the website has not a vali json structure.
     * @return fenotpe the phenotype of the feature.
     * @author mkslofstra
     */
    private String getPhenotypeOfFeature(final String feature)
            throws JSONException {
        //getString gets the string which belongs to the given
        //string in the json structure.
        String phenotype = features.getString(feature);
        return phenotype;
    }

    /**
     * collectPhenotypes collects all the phenotypes of a disease.
     *
     * @param allFeatures an arraylist which contains all the features which can
     * be true.
     * @throws JSONException when the website has not a valid json structure.
     * @author mkslofstra
     */
    private void collectPhenotypes(
            final ArrayList<String> allFeatures) throws JSONException {
        for (String feature : allFeatures) {
            //check for each feature if the value of it is true or false.
            //get the value of the feature from the json structure
            //if the feature does exist.
            String phenotype = getPhenotypeOfFeature(feature);
            //add the phenotype to the global variable phenotypes
            //which contains the full fenotype of a disease.
            phenotypes.put(feature, phenotype);
        }
    }

    /**
     * getFeatures gets all the possible features and uses checkFeature to check
     * if they are true or false, then calls the next function to get the child
     * features of these parent and adds this to featuresToFind.
     *
     * @throws JSONException when the website is not a valid json structure.
     * possible true.
     * @author mkslofstra
     */
    private void getFeatures() throws JSONException {
        String[] allFeatures;
        FeatureCollection possibleFeatures = new FeatureCollection();
        //iterate over all possible features.
        for (String pf : possibleFeatures.getGlobalFeatures()) {
            Boolean check = checkFeature(pf);
            if (check) {
                if (pf.equals("oldFormat")) {
                    Object old = features.get(pf);
                    JSONObject jsonSite = new JSONObject(old.toString());
                    Iterator<String> keys = jsonSite.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        String val = jsonSite.getString(key);
                        phenotypes.put(key, val);

                    }
                } else {
                    //if the check is true and not oldformat, get the
                    // extension of the main feature to get the specific
                    //features of the main feature.
                    allFeatures = possibleFeatures.extendFeature(pf);
                    //add all specific features to the features list,
                    //if the feature exists in the disease.
                    for (String feature : allFeatures) {
                        if (features.getBoolean(feature + "Exists")) {
                            featuresToFind.add(feature);
                        }
                    }
                }
            }
        }

    }

    /**
     * getTitleOfDisease gets the title of a disease from the json structure.
     *
     * @return title is the title of the disease.
     * @author mkslofstra
     * @throws JSONException when json is not properly structured.
     */
    private String getTitleOfDisease() throws JSONException {
        String title = features.getString("preferredTitle");
        return title;
    }

    /**
     * This function saves the information about the disease in a disease
     * object.
     *
     * @param mimNumber is the id of the disease
     * @param title is the name of the disease
     * @author mkslofstra
     */
    private void saveDisease(final String mimNumber,
            final String title) {
        disease = new Disease(mimNumber, title, phenotypes);
    }
}
