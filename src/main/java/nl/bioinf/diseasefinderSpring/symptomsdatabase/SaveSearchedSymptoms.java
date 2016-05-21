package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * Created by hjdupon on 21-4-16.
 */

public class SaveSearchedSymptoms {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

        @Autowired
        public SaveSearchedSymptoms(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
            this.userRepository = userRepository;
            this.searchHistoryRepository = searchHistoryRepository;
        }

    public void saveSymptoms(String symptoms) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        SearchHistory searchedsymptoms = new SearchHistory();
        searchedsymptoms.setUser(user);
        searchedsymptoms.setCreatedAt(LocalDateTime.now().toString());
        searchedsymptoms.setQuery(symptoms);
        //searchsymptoms = a object
        searchHistoryRepository.save(searchedsymptoms);
    }

}

