package com.techelevator.service;

import com.techelevator.Exception.BadRequestException;
import com.techelevator.Exception.NotFoundException;
import com.techelevator.dao.FlashCardDao;
import com.techelevator.model.FlashCard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class FlashCardService {

    private FlashCardDao flashCardDao;

    public FlashCardService (FlashCardDao flashCardDao) {
        this.flashCardDao = flashCardDao;
    }

       public List<FlashCard> getFlashCards(Integer id){
            Map<Integer, List<String>> flashCardMap = new HashMap();
            List<String> keywordList = null;
            List<FlashCard> flashCards = flashCardDao.getFlashcardsByUserId(id);

            for(FlashCard flashCard : flashCards){
                if(flashCardMap.containsKey(flashCard.getFlashCardId())){
                    flashCardMap.get(flashCard.getFlashCardId()).add(flashCard.getKeyword());
                }else{
                    keywordList = new ArrayList<>();
                    keywordList.add(flashCard.getKeyword());
                    flashCardMap.put(flashCard.getFlashCardId(), keywordList);
                }
            }

            List<FlashCard> flashCardList = new ArrayList<>();
            for(Map.Entry<Integer,List<String>> entry : flashCardMap.entrySet()) {
                FlashCard flashCardNew = new FlashCard();
                for (FlashCard flashCard : flashCards) {
                    if(entry.getKey().compareTo(flashCard.getFlashCardId()) == 0){
                        flashCardNew.setFlashCardId(entry.getKey());
                        flashCardNew.setQuestion(flashCard.getQuestion());
                        flashCardNew.setAnswer(flashCard.getAnswer());
                        flashCardNew.setKeywords(entry.getValue().toArray(String[]::new));
                    }
                }
                flashCardList.add(flashCardNew);
            }
            return flashCardList;
       }

    public List<FlashCard> getFlashCardsByDeckId(Integer id){
        Map<Integer, List<String>> flashCardMap = new HashMap();
        List<String> keywordList = null;
        List<FlashCard> flashCards = flashCardDao.getFlashcardsByDeckId(id);

        for(FlashCard flashCard : flashCards){
            if(flashCardMap.containsKey(flashCard.getFlashCardId())){
                flashCardMap.get(flashCard.getFlashCardId()).add(flashCard.getKeyword());
            }else{
                keywordList = new ArrayList<>();
                keywordList.add(flashCard.getKeyword());
                flashCardMap.put(flashCard.getFlashCardId(), keywordList);
            }
        }

        List<FlashCard> flashCardList = new ArrayList<>();
        for(Map.Entry<Integer,List<String>> entry : flashCardMap.entrySet()) {
            FlashCard flashCardNew = new FlashCard();
            for (FlashCard flashCard : flashCards) {
                if(entry.getKey().compareTo(flashCard.getFlashCardId()) == 0){
                    flashCardNew.setFlashCardId(entry.getKey());
                    flashCardNew.setQuestion(flashCard.getQuestion());
                    flashCardNew.setAnswer(flashCard.getAnswer());
                    flashCardNew.setKeywords(entry.getValue().toArray(String[]::new));
                }
            }
            flashCardList.add(flashCardNew);
        }
        return flashCardList;
    }

       public boolean  createNewFlashCard( FlashCard flashCard) throws Exception {

            FlashCard existingFlashCard =  flashCardDao.getFlashCardByQuestion(flashCard.getQuestion(), flashCard.getAnswer(), flashCard.getUserId());
            if(existingFlashCard != null) {
                throw new BadRequestException("Flash Card Already Exist");
            }
            flashCardDao.createFlashCard(flashCard);
            flashCardDao.addFlashCardUser(flashCard);
            flashCardDao.addFlashCardKeywords(flashCard);

            return true;
        }

        public void updateExistingFlashCard(FlashCard flashCard) throws Exception{

        Integer userId = flashCardDao.getUserIdByFlashcardId(flashCard.getFlashCardId());

            if(userId == null){
                throw new BadRequestException("Flash Card does not Exist");
            }
            flashCardDao.updateFlashCard(flashCard);
            flashCardDao.deleteAllFlashcardKeywords(flashCard.getFlashCardId());
            flashCardDao.addFlashCardKeywords(flashCard);

        }


       public List <FlashCard> getFlashCardsByKeyword(Integer userId, String keyword) throws NotFoundException {
            List<FlashCard> flashCards = flashCardDao.getFlashCardByKeyword(userId, keyword);
            if(flashCards.size() == 0){
                throw new NotFoundException("Flash Card for the key word Not Found");
            }
            return flashCards;
        }

        public void deleteFlashcardById(int cardId) {
            flashCardDao.deleteAllFlashcardKeywords(cardId);
            flashCardDao.deleteFlashcardFromDeck(cardId);
            flashCardDao.deleteFlashcardUser(cardId);
            flashCardDao.deleteFlashcard(cardId);
        }


}
