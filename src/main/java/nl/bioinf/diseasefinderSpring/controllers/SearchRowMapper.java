package nl.bioinf.diseasefinderSpring.controllers;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hjdupon on 21-4-16.
 */
public class SearchRowMapper implements RowMapper {

    public Object SearchRowMapper(ResultSet rs, int rowNum) throws SQLException {
        SearchHis search = new SearchHis();
        search.setdatetime(rs.getString("datetime"));
        search.setsearchedsymptoms(rs.getString("searchedsymptoms"));

        return search;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        return new int[0];
    }
}