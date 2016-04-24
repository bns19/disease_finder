package nl.bioinf.diseasefinderSpring.Database;

/**
 * Created by henridupon on 4/20/2016.
 */
public class SearchData {

    private String searchhistory;

    private String username;


    /**
     * Get username.
     *
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     *
     * @param username username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get username.
     *
     * @return searchhistory.
     */
    public String getsearchhistory() {
        return searchhistory;
    }

    /**
     * Set username.
     *
     * @param searchhistory username.
     */
    public void setsearchhistory(final String searchhistory) {
        this.searchhistory = searchhistory;
    }

}
