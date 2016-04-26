package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.controllers.SearchHis;
import nl.bioinf.diseasefinderSpring.controllers.SearchRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import java.util.List;

/**
 * Created by hjdupon on 21-4-16.
 */
@Service
public class GetSearchHistory {

    String info;

    @Autowired
    Authentication authentication;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List <SearchHis> GetSearchHistory (String username, final NamedParameterJdbcTemplate jdbcTemplate) {

        RowMapper rowMapper = new SearchRowMapper();  // reusable object
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        String sql = String.format("SELECT * from history where username = '%s'", username);

        List<SearchHis> mySQLSearchHistory =  jdbcTemplate.query(sql, new MapSqlParameterSource(), BeanPropertyRowMapper.newInstance(SearchHis.class));

        SearchHis dit = new SearchHis();

        for (SearchHis item : mySQLSearchHistory){
            System.out.println(item.getusername());
            dit.setsearchedsymptoms(item.getsearchedsymptoms());
            System.out.println(item.getsearchedsymptoms());
            info = item.getsearchedsymptoms();
        }

        return mySQLSearchHistory;

    }

}

