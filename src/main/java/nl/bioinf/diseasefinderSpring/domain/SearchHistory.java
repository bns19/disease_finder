/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Mariska Slofstra & Arne Roeters
 * Created by hjdupon on 1-5-16.
 */
package nl.bioinf.diseasefinderSpring.domain;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Database information about the search history.
 */
@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @NotNull
    @Type(type="text")
    private String query;

    @NotNull
    @Type(type="text")
    private String longQuery;

    @NotNull
    private String createdAt;
    /**
     * Mandatory JPA constructor.
     */
    public SearchHistory() {
    }

    public SearchHistory(User user, String query, String longQuery, String createdAt) {
        this.user = user;
        this.query = query;
        this.longQuery = longQuery;
        this.createdAt = createdAt;
    }

    /**
     * @return id of user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of user.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user object.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getLongQuery() {
        return longQuery;
    }

    /**
     * Set querys inputted by user.
     * @param longQuery
     */
    public void setLongQuery(String longQuery) {
        this.longQuery = longQuery;
    }

    /**
     * @return querys inputted by user.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Set short query inputted by user.
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return date when created.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Set creation date.
     * @param createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}
