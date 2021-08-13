<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />

    <h2>Edit This Deck</h2>

    <div>
      <edit-deck-form
        :name="deck.name"
        :deckId="deck.deckId"
        :description="deck.description"
      />
    </div>
  </div>
</template>

<script>
//import DeckService from "@/services/DeckService";
import EditDeckForm from "./EditDeckForm.vue";

export default {
  name: "modifyThisDeck",
  components: {
    EditDeckForm,
  },
  data() {
    return {
      deck: {
        deckId: this.$store.state.deckId,
        name: "",
        description: "",
      },
    };
  },
  created() {
    let thisDeck = this.$store.state.decks.find((deck) => {
      return deck.deckId == this.deck.deckId;
    });
    this.deck.name = thisDeck.name;
    this.deck.description = thisDeck.description;
  },
  methods: {},
};
</script>

<style>
.flashcardDisplay {
  border: 2px solid black;
  border-radius: 10px;
  width: 650px;
  height: 300px;
  margin: 0 auto;
  background-image: url("../images/noteCard.jpg");
  background-size: cover;
}
.cardText {
  margin: 0 auto;
  font-size: 70px;
  color: navy;
  font-family: "Love Ya Like A Sister", sans-serif;
}
.keywordTable {
  margin: 0 auto;
  color: white;
  font-family: "Love Ya Like A Sister", sans-serif;
}
.cardHead {
  font-size: 30px;
  color: black;
  font-family: "Love Ya Like A Sister", sans-serif;
}
</style>