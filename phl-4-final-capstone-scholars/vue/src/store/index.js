import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

//execute command below

// npm install --save vuex-persistedstate

// now uncomment line below and two line above mutation.

import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    flashcards: [],
    decks: [],
    deckId: 0
  },
  // import to make data persistance between reload
  plugins: [createPersistedState()],
  mutations: {  
    SET_FLASHCARDS(state, flashcards) {
      this.state.flashcards = flashcards;
    },
    SET_DECKS(state, decks) {
      this.state.decks = decks;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_FLASHCARD(state, flash) {
      //const card = this.state.flashcards.find(c => c.id === flash.flashcardID);
      //let maxID = card.id * 1000;
      //maxID = card.flash.reduce( (maxID, flash) => {
       // return Math.max(maxID, flash.id);
      //}, maxID);
      //flash.id = maxID + 1;
      //card.flash.unshift(flash);
      this.state.flashcard.creatorID = flash.creatorID;
      this.state.flashcard.question = flash.question;
      this.state.flashcard.answer = flash.answer;
      this.state.flashcard.keywords = flash.keywords;
    },
    SET_DECK_ID(state, deckId){
      this.state.deckId = deckId;
    }
  }
})
