package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;

import static java.util.Objects.requireNonNull;

/**
 * Created by henridupon on 5/4/2016.
 */
public class LoadSearchedSymptoms {

    Authentication authentication;
    NamedParameterJdbcTemplate jdbcTemplate;

    //    @Autowired
    public LoadSearchedSymptoms(Authentication authentication, NamedParameterJdbcTemplate jdbcTemplate) {
        this.authentication = requireNonNull(authentication);
        this.jdbcTemplate = requireNonNull(jdbcTemplate);
    }

//    public List <SearchHis> SaveSearchedSymptoms (String username, final NamedParameterJdbcTemplate jdbcTemplate) {
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