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

    /**
     * gets complete searchhistory by user.
     * @param userId user id
     * @return A list with the search history
     */
    List<SearchHistory> findByUser_id(Long userId);

    /**
     *  gets complete global search history.
     * @return iterable search history
     */
    Iterable<SearchHistory> findAll();

    /**
     *  gets search history by id.
     * @param id  id
     * @return searchhistory object
     */
    SearchHistory findById(Long id);

    /**
     * counts the total history.
     * @return number of total searches
     */
    long count();

    /**
     * gets number of search query's by user id.
     * @param userId user id
     * @return number of searches by user id
     */
    long countByUser_id(Long userId);

    /**
     * gets number of times the specific query whas searched for.
     * @param query specific query
     * @return the number
     */
    long countByQueryContaining(String query);

    /**
     * gets how many times a specific query was found by a specific user.
     * @param query the specific query
     * @param userId the specific user
     * @return the number
     */
    long countByQueryContainingAndUser_id(String query, Long userId);
}