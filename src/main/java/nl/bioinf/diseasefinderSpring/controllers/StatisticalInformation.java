package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by bnsikkema on 19-5-16.
 */
public class StatisticalInformation {

    private Long totalSearches;
    private Long totalQuerySearches;
    private Long totalUserSearches;
    private Long totalQuerySearchesUser;
    private double percentageQuerySearches;
    private double PercentageQuerySearchesUser;
    private double PercentageQuerySearchesUserOverAll;


    public Long getTotalSearches() {
        return totalSearches;
    }

    public void setTotalSearches(Long totalSearches) {
        this.totalSearches = totalSearches;
    }

    public Long getTotalQuerySearches() {
        return totalQuerySearches;
    }

    public void setTotalQuerySearches(Long totalQuerySearches) {
        this.totalQuerySearches = totalQuerySearches;
    }

    public Long getTotalUserSearches() {
        return totalUserSearches;
    }

    public void setTotalUserSearches(Long totalUserSearches) {
        this.totalUserSearches = totalUserSearches;
    }

    public Long getTotalQuerySearchesUser() {
        return totalQuerySearchesUser;
    }

    public void setTotalQuerySearchesUser(Long totalQuerySearchesUser) {
        this.totalQuerySearchesUser = totalQuerySearchesUser;
    }

    public double getPercentageQuerySearches() {
        return percentageQuerySearches;
    }

    public void setPercentageQuerySearches(double percentageQuerySearches) {
        this.percentageQuerySearches = percentageQuerySearches;
    }

    public double getPercentageQuerySearchesUser() {
        return PercentageQuerySearchesUser;
    }

    public void setPercentageQuerySearchesUser(double percentageQuerySearchesUser) {
        PercentageQuerySearchesUser = percentageQuerySearchesUser;
    }

    public double getPercentageQuerySearchesUserOverAll() {
        return PercentageQuerySearchesUserOverAll;
    }

    public void setPercentageQuerySearchesUserOverAll(double percentageQuerySearchesUserOverAll) {
        PercentageQuerySearchesUserOverAll = percentageQuerySearchesUserOverAll;
    }
}
