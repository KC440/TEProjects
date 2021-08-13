package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlashCard {

    private Integer flashCardId;
    private String question;
    private String answer;
    private Integer userId;
    private String keyword;
    private String [] keywords;
    private Integer deckId;


    public FlashCard(Integer flashCardId, String question, String answer, Integer userId, String keyword, String[] keywords, Integer deckId) {
        this.flashCardId = flashCardId;
        this.question = question;
        this.answer = answer;
        this.userId = userId;
        this.keyword = keyword;
        this.keywords = keywords;
        this.deckId = deckId;
    }

    public FlashCard (){

    }

    public Integer getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(Integer flashCardId) {
        this.flashCardId = flashCardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }
}
