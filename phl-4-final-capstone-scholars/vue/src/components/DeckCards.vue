<template>
  <div>
    <div class="status-message" v-show="errorMsg !== ''">{{ errorMsg }}</div>
    <h2 class="flashcardTitle"><th>My FlashCards</th></h2>
    <div>
      <router-link
        tag="button"
        class="btn addCard"
        :to="{ name: 'addFlashcardToDeck' }"
        >Add New Card</router-link
      >

      <router-link
        tag="button"
        class="btn modifyDeck"
        :to="{ name: 'modifyDeck' }"
        >Edit Deck</router-link
      >

      <!-- <router-link
        tag="button"
        class="btn addCard"
        :to="{ name: 'addFlashcard'}"
        >Add Existing Card</router-link> -->
    </div>

    <div class="body">
      <div v-for="card in this.getDeckCards" v-bind:key="card.flashCardId">
        <!-- this should display  "flashcard-question"-->
        <!-- <div class="card" v-bind:id="card.flashCardId" + 'Flashcard' v-on:click="selectCardsForDelete('Flashcard'+ card.flashCardId)">{{ card.question }}</div> -->
        <div class="card">{{ card.question }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import DeckService from "@/services/DeckService";
export default {
  name: "my-cards-list",
  components: {},
  data() {
    return {
      flashCards: [],
      deckId: -1,
      errorMsg: "",
      mySelection: [],
      // showAddDeck: false,
      // newDeck: {
      //     name: '',
      //     description: ''
      // },
    };
  },

  computed: {
    getDeckCards() {
      let deckId = this.$store.state.deckId;
      if (deckId != this.deckId) {
        if (deckId != undefined) {
          this.retrieveCards(deckId);
        }
      }
      return this.flashCards;
    },
  },
  created() {
    //this.deckId = this.$store.state.deckId
    //this.deckId = this.$route.params.id;
    //this.retrieveCards();
  },
  methods: {
    selectCardsForDelete(cardId) {
      if (!this.mySelection.includes(cardId)) {
        this.mySelection.push(cardId);
        //this.$refs[cardId].style.backgroundColor = "green";
        //document.getElementById(event.currentTarget.id).style.backgroundColor = "green";
      } else {
        const index = this.mySelection.indexOf(cardId);
        if (index > -1) {
          this.mySelection.splice(index, 1);
        }
        //document.getElementById(event.currentTarget.id).style.backgroundColor = "white";
        //this.$refs[cardId].style.backgroundColor = "white";
        console.log(this.$refs[cardId]);
      }
    },

    retrieveCards(deckId) {
      DeckService.getDeckCards(deckId)
        .then((response) => {
          if (response.status === 200) {
            this.deckId = deckId;
            this.flashCards = response.data;
          }
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Board cards not available. This Deck may have been deleted or you have entered an invalid Deck ID."
            );
          }
        });
    },
  },
};
</script>



<style scoped>
/* label {
         display: block; 
    } */

.flashcardTitle {
  margin-bottom: 8px;
}
.flashcard-search {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  border-radius: 0.2em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: "Roboto", sans-serif;
  font-weight: 400;
  color: #ffffff;
  background-color: #3369ff;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  position: relative;
}

.search-keyword {
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}

.body {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  border: 3px solid antiquewhite;
  border-radius: 20px;
  width: 75vw;
  margin: 0 auto;
}

.card {
  margin: 7px;
  padding: 10px;
  border: 1px solid black;
  width: 250px;
  height: 125px;
  align-content: center;
  border-radius: 3px;
  background-image: url("../images/noteCard.jpg");
  background-size: cover;
  color: navy;
  text-decoration: none;
  font-size: 40px;
  font-family: "Love Ya Like A Sister", sans-serif;
  cursor: pointer;
  line-height: 125px;
}

.card-list {
  display: grid;
  grid-gap: 1em;
}

.card-item {
  background-color: dodgerblue;
  padding: 2em;
}

body {
  background: #20262e;
  padding: 20px;
  font-family: Helvetica;
}

#app {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  transition: all 0.2s;
}

ul {
  list-style-type: none;
}
</style>