<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister"
    />

    <form>
      <input
        class="field"
        placeholder="Question"
        type="text"
        v-model="flashcard.question"
        required
      />
      <input
        class="field"
        placeholder="Answer"
        type="text"
        v-model="flashcard.answer"
        required
      />

      <input
        class="field"
        placeholder="Enter keyword"
        type="text"
        v-model="keywordToAdd"
      />

      <button class="btn" @click.prevent="addNewKeyword">+Keyword</button>
      <div>
        <h4 v-show="this.flashcard.keywords.length === 0">
          At least 1 keyword required
        </h4>

        <button
          class="btn"
          v-show="this.flashcard.keywords.length !== 0"
          v-on:click.prevent="addNewFlashcardToDeck()"
        >
          Save
        </button>

        <button
          class="btn"
          v-show="this.flashcard.keywords.length !== 0"
          v-on:click.prevent="resetKeywords"
        >
          Reset Keywords
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import DeckService from "@/services/DeckService";
export default {
  name: "add-flashcard-to-deck",
  components: {},
  data() {
    return {
      flashcard: {
        deckId: this.$store.state.deckId,
        userId: this.$store.state.user.id,
        question: "",
        answer: "",
        keywords: [],
      },
      keywordToAdd: "",
    };
  },
  methods: {
    addNewFlashcardToDeck() {
      this.addNewKeyword();
      DeckService.addFlashCardToDeck(
        this.flashcard.deckId,
        this.flashcard
      ).then((response) => {
        if (response.status === 201 || response.status === 200) {
          this.$router.push("/deckBoard");
          this.clearForm();
          location.reload();
        }
      });
    },
    addNewKeyword() {
      if (
        this.keywordToAdd != "" &&
        !this.flashcard.keywords.includes(this.keywordToAdd.toLocaleLowerCase())
      ) {
        this.flashcard.keywords.push(this.keywordToAdd.toLocaleLowerCase());
        this.keywordToAdd = "";
      }
    },

    resetKeywords() {
      this.flashcard.keywords = [];
    },
    clearForm() {
      this.flashcard.question = "";
      this.flashcard.answer = "";
      this.resetKeywords();
    },
  },
};
</script>

<style>
.btn {
  display: inline-block;
  padding: 0.7em 1.7em;
  margin: 0 0.3em 0.3em 0;
  border-radius: 0.25rem;
  box-sizing: border-box;
  text-decoration: none;
  /* font-family:'Roboto',sans-serif; */
  font-family: "Love Ya Like A Sister", sans-serif;
  font-weight: 400;
  color: #ffffff;
  background-color: #3369ff;
  box-shadow: inset 0 -0.6em 1em -0.35em rgba(0, 0, 0, 0.17),
    inset 0 0.6em 2em -0.3em rgba(255, 255, 255, 0.15),
    inset 0 0 0em 0.05em rgba(255, 255, 255, 0.12);
  text-align: center;
  position: relative;
}

.field {
  display: inline-block;
  height: 30px;
  width: 300px;
  background-color: white;
  display: inline-block;
  border-radius: 5px;
}
</style>