package nl.bioinf.diseasefinderSpring.beans;

/**
 * Created by Bas Sikkema on 19-5-16.
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
    /**
     * percentage of searches of query against total searches user.
     */
    private double percentageQuerySearchesUser = 0;
    /**
     * percentage of query searches by the user against the total searche sof the user.
     */
    private double percentageQuerySearchesUserOverAll = 0;
    /**
     * The last founded query.
     */
    private String query;

    /**
     * getter of total searches.
     * @return totalSearches
     */
    public Long getTotalSearches() {
        return totalSearches;
    }

    /**
     * setter of total searches.
     * @param totalSearches total user searches
     */
    public void setTotalSearches(final Long totalSearches) {
        this.totalSearches = totalSearches;
    }

    /**
     * getter of total query searches.
     * @return totalQuerySearches total query searches
     */
    public Long getTotalQuerySearches() {
        return totalQuerySearches;
    }

    /**
     * setter of total query searches.
     * @param totalQuerySearches total query searches
     */
    public void setTotalQuerySearches(final Long totalQuerySearches) {
        this.totalQuerySearches = totalQuerySearches;
    }

    /**
     * getter of total user searches.
     * @return totalUserSearches total user searches
     */
    public Long getTotalUserSearches() {
        return totalUserSearches;
    }

    /**
     * setter of total user searches.
     * @param totalUserSearches total user searches
     */
    public void setTotalUserSearches(final Long totalUserSearches) {
        this.totalUserSearches = totalUserSearches;
    }

    /**
     * getter of total query searches user.
     * @return totalQuerySearchesUser total query searches user
     */
    public Long getTotalQuerySearchesUser() {
        return totalQuerySearchesUser;
    }

    /**
     * setter of total query searches user.
     * @param totalQuerySearchesUser total query searches user
     */
    public void setTotalQuerySearchesUser(final Long totalQuerySearchesUser) {
        this.totalQuerySearchesUser = totalQuerySearchesUser;
    }

    /**
     * getter of percentage query searches.
     * @return percentageQuerySearches
     */
    public double getPercentageQuerySearches() {
        return percentageQuerySearches;
    }

    /**
     * setter of percentage query searches.
     * @param percentageQuerySearches percentage query searches
     */
    public void setPercentageQuerySearches(final double percentageQuerySearches) {
        this.percentageQuerySearches = percentageQuerySearches;
    }

    /**
     * getter of percentage searches user.
     * @return percentageSearchesUser
     */
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
