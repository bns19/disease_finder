package nl.bioinf.diseasefinderSpring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

//    User findByUsername(String username);
//
//    Optional<User> getUserById(long id);
//
//    Optional<User> getUserByEmail(String email);
//
//    Collection<User> getAllUsers();
//
//    User create(User form);

}
