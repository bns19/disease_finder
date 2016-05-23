package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.controllers.StatisticalInformation;
import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.text.DecimalFormat;

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

    public void calculateSymptomsSearch(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Long totalSearches =  searchHistoryRepository.count();
        SearchHistory historyObj = searchHistoryRepository.findById(totalSearches);
        System.out.println(historyObj.getQuery()+ " query");
        String lastSearchedQuery;
        if (historyObj == null) {
            lastSearchedQuery = "";
        } else {
            lastSearchedQuery = historyObj.getQuery();
        }
        user = userRepository.findByUsername(username);
        if (auth.getName() != "anonymousUser") {
            calculatePercentageOfSymptomsSearchedByUser(lastSearchedQuery);
        }


        calculatePercentageOfSymptomsSearchedByTotal(lastSearchedQuery);
    }

    private void calculatePercentageOfSymptomsSearchedByUser(String lastSearchedQuery){
        // Count number of searches total\\
        Long totalUserSearches = 0L;
       // Long countUserSearches = searchHistoryRepository.count();

        totalUserSearches = searchHistoryRepository.countByUser_id(user.getId());
        this.statisticalInformation.setTotalUserSearches(totalUserSearches);
        if (!lastSearchedQuery.equals("")) {

            Long totalSearches = searchHistoryRepository.count();

            Long totalSearchesQueryUser = searchHistoryRepository.countByQueryContainingAndUser_id(lastSearchedQuery, user.getId());
            this.statisticalInformation.setTotalQuerySearchesUser(totalSearchesQueryUser);



            if (totalUserSearches > 0) {
                double searchedSymptomsPercentageUser = (double) 100 / totalUserSearches * totalSearchesQueryUser;
                System.out.println(searchedSymptomsPercentageUser + " user");

                searchedSymptomsPercentageUser = roundNumbers(searchedSymptomsPercentageUser);
                this.statisticalInformation.setPercentageQuerySearchesUser(searchedSymptomsPercentageUser);

                double percentageSearchesUser = (double) totalUserSearches / totalSearches * 100;
                percentageSearchesUser = roundNumbers(percentageSearchesUser);
                this.statisticalInformation.setPercentageSearchesUser(percentageSearchesUser);
            }

        }
    }

    private void calculatePercentageOfSymptomsSearchedByTotal(String lastSearchedQuery){
        Long totalSearches = searchHistoryRepository.count();
        this.statisticalInformation.setTotalSearches(totalSearches);
        if (!lastSearchedQuery.equals("")) {
            this.statisticalInformation.setQuery(lastSearchedQuery);

            Long countQueryUsers = searchHistoryRepository.countByQueryContaining(lastSearchedQuery);
            this.statisticalInformation.setTotalQuerySearches(countQueryUsers);

            double searchedSymptomsPercentageTotal = (double) countQueryUsers / totalSearches * 100;
            searchedSymptomsPercentageTotal = roundNumbers(searchedSymptomsPercentageTotal);
            this.statisticalInformation.setPercentageQuerySearches(searchedSymptomsPercentageTotal);
        }
    }

    private double roundNumbers(final double percentage) {
        System.out.println(percentage);
        DecimalFormat df = new DecimalFormat("#.###");
       String roundedPercentage = df.format(percentage);
        roundedPercentage = roundedPercentage.replace(",",".");
        return Double.parseDouble(roundedPercentage);

    }


    public StatisticalInformation getStatisticalInformation() {
        return this.statisticalInformation;
    }

}
