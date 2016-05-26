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
 * Created by henridupon on 5/4/2016.
 */
public class LoadSearchedSymptoms {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public LoadSearchedSymptoms(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public List<SearchHistory> loadSearchedSymptoms() {
        List<SearchHistory> usersHistory;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        usersHistory = searchHistoryRepository.findByUser_id(user.getId());
        return usersHistory;

    }


}