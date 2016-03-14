package nl.bioinf.diseasefinderSpring.connection;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class gets retrieves from the omim api. It needs to have a omimURL and
 * an omimKey to access the website. Then it retrieves the content of the site
 * in jsonstructure. Then dependant on what is asked, the class gives the
 * information asked (this can be an array with omimnumbers or a jsonstructure
 * with clinical features of a disease).
 *
 * @author mkslofstra
 */
public class OmimDataRetriever {

    /**
     * The url of the omimSite which should be read from.
     */
    private String omimURL;
    /**
     * The key which is needed to use the omim api.
     */
    private String omimKey;

    /**
     * The constructor of the class.
     *
     * @param url the url to reach the website.
     * @param key the key which is needed to use the omim api.
     */
    public OmimDataRetriever(final String url, final String key) {
        omimURL = url;
        omimKey = key;
    }

    /**
     * Gets the result from the website given an omim number.
     * @param omimNumber the id of the disease on omim
     * @return the webpage object so the disease information page on omim.
     * @throws IOException when the url is malformed
     * @throws org.json.JSONException when the json structure is incorrect
     */
    public final JSONObject getOmimResult(final String omimNumber)
            throws IOException, JSONException {
        String siteUrl = this.getResultUrl(omimURL, omimNumber, omimKey);
        String content = this.getSiteContent(siteUrl);
        content = getSuitableContent(makeJSONObject(content), "omim");
        content = getSuitableContent(makeJSONObject(content),
                "clinicalSynopsisList");
        JSONArray siteContent = new JSONArray(content);
        JSONObject webpageObject = makeJSONObject(siteContent.get(0)
                .toString());
        content = getSuitableContent(webpageObject, "clinicalSynopsis");
        webpageObject = makeJSONObject(content);
        return webpageObject;
    }

    /**
     * makeJSONObject makes a JSONObject from which can easily information be
     * obtained.
     *
     * @author mkslofstra
     * @param jsonString a string with json content from the omim website.
     * @return jsonSite the page in a json object.
     * @throws JSONException when the given structure in the string is not a
     * viable json structure.
     */
    private JSONObject makeJSONObject(final String jsonString)
            throws JSONException {
        JSONObject jsonSite = new JSONObject(jsonString);
        return jsonSite;
    }

    /**
     * This function gets the needed content from all content of the webpage.
     *
     * @param content is the greater content of the website.
     * @param search what to get out of the content.
     * @throws JSONException when the content is not valid jsonstructure.
     * @return content the new content.
     */
    private String getSuitableContent(final JSONObject content,
            final String search) throws JSONException {
        String found = content.get(search).toString();
        return found;
    }

    /**
     * This function gets the OmimNumbers of diseases given features of a
     * disease.
     *
     * @param features are the features of a hypothetical disease.
     * @throws IOException when the URL is malformed.
     * @throws JSONException when the sitecontent is not valid JSON.
     * @return diseases the omimnumbers of the found diseases.
     */
    public final HashMap getOmimNumbers(final String[] features)
            throws IOException, JSONException {
        String urlToSearch = this.buildFeatureURL(features, omimURL, omimKey);
        String siteContentString = this.getSiteContent(urlToSearch);
        String content = getSuitableContent(makeJSONObject(
                siteContentString), "omim");
        content = getSuitableContent(makeJSONObject(content), "searchResponse");
        content = getSuitableContent(makeJSONObject(content), "entryList");
        HashMap<String, String> diseases = getDiseases(content);
        return diseases;
    }

    /**
     * getSiteContent This function gets the page of the given omim url and
     * saves it in a String.
     *
     * @author mkslofstra
     * @param omimSite the URL of the site which should be opened.
     * @return resultBuilder.toString(), the string of the content of the
     * webpage.
     * @throws IOException when the url cannot be made.
     */
    private String getSiteContent(final String omimSite)
            throws IOException {
        URL omim = new URL(omimSite);
        BufferedReader omimContent = new BufferedReader(
                new InputStreamReader(omim.openStream()));
        String inputLine;
        StringBuilder resultBuilder = new StringBuilder();
        while ((inputLine = omimContent.readLine()) != null) {
            resultBuilder.append(inputLine);
        }
        omimContent.close();
        return resultBuilder.toString();
    }

    /**
     * buildFeatureURL creates a string which can be converted to a url for the
     * omim api from a list of strings.
     *
     * @param features are the features which should be searched.
     * @param omimSite the url of the site where the features and key should be
     * added to.
     * @param apiKey the key of the omim api.
     * @return siteUrl is a string which can be converted to a url.
     * @author mkslofstra
     */
    private String buildFeatureURL(final String[] features,
            final String omimSite, final String apiKey) {
        StringBuilder buildFeatures = new StringBuilder();
        for (String feature : features) {
            //check if there is a whitespace in the feature
            if (feature.contains(" ")) {
                String featureToAppend = feature.replace(" ", "+");
                buildFeatures.append("\"").append(featureToAppend)
                        .append("\"+");
            } else {
                buildFeatures.append(feature).append("+");
            }
        }
        String allFeatures = buildFeatures.toString();
        //remove the last +
        String featuresToReturn = allFeatures.substring(0,
                (allFeatures.length() - 1));
        String siteUrl = String.format(omimSite, featuresToReturn, apiKey);
        return siteUrl;
    }

    /**
     * getDiseases searches with regex in the entrylist of omim to get the
     * omimnumbers.
     *
     * @param entries the found diseases in the format of the jsonstructure on
     * the omim website.
     * @return diseases is an arraylist of strings which are the omimnumbers.
     */
    private HashMap<String, String> getDiseases(final String entries) {
        HashMap<String, String> diseaseMatches = new HashMap();
        Pattern numbers = Pattern.compile("\\d{6}");
        Matcher match = numbers.matcher(entries);
        Pattern symptomMatches = Pattern.compile("(\"matches\":\")([^}]+\")");
        Matcher symptomMatcher = symptomMatches.matcher(entries);
        while (match.find() && symptomMatcher.find()) {
            String matches = symptomMatcher.group(2).replace("\\", "");
            matches = matches.replace("\"", "");
            diseaseMatches.put(match.group(), matches);
        }
        return diseaseMatches;
    }

    /**
     * This function makes the url for the getOmimResult function.
     *
     * @param url the url to search for with stringformatting in in.
     * @param omimNumber the id of the disease to get the page from.
     * @param key the key that is needed to use the api.
     * @throws IOException when the url is malformed.
     * @throws JSONException when the json structure is malformed.
     * @return omimSite the url of the omimSite which should be opened.
     */
    private String getResultUrl(final String url, final String omimNumber,
            final String key) throws IOException, JSONException {
        String omimUrl = String.format(url, omimNumber, key);
        return omimUrl;
    }
}
