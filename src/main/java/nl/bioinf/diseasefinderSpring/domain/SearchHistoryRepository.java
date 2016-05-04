package nl.bioinf.diseasefinderSpring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "searchHistory", path = "history")
public interface SearchHistoryRepository extends PagingAndSortingRepository<SearchHistory, Long> {

//    User findByUsername(String username);

}
