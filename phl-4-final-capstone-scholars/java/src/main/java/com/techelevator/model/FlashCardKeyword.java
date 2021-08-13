package com.techelevator.model;

public class FlashCardKeyword {
    private Integer flashCardId;
    private String[] keyword;

    public FlashCardKeyword() {
    }

    public FlashCardKeyword(Integer flashCardId, String[] keyword) {
        this.flashCardId = flashCardId;
        this.keyword = keyword;
    }

    public Integer getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(Integer flashCardId) {
        this.flashCardId = flashCardId;
    }

    public String[] getKeyword() {
        return keyword;
    }

    public void setKeyword(String[] keyword) {
        this.keyword = keyword;
    }
}
