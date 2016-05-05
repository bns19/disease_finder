package nl.bioinf.diseasefinderSpring.controllers;

/**
 * Created by hjdupon on 25-4-16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class SearchController extends WebMvcConfigurerAdapter {

//    List<SearchHis> ListWithMysqlInformation;
//
//    /**
//     * Make the jdbcTemplate usable in the class.
//     * This is the database connector.
//     */
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;
//
//    /**
//     * Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
//     * and various simple DataSource implementations.
//     */
//    @Autowired
//    DataSource dataSource;
//
//    @RequestMapping(value = "/history", method = RequestMethod.GET)
//    @ResponseBody
//    public Object executeHis(final User user, HttpSession session) {
//
//        System.out.println("print");
//
////        ListWithMysqlInformation = dit.SaveSearchedSymptoms("klaasje", jdbcTemplate);
//
////        model.addAttribute("ListWithMysqlInformation", ListWithMysqlInformation); // This is important
//
////        UserForm personform = new UserForm();
////        personform.setUsername("HANS");
//
//        session.setAttribute("mySessionAttribute", "someValue");
//
//        System.out.println("HIER");
//
//        return ListWithMysqlInformation;
//    }


}