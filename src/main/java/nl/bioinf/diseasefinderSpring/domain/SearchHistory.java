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

    /**
     * ID annotation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * oneToOne annotation.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    /**
     * not null annotation.
     */
    @NotNull
    /**
     * type annotation.
     */
    @Type(type = "text")
    private String query;

    /**
     * not null annotation.
     */
    @NotNull
    @Type(type = "text")
    private String longQuery;

    /**
     * not null annotation.
     */
    @NotNull
    private String createdAt;
    /**
     * Mandatory JPA constructor.
     */
    public SearchHistory() {
    }

    /**
     * public constructor.
     * @param user user
     * @param query query (leafs)
     * @param longQuery longquery (leafs with parents)
     * @param createdAt date and time of creation
     */
    public SearchHistory(final User user, final String query, final String longQuery, final String createdAt) {
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
     * @param id the id
     */
    public void setId(final Long id) {
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
     * @param user the user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     *  getlongQuery.
     * @return longQuery
     */
    public String getLongQuery() {
        return longQuery;
    }

    /**
     * Set querys inputted by user.
     * @param longQuery the longQuery
     */
    public void setLongQuery(final String longQuery) {
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
     * @param query the query
     */
    public void setQuery(final String query) {
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
     * @param createdAt data and time of creation
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }


}
