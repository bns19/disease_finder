/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Adjusted by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.disease;

import nl.bioinf.diseasefinderSpring.beans.DiseaseInformation;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Disease collects all the information about one disease which is found.
 * The toString method and summary method of this class print html, so the servlets
 * can use this output.
 *
 * @author mkslofstra and bnsikkema
 */
public class Disease implements Comparable {

    /**
     * constructor of the class disease.
     *
     * @param mimNumberValue  is the id of the disease.
     * @param titleOfDisease  is the most common title of the disease.
     * @param featuresHashMap are the properties of the disease.
     */
    public Disease(final String mimNumberValue,
                   final String titleOfDisease, final TreeMap featuresHashMap) {
        //the mimNumber length should always be 6, otherwise, it is invalid.
        if (mimNumberValue == null || mimNumberValue.length() != 6) {
            throw new IllegalArgumentException("The omimNumber should be a"
                    + "String of exactly six characters.");
        }

        if (featuresHashMap == null || featuresHashMap.isEmpty()) {
            throw new IllegalArgumentException("The map cannot be empty");
        }

        if (titleOfDisease.isEmpty() || titleOfDisease.length() < 1) {
            throw new IllegalArgumentException("The title of the disease cannot be null");
        }
        this.mimNumber = mimNumberValue;
        this.title = titleOfDisease;
        this.features = featuresHashMap;
        this.hits++;
        this.diseaseInformation.setOmimNumber(mimNumberValue);
        this.diseaseInformation.setTitle(titleOfDisease);



    }

    /**
     *
     */
    private DiseaseInformation diseaseInformation = new DiseaseInformation();

    /**
     * matches is the list of matches found with the symptoms.
     */
    private List<String> matches;
    /**
     * mimNumber is the OMIM number of the disease.
     */
    private String mimNumber;
    /**
     * title is the name of the disease.
     */
    private String title;
    /**
     * features are the characteristics of the disease.
     */
    private TreeMap features;
    /**
     * counts the number of hits which this disease has.
     */
    private Integer hits = 0;
    /**
     * the score which is given for this disease given the phenotype.
     */
    private Double score;

    /**
     * The getter of hits.
     *
     * @return hits the number of hits a disease has.
     */

    public final DiseaseInformation getDiseaseInformation() {
        return this.diseaseInformation; }

    /**
     * getHits.
     * @return hits the hits of the disease
     */
    public final Integer getHits() {
        return hits;
    }

    /**
     * The setter of hits.
     *
     * @param count the current number of hits.
     */
    public final void setHits(final Integer count) {
        this.hits = count;
    }

    /**
     * The getter of the score.
     *
     * @return score which is determined given the phenotype.
     */
    public final Double getScore() {
        return score;
    }

    /**
     * This function collects most of the information about the disease.
     */
    public void createDiseaseInformation() {

        StringBuilder sb = new StringBuilder();
        Iterator it = features.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String info = pair.getValue().toString();
            //this replaces the ugly links and unreadable information
            //on the omim api
            info = info.replaceAll("\\{[A-Za-z0-9:_,. -]+\\}", "");
            //name the key of the hashmap, this is the ontology of the
            //symptom in camelcase
            String wholeKey = (String) pair.getKey();
            //make a stringbuilder
            StringBuilder key = new StringBuilder();
            //nicely format the symptom ontology
            for (int i = 0; i < wholeKey.length(); i++) {
                char letter = wholeKey.charAt(i);
                //check for each character if the character is uppercase
                //(then it is a new word)
                if (Character.isUpperCase(letter)) {
                    //append space if character is uppercase and make
                    //the character lowercase
                    key.append(" ").append(Character.toString(letter)
                            .toLowerCase());
                } else {
                    //just append the character
                    key.append(letter);
                }
            }
            sb.append("<br/><br/><b>").append(key.toString())
                    .append("</b><br/>").append(info);
            it.remove(); // avoids a ConcurrentModificationException
        }
        String diseaseInfo = sb.toString();
        if (this.matches != null && !this.matches.isEmpty()) {
            for (String match : this.matches) {
                match = match.replaceAll(" ", "");
                diseaseInfo = diseaseInfo.replaceAll("(?i)" + match.toLowerCase(),
                        "<span class=\"highlight\">" + match + "</span>");
            }
            this.diseaseInformation.setMatches(convertMatchesToString());
        }
        this.diseaseInformation.setInformation(diseaseInfo);


    }

    /**
     * This function turns the list of matches into a string of matches.
     * @return string of matches
     */
    private String convertMatchesToString() {
        StringBuilder sb1 = new StringBuilder();
        for (String match : this.matches) {
            sb1.append(match);
            if (this.matches.size() > 1 && this.matches.indexOf(match) != this.matches.size()) {
                sb1.append(",");
            }
        }
        return sb1.toString();
    }

    /**
     * The setter of score.
     *
     * @param givenScore the score which is given.
     */
    public final void setScore(final Double givenScore) {
        this.score = givenScore;
    }

    /**
     * The getter of the OMIM number.
     *
     * @return mimNumber the OMIM number of the disease.
     */
    public final String getMimNumber() {
        return mimNumber;
    }

    /**
     * The getter of the title of the disease.
     *
     * @return title of the disease.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * The getter of getFeatures.
     *
     * @return the features of the disease.
     */
    public final TreeMap getFeatures() {
        return features;
    }

    /**
     * The getter of the list matches.
     *
     * @return the list of matches.
     */
    public final List getMatches() {
        try {
            return this.matches;
        } catch (Exception e) {
            throw new NullPointerException("matches is not filled");
        }
    }

    /**
     * This method adds a given string to the StringList matches.
     *
     * @param match the list of matches.
     */
    public final void setMatches(final List match) {
        this.matches = match;
    }

    /**
     * The compare function to compare two disease objects.
     *
     * @param otherDisease the disease object which should be compared with this
     *                     one.
     * @return if the comparison is the same more or less.
     */
    @Override
    public final int compareTo(final Object otherDisease) {
        Disease disease = (Disease) otherDisease;
        int comparison = Double.compare(disease.getScore(), this.score);
        return comparison;
    }
}
