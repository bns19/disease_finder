package nl.bioinf.diseasefinderSpring.symptomsdatabase;

/**
 * Created by hjdupon on 21-4-16.
 */
public class SearchHis {

    private String username;

    private String searchedSymptoms;

    private String datetime;

    public SearchHis() {
    }

    /**
     * Get first name.
     *
     * @return FirstName of the User.
     */
    public String getSearchedSymptoms() {
        return searchedSymptoms;
    }

    /**
     * Sets the search history
     * @param searchedSymptoms Search string
     */
    public void setSearchedSymptoms(final String searchedSymptoms) {
        this.searchedSymptoms = searchedSymptoms;
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

    /**
     * Get first name.
     *
     * @return FirstName of the User.
     */
    public String getusername() {
        return username;
    }

    /**
     * Set first name.
     *
     * @param username firstname.
     */
    public void setusername(final String username) {
        this.username = username;
    }

}
