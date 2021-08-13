<template>
  <div>
    <h2>Select a deck to begin study session</h2>
    <div class="deckFlexContainer">
      <!-- <router-link
                              to="home"
                              v-on:click="updateDeckId(deck.deckId, $event)"
                              :disabled=true
                              class="board"
                              v-for="deck in myDecks"
                              v-bind:key="deck.deck_id"
                              v-bind:style="{ 'background-color': randomBackgroundColor() }"
                              tag="div"
                          > {{deck.name}}
                          </router-link> -->

      <div
        v-on:click="
          updateDeckId(deck.deckId, $event);
          startSession(deck.deckId);
        "
        :disabled="true"
        class="deckRep"
        v-for="deck in myDecks"
        v-bind:key="deck.deckId"
        v-bind:id="deck.deckId"
      >
        {{ deck.name }}
      </div>
    </div>
  </div>
</template>
<script>
import DeckService from "@/services/DeckService";
export default {
  name: "choose-deck",
  components: {},
  created() {
    DeckService.getMyDecks(this.$store.state.user.id).then((response) => {
      const decks = response.data;
      //this.$store.commit("SET_DECKS", decks);
      this.myDecks = decks;
      if (response.status === 200 && response.data.length > 0) {
        this.$store.commit("SET_DECK_ID", response.data[0].deckId);
        // this.$router.push(`/deckBoard/${response.data[0].deckId}`);
      }
    });
  },
  data() {
    return {
      // myDecks: this.$store.state.decks
      myDecks: [],
      newDeck: {
        deck_id: 0,
        name: "",
        backgroundColor: this.randomBackgroundColor(),
        isClicked: false,
      },
      userId: this.$store.state.user.id,
      errorMsg: "",
      showAddDeck: false,
      myNewDeck: {
        name: "",
        description: "",
        userId: 0,
      },
    };
  },
  methods: {
    startSession(deckId) {
      this.$router.push(`/session/${deckId}/${0}`);
    },
    randomBackgroundColor() {
      return "#" + this.generateHexCode();
    },
    generateHexCode() {
      // var bg = Math.floor(Math.random()*16777215).toString(16);
      // if (bg.length !== 6) bg = this.generateHexCode();
      // return bg;
      return "ffffff";
    },
    updateDeckId(deckId, event) {
      // document.getElementById(event.currentTarget.id).style.backgroundColor =
      //  "green";
      console.log(event.currentTarget.id);
      this.$store.commit("SET_DECK_ID", deckId);
    },
    saveNewDeck() {
      this.myNewDeck.userId = this.userId;
      DeckService.addDeck(this.myNewDeck)
        .then((response) => {
          if (response.status === 201) {
            this.retrieveDecks();
            this.showAddDeck = false;
            this.newDeck = {
              name: "",
              description: "",
            };
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error submitting new Deck. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error submitting new Deck. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error submitting new Deck. Request could not be created.";
          }
          this.isLoading = false;
        });
    },
  },
};
</script>

<style>
.deckFlexContainer {
  display: flex;
  margin-left: 40px;
  align-items: center;
  justify-content: center;
  border: 3px solid antiquewhite;
  border-radius: 20px;
  width: 75vw;
  margin: 0 auto;
}
.deckRep {
  margin: 7px;
  padding: 10px;
  border: 1px solid black;
  border-radius: 3px;
  background-color: turquoise;
  color: black;
  font-family: "Monoton", sans-serif;
  text-decoration: none;
  font-size: 40px;
  font-family: "Love Ya Like A Sister", sans-serif;
  cursor: pointer;
}
.deckRep:hover {
  background-color: yellowgreen;
}
</style>