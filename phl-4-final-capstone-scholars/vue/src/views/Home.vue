`<template>
  <div class="home">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Mukta"
    />
    <!-- <p>You must be authenticated to see this</p> -->

    <nav>
      <div>
        <div class="grid">
          <div class="grid-card">
            <a><router-link :to="{ name: 'myflashcards' }" class="editCards">Flashcards</router-link></a>
          </div>
          <div class="grid-deck">
            <a><router-link :to="{ name: 'deck' }" class="createDeck">Decks</router-link></a>
          </div>
          <div class="grid-session">
            <a><router-link :to="{ name: 'session' }" class="newSession">Study Session</router-link></a>
          </div>
          <div class="grid-search">
            <a><router-link :to="{ name: 'searchCard' }" class="viewCards">Search Cards</router-link></a>
          </div>
          <div class="grid-view">
            <a><router-link :to="{ name: 'deckBoard' }" class="viewDecks">View Decks</router-link></a>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "home",
  components: {},
  data() {
    return {
      home: {
        flashcards: "",
        deck: "",
        deck_list: "",
        session: "",
      },
    };
  },
};
</script>

<style>
div {
  text-align: center;
  color: white;
}
h1 {
  font-family: "Love Ya Like A Sister", sans-serif;
}
ul {
  list-style-type: none;
  margin: 0 20px 0 20px;
  padding: 0;
}
li {
  font-family: "Love Ya Like A Sister", sans-serif;
  font-size: 2rem;
  font-weight: 400;
  text-transform: uppercase;
  border-bottom: 1px solid white;
  padding-top: 30px;
}
.editCards {
  font-size: 55px;
  width: 450px;
  height: 242px;
  background-image: url("../images/blue.png");
  background-size: 100%;
}
.grid-card {
  grid-area: flashcards;
}
.createDeck {
  font-size: 55px;
  width: 450px;
  height: 242px;
  background-image: url("../images/yellow.png");
  background-size: 100%;
}
.grid-deck{
  grid-area: decks;
}
.newSession {
  font-size: 55px;
  width: 450px;
  height: 242px;
  background-image: url("../images/orange.png");
  background-size: 100%;
}
.grid-session {
  grid-area: session;
}
.viewDecks {
  font-size: 55px;
  width: 450px;
  height: 242px;
  background-image: url("../images/red.png");
  background-size: 100%;
}
.grid-view {
  grid-area: viewDecks;
}
.viewCards {
  font-size: 55px;
  width: 450px;
  height: 242px;
  background-image: url("../images/green.png");
  background-size: 100%;
}
.grid-search {
  grid-area: searchCards;
}
.grid {
  display: grid;
  align-items: center;  
  grid-template-columns: 1fr 1fr 1fr;
  column-gap: 30px;
  grid-template-areas:
  "flashcards session decks"
  ". session ."
  "searchCards session viewDecks";
}
.editCards:link {
  size: 40px;
  padding-top: 200px;
  display: block;
  color: black;
  text-decoration: none;
}
.createDeck:link {
  size: 40px;
  padding-top: 200px;
  display: block;
  color: black;
  text-decoration: none;
}
.viewDecks:link {
  size: 40px;
  padding-top: 200px;
  display: block;
  color: black;
  text-decoration: none;
}
.viewCards:link {
  size: 40px;
  padding-top: 200px;
  display: block;
  color: black;
  text-decoration: none;
}
.newSession:link {
  size: 40px;
  padding-top: 200px;
  display: block;
  color: black;
  text-decoration: none;
}
.createDeck:visited {
  color: black;
}
.editCards:visited {
  color: black;
}
.viewDecks:visited {
  color: black;
}
.viewCards:visited {
  color: black;
}
.newSession:visited {
  color: black;
}
.editCards:hover {
  color: cyan;
}
.createDeck:hover {
  color: orangered;
}
.viewDecks:hover {
  color: hotpink;
}
.viewCards:hover {
  color: yellow;
}
.newSession:hover {
  color: red;
}
/* .row1 {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.row2 {
  display: flex;
  align-items: center;
  justify-content: space-between;
} */
</style>
