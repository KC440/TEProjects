package com.techelevator.dao;

import com.techelevator.Exception.BadRequestException;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckUser;
import com.techelevator.model.FlashCard;

import java.util.List;

public interface DeckDao {

    boolean createDeck(Deck Deck);

    boolean addDeckUser(Deck deck);

    Deck getDeckByDeckId(Integer deckId);

    boolean updateDeckName(Deck deck);

    boolean updateDeck(Deck deck);

    boolean updateDeckContents(FlashCard flashCard);

    List<Deck> getDeckByUserId(Integer userId);

    boolean addCardToDeck(Deck deck) throws BadRequestException;

    Integer getUserIdByDeckId(Integer deckId);

    Integer[] getCardsByDeckId(int deckId);

    List<FlashCard> getFlashcardsIdByDeckId(Integer deckId);

//    boolean addFlashcardToDeck(Integer deckId, FlashCard flashCard);
}
