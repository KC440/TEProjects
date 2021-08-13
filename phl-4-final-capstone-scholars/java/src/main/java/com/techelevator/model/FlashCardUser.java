package com.techelevator.model;

public class FlashCardUser {
    private Integer userId;
    private Integer flashCardId;

    public FlashCardUser() {
    }

    public FlashCardUser(Integer userId, Integer flashCardId) {
        this.userId = userId;
        this.flashCardId = flashCardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(Integer flashCardId) {
        this.flashCardId = flashCardId;
    }
}
