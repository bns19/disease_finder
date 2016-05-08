package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * Created by henridupon on 5/4/2016.
 */
public class LoadSearchedSymptoms {

    SearchHistoryRepository searchHistoryRepository;
    UserRepository userRepository;

    public LoadSearchedSymptoms() {
    }

    public List<SearchHistory> loadSearchedSymptoms() {

        List mySQLSearchHistory = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);
        List<SearchHistory> searchHistoryRepos = searchHistoryRepository.findByUser_id(user.getId());

        //TODO: gooit nu een null pointerexception als iemand niet is ingelogd, moet nog gefixed worden

        return searchHistoryRepos;

    }

}