package com.careerfocus.dao;

import com.careerfocus.entity.States;
import com.careerfocus.model.request.SaveQuestionVO;
import com.careerfocus.model.response.QuestionPopulateVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class QuestionDAO {

    private JdbcTemplate template;

    @Autowired
    public QuestionDAO(JdbcTemplate template) {
        this.template = template;
    }

    public List<States> getStates() {

        String query = "SELECT * FROM states";

        return template.query(query, (ResultSet result, int arg1) ->
                new States(result.getInt("state_id"), result.getString("name")));
    }

	public QuestionPopulateVO getQuestion(int examId, int qId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveQuestion(SaveQuestionVO question) {
		int qStatus = 0;
		// TODO Auto-generated method stub
		return qStatus;
	}

}