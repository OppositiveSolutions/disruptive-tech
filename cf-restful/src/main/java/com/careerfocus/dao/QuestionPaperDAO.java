package com.careerfocus.dao;

import com.careerfocus.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionPaperDAO {

//    private static final Logger log = Logger.getLogger(DateUtils.class.getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public  QuestionPaperDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateLastModified(List<Integer> qIds) {

        String query = "UPDATE `career_focus`.`question_paper` SET `last_modified` = current_timestamp() WHERE `question_paper_id`=?";

        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, qIds.get(i));
            }

            @Override
            public int getBatchSize() {
                return qIds.size();
            }
        });
    }

    public void updateLastModidiedByQuestionPaperSubCategoryIds(List<Integer> ids) {
        if (ids.isEmpty())
            return;

        StringBuilder query = new StringBuilder("UPDATE career_focus.question_paper qp "
                + "INNER JOIN question_paper_category qpc ON qp.question_paper_id=qpc.question_paper_id "
                + "INNER JOIN question_paper_sub_category qpsc ON qpc.question_paper_category_id=question_paper_category_id "
                + "SET qp.`last_modified` = current_timestamp() "
                + "WHERE qpsc.`question_paper_sub_category_id` IN (" + DBUtils.getMarkers(ids.size()) + ")");
        for (int id : ids)
            query.append(id).append(", ");
        query = new StringBuilder(query.substring(0, query.length() - 2) + ")");

        jdbcTemplate.update(query.toString(), ids.toArray());
    }

}
