/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 24-4-16.
 */
package nl.bioinf.diseasefinderSpring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * An interface that handles the search history.
 */
@RepositoryRestResource(collectionResourceRel = "searchHistory", path = "history")
public interface SearchHistoryRepository extends PagingAndSortingRepository<SearchHistory, Long> {

    List<SearchHistory> findByUser_id(Long userId);

    Iterable<SearchHistory> findAll();
    SearchHistory findById(Long id);
    long count();
    long countByUser_id(Long userId);
    long countByQueryContaining(String query);
    long countByQueryContainingAndUser_id(String query, Long userId);
}