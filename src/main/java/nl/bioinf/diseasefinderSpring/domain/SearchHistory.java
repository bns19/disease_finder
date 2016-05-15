package nl.bioinf.diseasefinderSpring.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*Note of Bas: I have changed date and user to Strings */

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

//    @NotNull
//    private LocalDateTime createdAt;

    @NotNull
    private String createdAt;
    /**
     * Mandatory JPA constructor.
     */
    public SearchHistory() {
    }

    public SearchHistory(User user, String query, String createdAt) {
        this.user = user;
        this.query = query;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}
