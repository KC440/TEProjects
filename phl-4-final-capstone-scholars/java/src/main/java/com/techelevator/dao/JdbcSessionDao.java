package com.techelevator.dao;

import com.techelevator.model.FlashCard;
import com.techelevator.model.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSessionDao implements SessionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createSession(Session session) {
        String sql = "INSERT INTO sessions(deck_id, user_id, attempted_quiz,mark_right , mark_wrong, total_right, session_date) " +
                "VALUES(  ?,?,?,?,?,?,CURRENT_DATE) RETURNING session_id;";
        Integer sessionId = jdbcTemplate.queryForObject(sql, Integer.class, session.getDeckId(),
                session.getUserId(), session.getAttemptedQuiz(), session.getMarkRight(), session.getMarkWrong(),
                session.getTotalRight());
        session.setSessionId(sessionId);

        return true;


    }

}
