package nl.bioinf.diseasefinderSpring.controllers;

import nl.bioinf.diseasefinderSpring.domain.SearchHistoryRepository;
import nl.bioinf.diseasefinderSpring.domain.UserRepository;
import nl.bioinf.diseasefinderSpring.symptomsdatabase.SymptomsCalculationInformation;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bas on 21-5-16.
 */

@Controller
public class StatisticsController {
    @Autowired
    SearchHistoryRepository searchHistoryRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
     * and various simple DataSource implementations.
     */
    @Autowired
    DataSource dataSource;
    @RequestMapping(value = "/showstatistics")
    public void getStatistics(Model model) {
        SymptomsCalculationInformation symptomsCalculationInformation =
                new SymptomsCalculationInformation(userRepository, searchHistoryRepository);
        symptomsCalculationInformation.calculateSymptomsSearch();
        System.out.println("hier?");
        model.addAttribute("statistics", symptomsCalculationInformation.getStatisticalInformation());

    }
}
