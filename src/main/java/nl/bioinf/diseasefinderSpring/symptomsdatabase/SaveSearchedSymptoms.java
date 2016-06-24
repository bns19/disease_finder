/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema & Henri du Pon on 21-4-16.
 */
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
 * This function ss responsible for saving the searched symptoms in the MySQL database.
 */
public class SaveSearchedSymptoms {
    /**
     * userRepository interface.
     */
    UserRepository userRepository;
    /**
     * searchHistoryRepository interface.
     */
    SearchHistoryRepository searchHistoryRepository;

    /**
     * Public constructor.
     * @param userRepository userRepository interface
     * @param searchHistoryRepository searchHistoryRepository interface.
     */
    @Autowired
        public SaveSearchedSymptoms(final UserRepository userRepository,
                                    final SearchHistoryRepository searchHistoryRepository) {
            this.userRepository = userRepository;
            this.searchHistoryRepository = searchHistoryRepository;
        }

    /**
     * Saves the short and long symptoms in the MySQL database.
     * @param shortSymptoms is a list of one symptom that is selected
     * @param symptoms is a list of several symptoms from selected to the root.
     */
    public void saveSymptoms(final String shortSymptoms, final String symptoms) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        SearchHistory searchedsymptoms = new SearchHistory();
        searchedsymptoms.setUser(user);
        searchedsymptoms.setCreatedAt(LocalDateTime.now().toString());
        searchedsymptoms.setQuery(shortSymptoms);
        searchedsymptoms.setLongQuery(symptoms);
        searchHistoryRepository.save(searchedsymptoms);
    }

}

