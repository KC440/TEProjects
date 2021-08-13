package com.techelevator.model;

public class FlashCardDeck {
    private Integer deckId;
    private Integer flashCardId;

    public FlashCardDeck() {
    }

    public FlashCardDeck(Integer deckId, Integer flashCardId) {
        this.deckId = deckId;
        this.flashCardId = flashCardId;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public Integer getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(Integer flashCardId) {
        this.flashCardId = flashCardId;
    }
}
