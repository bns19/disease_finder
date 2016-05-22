package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by bnsikkema on 19-5-16.
 */
public class StatisticalInformation {

    private Long totalSearches;
    private Long totalQuerySearches = 0L;
    private Long totalUserSearches = 0L;
    private Long totalQuerySearchesUser = 0L;
    private double percentageQuerySearches;
    private double percentageSearchesUser = 0;
    private double PercentageQuerySearchesUser = 0;
    private double PercentageQuerySearchesUserOverAll = 0;
    private String query;

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

    public double getPercentageSearchesUser() {
        return percentageSearchesUser;
    }

    public void setPercentageSearchesUser(double percentageSearchesUser) {
        this.percentageSearchesUser = percentageSearchesUser;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
