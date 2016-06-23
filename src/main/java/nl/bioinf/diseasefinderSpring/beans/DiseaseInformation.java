package nl.bioinf.diseasefinderSpring.beans;

/**
 * Created by bnsikkema on 2-6-16.
 * This bean class contains all information regaring the disease
 */
public class DiseaseInformation {
    /**
     * title title.
     */
    private String title;
    /**
     * omimNumber omimnumber.
     */
    private String omimNumber;
    /**
     * score score.
     */
    private double score;
    /**
     * hits hits.
     */
    private double hits;
    /**
     * matches matches.
     */
    private String matches;
    /**
     * information, a string of information regarding the disease.
     */
    private String information;

    /**
     * getter of the title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter of the title.
     * @param title title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * getOmimNumber omimnumber.
     * @return omimNumber
     */
    public String getOmimNumber() {
        return omimNumber;
    }

    /**
     * the setter of the omimNumber.
     * @param omimNumber omimnumber
     */
    public void setOmimNumber(final String omimNumber) {
        this.omimNumber = omimNumber;
    }

    /**
     * the getter of the score.
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * the setter of the score.
     * @param score score
     */
    public void setScore(final double score) {
        this.score = score;
    }

    /**
     * the getter of the hits.
     * @return hits hits
     */
    public double getHits() {
        return hits;
    }

    /**
     * the setter of the hits.
     * @param hits hits
     */
    public void setHits(final double hits) {
        this.hits = hits;
    }

    /**
     * the getter of the matches.
     * @return matches
     */
    public String getMatches() {
        return matches;
    }

    /**
     * the setter of the matches.
     * @param matches matches
     */
    public void setMatches(final String matches) {
        this.matches = matches;
    }

    /**
     * the getter of the information.
     * @return information of the disease
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * te setter of the information.
     * @param information information of the disease
     */
    public void setInformation(final String information) {
        this.information = information;
    }


}
