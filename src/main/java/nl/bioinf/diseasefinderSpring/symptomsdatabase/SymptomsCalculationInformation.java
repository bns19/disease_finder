package nl.bioinf.diseasefinderSpring.symptomsdatabase;

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

    @Autowired
    public SymptomsCalculationInformation(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public void calculateSymptomsSearch(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        user = userRepository.findByUsername(username);

        calculatePercentageOfSymptomsSearchedByUser();
        calculatePercentageOfSymptomsSearchedByTotal();

    }

    public void calculatePercentageOfSymptomsSearchedByUser(){
        // Count number of searches total
        Long countUser = searchHistoryRepository.countByUser_id(user.getId());
        // Count query that contains ? word
        Long countQueryUser = searchHistoryRepository.countByQueryContainingAndUser_id("sss", user.getId());

        Long searchedSymptomsPercentageUser = 100/countUser*countQueryUser;

        System.out.println("percentage User: "+ searchedSymptomsPercentageUser);
    }

    public void calculatePercentageOfSymptomsSearchedByTotal(){
        // Count number of searches total
        Long countUsers = searchHistoryRepository.count();
        // Count query that contains ? word
        Long countQueryUsers = searchHistoryRepository.countByQueryContaining("sss");

        Long searchedSymptomsPercentageTotal = 100/countUsers*countQueryUsers;

        System.out.println("percentage Total: "+ searchedSymptomsPercentageTotal);
    }


}
