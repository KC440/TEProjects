package com.techelevator.service;


import com.techelevator.Exception.BadRequestException;
import com.techelevator.dao.DeckDao;
import com.techelevator.dao.FlashCardDao;
import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {

    private DeckDao deckDao;
    private FlashCardDao flashCardDao;
    private FlashCardService flashCardService;

    public DeckService (DeckDao deckDao, FlashCardDao flashCardDao, FlashCardService flashCardService) {

        this.deckDao = deckDao;
        this.flashCardDao = flashCardDao;
        this.flashCardService = flashCardService;
    }

    public boolean createDeck( Deck deck) throws BadRequestException {
        deckDao.createDeck(deck);
        deckDao.addDeckUser(deck);
        if(deck.getCards() != null) {
        deckDao.addCardToDeck(deck);
        }



        return true;
    }

    public List<Deck> getDeckByUser(Integer userId){

        return deckDao.getDeckByUserId(userId);
    }

    public void updateExistingDeck(Deck deck) throws Exception{

        Deck existingDeck =  deckDao.getDeckByDeckId(deck.getDeckId());
        if(existingDeck == null){
            throw new BadRequestException("Deck does not Exist");
        }
        deckDao.updateDeck(deck);

    }

    public boolean  addFlashCardToDeck( FlashCard flashCard) throws Exception {

        Integer userId = deckDao.getUserIdByDeckId(flashCard.getDeckId());
        if(userId == null){
            throw new BadRequestException("Deck does not Exist");
        }
        //deckDao.addCardToDeck(flashCard);

        return true;

    }



    public boolean addCardToDeck(Integer deckId, FlashCard flashCard) throws Exception {

        Integer userId = deckDao.getUserIdByDeckId(flashCard.getDeckId());
        if(userId == null){
            throw new BadRequestException("Deck does not Exist");
        }
        flashCardService.createNewFlashCard(flashCard);
        Deck deck = new Deck();
        deck.setDeckId(deckId);
        Integer[] flashCardList = new Integer[1];
        flashCardList[0] = flashCard.getFlashCardId();
        deck.setCards(flashCardList);
        deckDao.addCardToDeck(deck);

        return true;

    }


    public List<FlashCard>  getFlashcardsByDeckId(Integer deckId) {
        return deckDao.getFlashcardsIdByDeckId(deckId);
    }




}
