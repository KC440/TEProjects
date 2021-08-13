<template>
  <div class="home">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lobster">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta">
    <!-- <p>You must be authenticated to see this</p> -->
    <nav>
      <ul>
        <li><a><router-link :to="{name: 'myflashcards'}" class="editCards">Flashcards</router-link></a></li>
        <li><a><router-link :to="{name: 'deck'}" class="createDeck">Create Decks</router-link></a></li>
      </ul>
    </nav>
    <!-- <router-link>View Decks</router-link>
    <router-link>Study Session</router-link> -->
  </div>
</template>

<script>
export default {
  name: "home",
  components: {},
    data() {
        return {
            home: {
                flashcards: '',
                deck: '',
                deck_list: '',
                session: ''
            }
        }
    }
};
</script>

<style>
div {
  text-align: center;
  color: #F2CC65;
}

h1 {
  font-family: "Lobster", sans-serif;
}

.editCards {
  align-content: center;
}

ul {
  list-style-type: none;
  margin: 0 20px 0 20px;
  padding: 0;
}
li {
  font-family: "Mukta", sans-serif;
  font-size: 2rem;
  font-weight: 400;
  text-transform: uppercase;
  border-bottom: 1px solid#F2CC65;
  padding-top: 30px
}
.editCards:link {
  color: #F2CC65;
  text-decoration: none;
}
.createDeck:link {
  color: #F2CC65;
  text-decoration: none;
}
.createDeck:visited {
  color: #F2CC65;
}
.editCards:visited {
  color: #F2CC65;
}
.editCards:hover {
  color: #003049;
  padding-left: 5px;
}
.createDeck:hover {
  color: #003049;
  padding-left: 5px;
}

</style>
