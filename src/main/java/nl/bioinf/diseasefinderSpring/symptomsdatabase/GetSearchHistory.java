package nl.bioinf.diseasefinderSpring.symptomsdatabase;

import nl.bioinf.diseasefinderSpring.controllers.SearchHis;
import nl.bioinf.diseasefinderSpring.controllers.SearchRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hjdupon on 21-4-16.
 */
@Service
public class GetSearchHistory {

    /**
     * Make the jdbcTemplate approachable.
     * There can only be one jdbcTemplate be made, in the WebSecurityConfig the Autowiring caused errors
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public List<SearchHis> GetSearchHistory (int id) {
        RowMapper rowMapper = new SearchRowMapper();  // reusable object
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = String.format("SELECT datetime from history where id ='%s'", id);

        System.out.println("print uitkomst");

        List dat = this.jdbcTemplate.query(sql, new MapSqlParameterSource(), BeanPropertyRowMapper.newInstance(SearchHis.class));

        System.out.println(dat.toString());

        return dat;

    }
}

