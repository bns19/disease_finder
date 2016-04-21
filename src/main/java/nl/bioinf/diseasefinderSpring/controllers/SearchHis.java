package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 21-4-16.
 */
public class SearchHis {

    private String searchedsymptoms;

    private String datetime;

    public SearchHis() {
    }

    /**
     * Get first name.
     *
     * @return FirstName of the User.
     */
    public String getsearchedsymptoms() {
        return searchedsymptoms;
    }

    /**
     * Set first name.
     *
     * @param searchedsymptoms firstname.
     */
    public void setsearchedsymptoms(final String searchedsymptoms) {
        this.searchedsymptoms = searchedsymptoms;
    }

    /**
     * Get first name.
     *
     * @return FirstName of the User.
     */
    public String getdatetime() {
        return datetime;
    }

    /**
     * Set first name.
     *
     * @param datetime firstname.
     */
    public void setdatetime(final String datetime) {
        this.datetime = datetime;
    }
}
