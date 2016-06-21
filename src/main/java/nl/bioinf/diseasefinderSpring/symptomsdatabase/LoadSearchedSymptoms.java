/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 5/4/2016.
 */
package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * LoadSearchedSymptoms is responsible for loading the symptoms / history of the user.
 */
public class LoadSearchedSymptoms {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public LoadSearchedSymptoms(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    /**
     * Gives back the searched symptoms of the user.
     * @return A list with user history
     */
    public List<SearchHistory> loadSearchedSymptoms() {
        List<SearchHistory> usersHistory;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        usersHistory = searchHistoryRepository.findByUser_id(user.getId());
        return usersHistory;

    }




}