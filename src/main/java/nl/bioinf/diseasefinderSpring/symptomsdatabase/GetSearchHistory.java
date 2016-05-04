package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;

import static java.util.Objects.requireNonNull;

/**
 * Created by hjdupon on 21-4-16.
 */
//@Service
public class GetSearchHistory {

    Authentication authentication;
    NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
    public GetSearchHistory(Authentication authentication, NamedParameterJdbcTemplate jdbcTemplate) {
        this.authentication = requireNonNull(authentication);
        this.jdbcTemplate = requireNonNull(jdbcTemplate);
    }

//    public List <SearchHis> GetSearchHistory (String username, final NamedParameterJdbcTemplate jdbcTemplate) {
//
//        RowMapper rowMapper = new SearchRowMapper();  // reusable object
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("username", username);
//        String sql = String.format("SELECT * from history where username = '%s'", username);
//
//        List<SearchHis> mySQLSearchHistory =  jdbcTemplate.query(sql, new MapSqlParameterSource(), BeanPropertyRowMapper.newInstance(SearchHis.class));
//
//        SearchHis dit = new SearchHis();
//
//        for (SearchHis item : mySQLSearchHistory){
//            System.out.println(item.getusername());
//            dit.setSearchedSymptoms(item.getSearchedSymptoms());
//            System.out.println(item.getSearchedSymptoms());
//            info = item.getSearchedSymptoms();
//        }
//
//        return mySQLSearchHistory;
//
//    }

}

