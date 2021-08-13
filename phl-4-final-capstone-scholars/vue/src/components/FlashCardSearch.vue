<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <!-- create a search field with the id search-term -->
    <!--<label for="search-term">Search </label> -->
    <h2 class="title">Type in a keyword to find your card</h2>
    <div>
      <button class="button">
        <router-link :to="{ name: 'myflashcards' }" class="toFlashcards"
          >Edit/Create Flashcards</router-link
        >
      </button>
    </div>
    <input
      class="search-keyword"
      placeholder="Enter a Keyword"
      type="text"
      id="search-term"
      v-model="searchKeyword"
    />
    <button class="flashcard-search" v-on:click="getCardsByKeyWord">
      Search
    </button>
    <div class="status-message" v-show="errorMsg !== ''">{{ errorMsg }}</div>

    <div class="card-Container">
      <router-link
        class="cards"
        :to="{ path: `/myFlashcards/${card.flashCardId}` }"
        v-for="card in flashCards" v-bind:key="card.id">
        <!-- this should display  "flashcard-question"-->
        {{ card.question }}
      </router-link>
    </div>
  </div>
</template>

<script>
import FlashcardService from "@/services/FlashcardService";
export default {
  name: "my-cards-list",
  components: {},
  data() {
    return {
      flashCards: [],
      searchKeyword: "",
      errorMsg: "",
    };
  },

  methods: {
    clearSerchField() {
      (this.flashCards = []), (this.errorMsg = "");
    },

    getCardsByKeyWord() {
      this.clearSerchField();

      if (this.searchKeyword == "" || this.searchKeyword == undefined) {
        this.errorMsg = "Please enter a keyword.";
        return;
      }
      FlashcardService.getCardsByKeyWord(
        this.$store.state.user.id,
        this.searchKeyword
      )
        .then((response) => {
          if (response.status === 200) {
            this.flashCards = response.data;
          }
        })
        .catch((error) => {
          if (
            (error.response && error.response.status === 404) ||
            (error.response && error.response.status === 400)
          ) {
            this.errorMsg =
              "No Flashcard found with entered keyword: " + this.searchKeyword;
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

#serch-term {
  width: 70%;
}
.title {
  font-family: "Love Ya Like A Sister", sans-serif;
  color: #ffffff;
  font-size: 40px;
  padding-bottom: 40px;
  padding-right: 55px;
  justify-content: center;
}
.toFlashcards {
  color: white;
}
.toFlashcards:link {
  color: white;
  text-decoration: none;
}
.button {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  margin-bottom: 35px;
  border-radius: 0.2em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: "Love Ya Like A Sister", sans-serif;
  /* font-family:'Roboto',sans-serif; */
  font-weight: 400;
  color: #ffffff;
  background-color: #3369ff;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  position: relative;
}

.flashcard-search {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  border-radius: 0.2em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: "Love Ya Like A Sister", sans-serif;
  /* font-family:'Roboto',sans-serif; */
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
  background: #e2e1e0;
  text-align: center;
  color: black;
}

/* .card {
        box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
       
        display: inline-block;
        
        position: relative;
        color: black;
        font-weight: bold;
        font-family: 'Courier New', Courier, monospace;
        text-justify: center;
        
        font-size: 40px;
        margin: 7px;
        padding: 20px;
        border: 1px solid black;
        border-radius: 3px;
        background-image: url("../images/noteCard.jpg");
        background-size: cover;
    }  */

.cards {
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
.cards:link {
  color: navy;
  text-decoration: none;
}
.cards:visited {
  color: navy;
  text-decoration: none;
}

.card-Container {
  display: flex;
  align-items: center;

  flex-wrap: wrap;
  border: 3px solid antiquewhite;
  border-radius: 20px;
  width: 75vw;
  margin: 0 auto;
}
</style>