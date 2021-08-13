<template>
  <div id="sideNav">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <h2><th>My Decks</th></h2>
    <div class="boards">
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

      <div>
        <div
          v-on:click="updateDeckId(deck.deckId, $event)"
          :disabled="true"
          v-bind:class="{ selected: mySelection.includes(deck.deckId) }"
          class="board"
          v-for="deck in myDecks"
          v-bind:key="deck.deckId"
          v-bind:id="deck.deckId"
          tag="div"
        >
          {{ deck.name }}
        </div>
      </div>

      <button
        class="btn addBoard"
        v-if="!showAddDeck"
        v-on:click="showAddDeck = !showAddDeck"
      >
        Add Deck
      </button>
      <form v-if="showAddDeck">
        <table>
          <tr>
            <td>
              Deck Name:
              <input
                type="text"
                class="form-control"
                v-model="myNewDeck.name"
              />
            </td>
          </tr>
          <tr>
            <td>
              Deck Description:
              <input
                type="text"
                class="form-control"
                v-model="myNewDeck.description"
              />
            </td>
          </tr>
          <tr>
            <td>
              <button class="btn" v-on:click="saveNewDeck()">Save</button>

              <button class="btn" v-on:click="showAddDeck = !showAddDeck">
                Cancel
              </button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</template>

<script>
import DeckService from "@/services/DeckService";
export default {
  name: "my-decks-list",
  components: {},
  created() {
    DeckService.getMyDecks(this.$store.state.user.id).then((response) => {
      const decks = response.data;
      this.$store.commit("SET_DECKS", decks);
      this.myDecks = decks;
      if (response.status === 200 && response.data.length > 0) {
        this.mySelection.push(response.data[0].deckId);
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
      mySelection: [],
      myNewDeck: {
        name: "",
        description: "",
        userId: 0,
      },
    };
  },
  methods: {
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
      if (!this.mySelection.includes(deckId)) {
        this.mySelection = [];
        this.mySelection.push(deckId);
      }

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



<style scoped>
div#sideNav {
  height: 90%;
  width: 20%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 40px;
  padding-bottom: 20px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
  margin-top: 30px;
}
.boards {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.board {
  background-color: white;
  color: black;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
  font-weight: bold;
}
.addBoard {
  color: #f7fafc;
  border-radius: 10px;
  background-color: #28a745;
  font-size: 16px;
  width: 60%;
  margin: 10px;
  padding: 20px;
  cursor: pointer;
}
.form-control {
  margin-bottom: 10px;
  /* font-family: "Love Ya Like A Sister", sans-serif; */
  /* display:inline-block;
        height: 30px;
        width: 300px;
        background-color: white;
        display: inline-block;
        border-radius: 5px; */
}
/* .btn {
  margin-bottom: 35px;
} */
.loading {
  flex: 3;
}
.selected {
  background-color: green;
}
/* .addBoard:hover {
  font-weight: bold;
} */

/* .board:hover:not(.board:focus),

.board:focus {
  font-weight: bold;
  border: solid blue 5px;
} */

/* .field{
        display:inline-block;
        height: 30px;
        width: 300px;
        background-color: white;
        display: inline-block;
        border-radius: 5px;
    
    } */
</style>