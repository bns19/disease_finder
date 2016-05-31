package nl.bioinf.diseasefinderSpring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

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