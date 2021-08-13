package com.techelevator.controller;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashCard;
import com.techelevator.service.DeckService;
import com.techelevator.service.FlashCardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DeckController {

    private DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }


    @RequestMapping(path ="/deck", method= RequestMethod.POST)
    public boolean createDeck(@RequestBody Deck deck) throws Exception{
        return deckService.createDeck((deck));
    }
    @RequestMapping(path = "/deck/{userId}", method= RequestMethod.GET)
    public List<Deck> getDeckByUser(@PathVariable Integer userId) throws Exception{
        return deckService.getDeckByUser(userId);
    }

    @RequestMapping(path ="/deck", method= RequestMethod.PUT)
    public void updateDeck(@RequestBody Deck deck) throws Exception{
        deckService.updateExistingDeck((deck));
    }



    @RequestMapping(path ="/deck/flashCards", method= RequestMethod.POST)
    public boolean addCardToDeck(@RequestBody FlashCard flashCard) throws Exception{
        return deckService.addFlashCardToDeck((flashCard));
    }

    @RequestMapping(path = "/decks/{deckId}/flashCards", method = RequestMethod.GET)
    public List<FlashCard> getFlashcardByDeckId(@PathVariable Integer deckId) {
        return deckService.getFlashcardsByDeckId(deckId);
    }

    @RequestMapping(path ="/decks/{deckId}/flashCards", method= RequestMethod.POST)
    public boolean addCardToDeck(@PathVariable Integer deckId,@RequestBody FlashCard flashCard) throws Exception{
        deckService.addCardToDeck(deckId,flashCard);
        return true;
    }


}
