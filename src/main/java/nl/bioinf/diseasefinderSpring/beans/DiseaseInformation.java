package nl.bioinf.diseasefinderSpring.beans;

/**
 * Created by bnsikkema on 2-6-16.
 * This beanclass contains all information regaring the disease
 */
public class DiseaseInformation {
    /**
     * title.
     */
    private String title;
    /**
     * omimNumber.
     */
    private String omimNumber;
    /**
     * score.
     */
    private double score;
    /**
     * hits.
     */
    private double hits;
    /**
     * matches.
     */
    private String matches;
    /**
     * information, a string of information regarding the disease.
     */
    private String information;

    /**
     * getTitle.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setTitle.
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * getOmimNumber.
     * @return omimNumber
     */
    public String getOmimNumber() {
        return omimNumber;
    }

    /**
     * setOmimNumber.
     * @param omimNumber
     */
    public void setOmimNumber(final String omimNumber) {
        this.omimNumber = omimNumber;
    }

    /**
     * getScore.
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * setScore.
     * @param score
     */
    public void setScore(final double score) {
        this.score = score;
    }

    /**
     * getHits.
     * @return hits
     */
    public double getHits() {
        return hits;
    }

    /**
     * sethits.
     * @param hits
     */
    public void setHits(final double hits) {
        this.hits = hits;
    }

    /**
     * getMatches.
     * @return matches
     */
    public String getMatches() {
        return matches;
    }

    /**
     * setmatches.
     * @param matches
     */
    public void setMatches(final String matches) {
        this.matches = matches;
    }

    /**
     * getInformation.
     * @return information of the disease
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * setInformation.
     * @param information
     */
    public void setInformation(final String information) {
        this.information = information;
    }


}
