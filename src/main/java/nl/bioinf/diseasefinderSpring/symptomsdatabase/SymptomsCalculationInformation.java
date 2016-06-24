/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon & Bas Sikkema on 4/5/2016.
 */
package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.beans.StatisticalInformation;
import nl.bioinf.diseasefinderSpring.domain.SearchHistory;
import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.User;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;

/**
 * Is responsible for the caclculations that are given in the statistics tab in the application
 */
public class SymptomsCalculationInformation {

    UserRepository userRepository;
    SearchHistoryRepository searchHistoryRepository;
    User user;

    private StatisticalInformation statisticalInformation = new StatisticalInformation();

    /**
     * Is the main constructor of the class.
     *
     * @param userRepository          interface of the user.
     * @param searchHistoryRepository interface of the search history.
     */
    @Autowired
    public SymptomsCalculationInformation(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    /**
     * Calculates the symptoms that are searched.
     */
    public void calculateSymptomsSearch() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Long totalSearches = searchHistoryRepository.count();
        SearchHistory historyObj = searchHistoryRepository.findById(totalSearches);
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

    /**
     * Calculates the percentages of the symptoms that have been searched by the user.
     * @param lastSearchedQuery
     */
    private void calculatePercentageOfSymptomsSearchedByUser(String lastSearchedQuery) {

        // Count number of searches total
        Long totalUserSearches = 0L;

        totalUserSearches = searchHistoryRepository.countByUser_id(user.getId());
        this.statisticalInformation.setTotalUserSearches(totalUserSearches);
        if (!lastSearchedQuery.equals("")) {

            Long totalSearches = searchHistoryRepository.count();

            Long totalSearchesQueryUser = searchHistoryRepository.countByQueryContainingAndUser_id(lastSearchedQuery, user.getId());
            this.statisticalInformation.setTotalQuerySearchesUser(totalSearchesQueryUser);

            if (totalUserSearches > 0) {
                double searchedSymptomsPercentageUser = (double) 100 / totalUserSearches * totalSearchesQueryUser;
                searchedSymptomsPercentageUser = roundNumbers(searchedSymptomsPercentageUser);
                this.statisticalInformation.setPercentageQuerySearchesUser(searchedSymptomsPercentageUser);

                double percentageSearchesUser = (double) totalUserSearches / totalSearches * 100;
                percentageSearchesUser = roundNumbers(percentageSearchesUser);
                this.statisticalInformation.setPercentageSearchesUser(percentageSearchesUser);
            }

        }
    }

    /**
     * Calculates the searched percentages of the symptoms that have been searched by all the users.
     * @param lastSearchedQuery
     */
    private void calculatePercentageOfSymptomsSearchedByTotal(String lastSearchedQuery) {
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

    /**
     * Rounds the numbers to a whole.
     * @param percentage
     * @return
     */
    private double roundNumbers(final double percentage) {
        DecimalFormat df = new DecimalFormat("#.###");
        String roundedPercentage = df.format(percentage);
        roundedPercentage = roundedPercentage.replace(",", ".");
        return Double.parseDouble(roundedPercentage);

    }

    /**
     * @return
     */
    public StatisticalInformation getStatisticalInformation() {
        return this.statisticalInformation;
    }

}
