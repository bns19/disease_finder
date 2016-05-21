package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.controllers.StatisticalInformation;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by henridupon on 5/5/2016.
 */
public class SymptomsCalculationInformation {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;
    User user;

    private StatisticalInformation statisticalInformation = new StatisticalInformation();

    @Autowired
    public SymptomsCalculationInformation(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public void calculateSymptomsSearch(String shortSymptoms){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        user = userRepository.findByUsername(username);
        if (auth.getName() != "anonymousUser") {
            calculatePercentageOfSymptomsSearchedByUser(shortSymptoms);
        }

        //calculatePercentageOfSymptomsSearchedByTotal(shortSymptoms);
    }

    private void calculatePercentageOfSymptomsSearchedByUser(String shortSymptoms){
        // Count number of searches total\\
        Long totalUserSearches = 0L;
       // Long countUserSearches = searchHistoryRepository.count();

        totalUserSearches = searchHistoryRepository.countByUser_id(user.getId());
        this.statisticalInformation.setTotalUserSearches(totalUserSearches);

        Long totalSearchesQueryUser = searchHistoryRepository.countByQueryContainingAndUser_id(shortSymptoms, user.getId());
        this.statisticalInformation.setTotalQuerySearchesUser(totalSearchesQueryUser);

        double searchedSymptomsPercentageUser = (double)100 / totalUserSearches * totalSearchesQueryUser;
        this.statisticalInformation.setPercentageQuerySearchesUser(searchedSymptomsPercentageUser);

    }

    private void calculatePercentageOfSymptomsSearchedByTotal(String shortSymptoms){
        // Count number of searches total
        Long totalSearches = searchHistoryRepository.count();
        this.statisticalInformation.setTotalSearches(totalSearches);

        Long countQueryUsers = searchHistoryRepository.countByQueryContaining(shortSymptoms);
        this.statisticalInformation.setTotalQuerySearches(countQueryUsers);

        double searchedSymptomsPercentageTotal = (double) 100/totalSearches*countQueryUsers;
        this.statisticalInformation.setPercentageQuerySearches(searchedSymptomsPercentageTotal);
    }

    public StatisticalInformation getStatisticalInformation() {
        return this.statisticalInformation;
    }

}
