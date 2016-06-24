/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Bas Sikkema & Henri du Pon on 4/5/2016.
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
 * Class responsible for the calculations that are given in the statistics tab in the application.
 */
public class SymptomsCalculationInformation {

    /**
     * userRepository interface.
     */
    UserRepository userRepository;
    /**
     * searchHistoryRepository interface.
     */
    SearchHistoryRepository searchHistoryRepository;
    /**
     * user interface.
     */
    User user;

    /**
     * a statistical information object.
     */
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
        /*extracts username and total searches from the databases*/
        String username = auth.getName();
        Long totalSearches = searchHistoryRepository.count();
        /*historyObj, does not exist when the application has not bene used yet with specified databases*/
        SearchHistory historyObj = searchHistoryRepository.findById(totalSearches);
        String lastSearchedQuery;
        if (historyObj == null) {
            lastSearchedQuery = "";
        } else {
            lastSearchedQuery = historyObj.getQuery();
        }
        user = userRepository.findByUsername(username);
        if (auth.getName() != "anonymousUser") {
            /*these statistics are only calcultated when the user is logged in*/
            calculatePercentageOfSymptomsSearchedByUser(lastSearchedQuery);
        }
        calculatePercentageOfSymptomsSearchedByTotal(lastSearchedQuery);
    }

    /**
     * Calculates the percentages of the symptoms that have been searched by the user.
     *
     * @param lastSearchedQuery the last searched query
     */
    private void calculatePercentageOfSymptomsSearchedByUser(String lastSearchedQuery) {
        // Count number of searches total
        Long totalUserSearches = 0L;
        totalUserSearches = searchHistoryRepository.countByUser_id(user.getId());
        this.statisticalInformation.setTotalUserSearches(totalUserSearches);
        /*if the last query is empty, the program has no previous searches. The below parts will than not be
        calculated*/
        if (!lastSearchedQuery.equals("")) {
            /*calculate total searches*/
            Long totalSearches = searchHistoryRepository.count();
            /*calculate total searches of the query by the user.*/
            Long totalSearchesQueryUser = searchHistoryRepository.countByQueryContainingAndUser_id(lastSearchedQuery, user.getId());
            this.statisticalInformation.setTotalQuerySearchesUser(totalSearchesQueryUser);
            /*if the user has not searched anything before, the next calculations are not done*/
            if (totalUserSearches > 0) {
                /*calculates the percentage of searches by the user against total */
                double searchedSymptomsPercentageUser = (double) 100 / totalUserSearches * totalSearchesQueryUser;
                searchedSymptomsPercentageUser = roundNumbers(searchedSymptomsPercentageUser);
                this.statisticalInformation.setPercentageQuerySearchesUser(searchedSymptomsPercentageUser);
                /*calculate percentage of searches by user*/
                double percentageSearchesUser = (double) totalUserSearches / totalSearches * 100;
                percentageSearchesUser = roundNumbers(percentageSearchesUser);
                this.statisticalInformation.setPercentageSearchesUser(percentageSearchesUser);
            }

        }
    }

    /**
     * Calculates the searched percentages of the symptoms that have been searched by all the users.
     *
     * @param lastSearchedQuery the query last searched for
     */
    private void calculatePercentageOfSymptomsSearchedByTotal(String lastSearchedQuery) {
        Long totalSearches = searchHistoryRepository.count();
        this.statisticalInformation.setTotalSearches(totalSearches);
        /*if no query has been searched before, next calculations will not be carried out*/
        if (!lastSearchedQuery.equals("")) {
            this.statisticalInformation.setQuery(lastSearchedQuery);
            /*calculates total query searches by user.*/
            Long countQueryUsers = searchHistoryRepository.countByQueryContaining(lastSearchedQuery);
            this.statisticalInformation.setTotalQuerySearches(countQueryUsers);
            /*calculates percentage of searches of this query by user against total searches*/
            double searchedSymptomsPercentageTotal = (double) countQueryUsers / totalSearches * 100;
            searchedSymptomsPercentageTotal = roundNumbers(searchedSymptomsPercentageTotal);
            this.statisticalInformation.setPercentageQuerySearches(searchedSymptomsPercentageTotal);
        }
    }

    /**
     * Rounds the numbers.
     *
     * @param percentage the number to be rounded
     * @return the rounded number
     */
    private double roundNumbers(final double percentage) {
        DecimalFormat df = new DecimalFormat("#.###");
        String roundedPercentage = df.format(percentage);
        /*creates a number*/
        roundedPercentage = roundedPercentage.replace(",", ".");
        return Double.parseDouble(roundedPercentage);

    }

    /**
     * getter of statistical information.
     *
     * @return statistical information object
     */
    public StatisticalInformation getStatisticalInformation() {
        return this.statisticalInformation;
    }

}
