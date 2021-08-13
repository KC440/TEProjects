package com.techelevator.dao;

import com.techelevator.Exception.BadRequestException;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckUser;
import com.techelevator.model.FlashCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createDeck(Deck deck) {

        String sql = "INSERT INTO deck (deck_name, description) " +
                "VALUES(?, ?) RETURNING deck_id;";
        Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class,
                deck.getName(), deck.getDescription());
        deck.setDeckId(deckId);

        return true;
    }

    @Override
    public boolean addDeckUser(Deck deck){

        String sql = "INSERT INTO deck_user( deck_id, user_id)" +
                "VALUES( ?, ?)RETURNING deck_id ;";
        Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckId(), deck.getUserId());
        return true;
    }

    @Override
    public Deck getDeckByDeckId(Integer deckId) {
        Deck deck = null;


        String sql = "SELECT deck_id, deck_name, description from deck WHERE deck_id = ? ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);
        while (rowSet.next()){
            deck = mapRowToDeck(rowSet);
        }
        return deck;
    }

    @Override
    public boolean updateDeckName(Deck deck) {

        String sql = "UPDATE deck SET deck_name = ? " +
                " WHERE deck_id = ?";
        jdbcTemplate.update(sql, deck.getName(),
                deck.getDeckId());
        return true;

    }

    @Override
    public boolean updateDeck(Deck deck) {

        String sql = "UPDATE deck SET deck_name = ?, description = ? " +
                " WHERE deck_id = ?";
        jdbcTemplate.update(sql, deck.getName(), deck.getDescription(),
                deck.getDeckId());
        return true;

    }

    @Override
    public boolean updateDeckContents(FlashCard flashCard) {

//        String sql
        return false;
    }

    @Override
    public List<Deck> getDeckByUserId(Integer userId) {
        List<Deck> decks = new ArrayList<>();

        String sql = "SELECT d.deck_id, d.deck_name, d.description FROM deck d " +
                "JOIN deck_user du ON du.deck_id = d.deck_id " +
                "JOIN users u ON u.user_id = du.user_id " +
                "WHERE u.user_id = ?";


        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()){
            Deck deck = mapRowToDeck(rowSet);
            if (deck != null) {
                decks.add(deck);
            }
        }
        return decks;
    }







    @Override
    public boolean addCardToDeck(Deck deck) throws BadRequestException {

        String sql = "INSERT INTO flashcard_deck( deck_id, flashcard_id)" +
                "VALUES( ?, ?)RETURNING deck_id ;";
        if(deck == null){
            throw new BadRequestException("Null value.");
        }
        if (deck.getCards().length != 0) {
            for (int i = 0; i < deck.getCards().length; i++) {
                Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckId(), deck.getCards()[i]);
            }
        }
        return true;
    }

//if(flashCard.getCards().length != 0) {
//        for(int i = 0; i < deck.getCards().length; i++) {
//            Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class, flashCard.getDeckId(), flashCard.getCards()[i]);
//        }
//    }


//    @Override
//    public boolean addFlashcardToDeck(Integer deckId, FlashCard flashCard) {
//
//        String sql = "INSERT INTO flashcard_deck( deck_id, flashcard_id)" +
//                "VALUES( ?, ?)RETURNING deck_id ;";
//
//        return true;
//
//    }



    @Override
    public List<FlashCard> getFlashcardsIdByDeckId(Integer deckId) {
        String sql = "Select fc.* from flashcard fc, flashcard_deck fd where fc.flashcard_id = fd.flashcard_id" +
                " and fd.deck_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);
        List<FlashCard> flashCards = new ArrayList<>();
        while(rowSet.next()){
            FlashCard flashCard = mapRowToFlashCard(rowSet);
            if(flashCard != null) {
                flashCards.add(flashCard);
            }
        }

        return flashCards;
    }

//    @Override
//    public boolean addFlashcardToDeck(Integer deckId, FlashCard flashCard) {
//        return false;
//    }

    private FlashCard mapRowToFlashCard(SqlRowSet rowSet) {
        FlashCard flashCard = new FlashCard();
        flashCard.setFlashCardId(rowSet.getInt("flashcard_id"));
        flashCard.setQuestion(rowSet.getString("question"));
        flashCard.setAnswer(rowSet.getString("answer"));
        return flashCard;
    }

    @Override
    public Integer[] getCardsByDeckId(int deckId) {
        List<Integer> cards = new ArrayList<>();
        String sql = "SELECT flashcard_id FROM flashcard_deck WHERE deck_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);
        while (rowSet.next()) {
            cards.add(rowSet.getInt("flashcard_id"));
        }
        return cards.toArray(Integer[]::new);
    }


    @Override
    public Integer getUserIdByDeckId(Integer deckId) {
        String sql = "SELECT user_id from deck_user WHERE " +
                "deck_id = ? ;";
        Integer userId = jdbcTemplate.queryForObject(sql, Integer.class, deckId);
        return userId;

    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setName(rowSet.getString("deck_name"));
        deck.setDescription(rowSet.getString("description"));

        return deck;
    }

    private Deck mapRowToDeck2(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setName(rowSet.getString("deck_name"));
        deck.setDescription(rowSet.getString("description"));

        return deck;
    }

}
