package nl.bioinf.diseasefinderSpring.beans;

/**
 * Created by bnsikkema on 19-5-16.
 * This bean class contains all the statistical information calculated.
 */
public class StatisticalInformation {
    /**
     * the number of total searches.
     */
    private Long totalSearches;
    /**
     * the number of total query searches.
     */
    private Long totalQuerySearches = 0L;
    /**
     * the number of total searches by the user.
     */
    private Long totalUserSearches = 0L;
    /**
     * the number of total searches of the latest query by user.
     */
    private Long totalQuerySearchesUser = 0L;
    /**
     * the percentage of latest query searches.
     */
    private double percentageQuerySearches;
    /**
     * the percentage of searches by the user.
     */
    private double percentageSearchesUser = 0;

    private double percentageQuerySearchesUser = 0;
    /**
     * percentage of query searches by the user against the
     */
    private double percentageQuerySearchesUserOverAll = 0;
    private String query;

    public Long getTotalSearches() {
        return totalSearches;
    }

    public void setTotalSearches(final Long totalSearches) {
        this.totalSearches = totalSearches;
    }

    public Long getTotalQuerySearches() {
        return totalQuerySearches;
    }

    public void setTotalQuerySearches(final Long totalQuerySearches) {
        this.totalQuerySearches = totalQuerySearches;
    }

    public Long getTotalUserSearches() {
        return totalUserSearches;
    }

    public void setTotalUserSearches(final Long totalUserSearches) {
        this.totalUserSearches = totalUserSearches;
    }

    public Long getTotalQuerySearchesUser() {
        return totalQuerySearchesUser;
    }

    public void setTotalQuerySearchesUser(final Long totalQuerySearchesUser) {
        this.totalQuerySearchesUser = totalQuerySearchesUser;
    }

    public double getPercentageQuerySearches() {
        return percentageQuerySearches;
    }

    public void setPercentageQuerySearches(final double percentageQuerySearches) {
        this.percentageQuerySearches = percentageQuerySearches;
    }

    public double getPercentageSearchesUser() {
        return percentageSearchesUser;
    }

    public void setPercentageSearchesUser(final double percentageSearchesUser) {
        this.percentageSearchesUser = percentageSearchesUser;
    }

    public double getPercentageQuerySearchesUser() {
        return percentageQuerySearchesUser;
    }

    public void setPercentageQuerySearchesUser(final double percentageQuerySearchesUser) {
        this.percentageQuerySearchesUser = percentageQuerySearchesUser;
    }

    public double getPercentageQuerySearchesUserOverAll() {
        return percentageQuerySearchesUserOverAll;
    }

    public void setPercentageQuerySearchesUserOverAll(final double percentageQuerySearchesUserOverAll) {
        this.percentageQuerySearchesUserOverAll = percentageQuerySearchesUserOverAll;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }
}
