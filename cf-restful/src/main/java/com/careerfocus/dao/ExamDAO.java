package com.careerfocus.dao;

import com.careerfocus.entity.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ExamDAO {

    private JdbcTemplate template;

    @Autowired
    public ExamDAO(JdbcTemplate template) {
        this.template = template;
    }

    public List<States> getStates() {

        String query = "SELECT * FROM states";

        return template.query(query, (ResultSet result, int arg1) ->
                new States(result.getInt("state_id"), result.getString("name")));
    }

	public String getCategorieList(int examId) {
		// TODO Auto-generated method stub
		return null;
	}

}
