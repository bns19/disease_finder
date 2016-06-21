package nl.bioinf.diseasefinderSpring.beans;

/**
 * Created by bnsikkema on 2-6-16.
 */
public class DiseaseInformation {
    private String title;
    private String omimNumber;
    private double score;
    private double hits;
    private String matches;
    private String information;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOmimNumber() {
        return omimNumber;
    }

    public void setOmimNumber(String omimNumber) {
        this.omimNumber = omimNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getHits() {
        return hits;
    }

    public void setHits(double hits) {
        this.hits = hits;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
