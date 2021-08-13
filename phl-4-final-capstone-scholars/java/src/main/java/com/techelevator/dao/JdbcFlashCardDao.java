package com.techelevator.dao;

import com.techelevator.model.FlashCard;
import com.techelevator.model.FlashCardKeyword;
import com.techelevator.model.FlashCardUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFlashCardDao implements FlashCardDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFlashCardDao(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FlashCard getFlashCardByQuestion(String question, String answer, Integer userId){
        FlashCard flashCard = null;
        String sql = "SELECT f.flashcard_id , f.question, f.answer FROM flashcard f " +
                "JOIN flashcard_user fu ON f.flashcard_id = fu.flashcard_id " +
                "JOIN users u ON  u.user_id = fu.user_id " +
                " WHERE question = ? AND answer = ?  AND u.user_id = ? ;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, question, answer, userId);
        if(rowSet.next()){
             flashCard = mapRowToFlashCard(rowSet);
        }
        return flashCard;
    }
//userid and flashcard id to get flashcard
    @Override
    public FlashCard getFlashCardById(Integer flashCardId, Integer userId){

        FlashCard flashCard = null;
        String sql = "SELECT f.flashcard_id , f.question, f.answer FROM flashcard f " +
                "JOIN flashcard_user fu ON f.flashcard_id = fu.flashcard_id " +
                "JOIN users u ON  u.user_id = fu.user_id " +
                " WHERE f.flashcard_id = ?  AND u.user_id = ? ;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, flashCardId, userId);
        if(rowSet.next()){
            flashCard = mapRowToFlashCard(rowSet);
        }
        return flashCard;
    }


    @Override
    public boolean createFlashCard(FlashCard flashCard){

        String sql = "INSERT INTO flashcard( question, answer) " +
                "VALUES( ?, ?) RETURNING flashcard_id;";
        Integer flashCardId = jdbcTemplate.queryForObject(sql, Integer.class,
                 flashCard.getQuestion(), flashCard.getAnswer());
        flashCard.setFlashCardId(flashCardId);

        return true;
    }

    @Override
    public boolean addFlashCardUser(FlashCard flashCard){

        String sql = "INSERT INTO flashcard_user( flashcard_id, user_id)" +
                "VALUES( ?, ?)RETURNING flashcard_id ;";
         Integer flashcardId = jdbcTemplate.queryForObject(sql, Integer.class,flashCard.getFlashCardId(), flashCard.getUserId());

        return true;
    }

    @Override
    public void addFlashCardKeywords(FlashCard flashCard){

        String [] keywords = flashCard.getKeywords();
        for (int i = 0; i < keywords.length; i++ ){

            String sql = "INSERT INTO flashcard_keyword( flashcard_id, keyword)  " +
                    "VALUES( ?, ?) RETURNING flashcard_id;";
            Integer flashcardId = jdbcTemplate.queryForObject(sql, Integer.class,flashCard.getFlashCardId(), keywords[i]);

        }

    }
    @Override
    public List<FlashCard> getFlashcardsByUserId(Integer userId) {
        List<FlashCard> flashCards = new ArrayList<>();

        String sql = "SELECT f.flashcard_id, f.question, f.answer, ck.keyword FROM Flashcard f " +
                "JOIN flashcard_keyword ck ON  f.flashcard_id = ck.flashcard_id " +
                "JOIN flashcard_user fu ON fu.flashcard_id = f.flashcard_id  " +
                "WHERE user_id = ?; ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while(rowSet.next()) {
           FlashCard flashCard = mapRowToUserFlashCard(rowSet);
           if(flashCard != null) {
               flashCards.add(flashCard);
           }
        }
        return flashCards;
    }
    @Override
    public List<FlashCard> getFlashcardsByDeckId(Integer deckId) {
        List<FlashCard> flashCards = new ArrayList<>();

        String sql = "SELECT f.flashcard_id, f.question, f.answer, ck.keyword FROM Flashcard f " +
                "JOIN flashcard_keyword ck ON  f.flashcard_id = ck.flashcard_id " +
                "JOIN flashcard_deck fd ON fd.flashcard_id = f.flashcard_id  " +
                "WHERE fd.deck_id = ?; ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);
        while(rowSet.next()) {
            FlashCard flashCard = mapRowToUserFlashCard(rowSet);
            if(flashCard != null) {
                flashCards.add(flashCard);
            }
        }
        return flashCards;
    }


    @Override
    public List<FlashCard> getFlashCardByKeyword(Integer userId, String keyword) {
        
        List<FlashCard> flashCards = new ArrayList<>();
        String sql = "SELECT f.flashcard_id, f.question, f.answer FROM flashcard f  " +
                "JOIN flashcard_keyword ck ON f.flashcard_id = ck.flashcard_id " +
                "JOIN flashcard_user fu ON f.flashcard_id = fu.flashcard_id " +
                "WHERE ck.keyword  ILIKE  ? AND fu.user_id = ?; ";

        SqlRowSet rowSet = null;
        if(keyword == null || keyword.length() == 0){
            rowSet = jdbcTemplate.queryForRowSet(sql, keyword, userId );
        } else {
            rowSet = jdbcTemplate.queryForRowSet(sql, '%' + keyword + '%', userId);
        }
        while(rowSet.next()){
            FlashCard flashCard = mapRowToFlashCard(rowSet);
            if(flashCard != null) {
                flashCards.add(flashCard);
            }
        }

        return flashCards;
    }

    @Override
    public void updateFlashCard(FlashCard flashCard) {
        String sql = " UPDATE flashcard AS f  " +
                "                SET question = ?, answer = ? " +
                "                FROM flashcard_user AS fu, users AS u " +
                "                where f.flashcard_id = fu.flashcard_id  " +
                "                AND u.user_id = fu.user_id " +
                "                AND f.flashcard_id = ? AND fu.user_id = ?;";
        jdbcTemplate.update(sql, flashCard.getQuestion(),flashCard.getAnswer(),
                flashCard.getFlashCardId(), flashCard.getUserId());
    }

    //method to check on for update.
    //if the flashcard belongs to user
    @Override
    public Integer getUserIdByFlashcardId(Integer flashCardId){
        String sql = "SELECT user_id from flashcard_user WHERE " +
                "flashcard_id = ? ;";
        Integer userId = jdbcTemplate.queryForObject(sql, Integer.class, flashCardId);
        return userId;

    }
    @Override
    public void deleteAllFlashcardKeywords(Integer cardId) {
        String sql = "DELETE FROM flashcard_keyword WHERE flashcard_id = ?";
        jdbcTemplate.update(sql, cardId);
    }
    @Override
    public void deleteFlashcard(int cardId) {
        String sql = "DELETE FROM flashcard WHERE flashcard_id = ?";
        jdbcTemplate.update(sql, cardId);
    }
    @Override
    public void deleteFlashcardUser(int cardId) {
        String sql = "DELETE FROM flashcard_user WHERE flashcard_id = ?";
        jdbcTemplate.update(sql, cardId);
    }
    @Override
    public void deleteFlashcardFromDeck(int cardId) {
        String sql = "DELETE FROM flashcard_deck WHERE flashcard_id = ?";
        jdbcTemplate.update(sql, cardId);
    }

    private FlashCard mapRowToFlashCard(SqlRowSet rowSet) {
        FlashCard flashCard = new FlashCard();
        flashCard.setFlashCardId(rowSet.getInt("flashcard_id"));
        flashCard.setQuestion(rowSet.getString("question"));
        flashCard.setAnswer(rowSet.getString("answer"));
        return flashCard;
    }

    private FlashCard mapRowToUserFlashCard(SqlRowSet rowSet) {
        FlashCard flashCard = new FlashCard();
        flashCard.setFlashCardId(rowSet.getInt("flashcard_id"));
        flashCard.setQuestion(rowSet.getString("question"));
        flashCard.setAnswer(rowSet.getString("answer"));
        flashCard.setKeyword(rowSet.getString("keyword"));
        return flashCard;
    }






}
