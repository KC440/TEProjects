package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Deck {
    private Integer deckId;
    private Integer userId;
    private String name;
    private String description;
    private Integer[] cards;
    private Integer flashCardId;

    public Deck() {
    }

    public Deck(Integer deckId, Integer userId, String name, String description, Integer[] cards, Integer flashCardId) {
        this.deckId = deckId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.cards = cards;
        this.flashCardId = flashCardId;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer[] getCards() {
        return cards;
    }

    public void setCards(Integer[] cards) {
        this.cards = cards;
    }

    public Integer getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(Integer flashCardId) {
        this.flashCardId = flashCardId;
    }
}



