/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema & Henri du Pon
 */
package nl.bioinf.diseasefinderSpring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Interface that handles tue User information.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * gets the user by username.
     * @param username username
     * @return User object
     */
    User findByUsername(String username);

}
