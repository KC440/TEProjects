package com.techelevator.model;

public class DeckUser {
    private Integer userId;
    private Integer deckId;

    public DeckUser() {
    }

    public DeckUser(Integer userId, Integer deckId) {
        this.userId = userId;
        this.deckId = deckId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }
}
